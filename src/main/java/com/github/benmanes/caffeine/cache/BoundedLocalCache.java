/*
 * Copyright 2014 Ben Manes. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.benmanes.caffeine.cache;

import static com.github.benmanes.caffeine.cache.Caffeine.requireArgument;
import static com.github.benmanes.caffeine.cache.Caffeine.requireState;
import static com.github.benmanes.caffeine.cache.Node.EDEN;
import static com.github.benmanes.caffeine.cache.Node.PROBATION;
import static com.github.benmanes.caffeine.cache.Node.PROTECTED;
import static java.util.Objects.requireNonNull;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

import com.github.benmanes.caffeine.base.UnsafeAccess;
import com.github.benmanes.caffeine.cache.LinkedDeque.PeekingIterator;
import com.github.benmanes.caffeine.cache.References.InternalReference;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;

/**
 * An in-memory cache implementation that supports full concurrency of retrievals, a high expected
 * concurrency for updates, and multiple ways to bound the cache.
 * <p>
 * This class is abstract and code generated subclasses provide the complete implementation for a
 * particular configuration. This is to ensure that only the fields and execution paths necessary
 * for a given configuration are used.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
@ThreadSafe
abstract class BoundedLocalCache<K, V> extends BLCHeader.DrainStatusRef<K, V>
    implements LocalCache<K, V> {

  /*
   * This class performs a best-effort bounding of a ConcurrentHashMap using a page-replacement
   * algorithm to determine which entries to evict when the capacity is exceeded.
   *
   * The page replacement algorithm's data structures are kept eventually consistent with the map.
   * An update to the map and recording of reads may not be immediately reflected on the algorithm's
   * data structures. These structures are guarded by a lock and operations are applied in batches
   * to avoid lock contention. The penalty of applying the batches is spread across threads so that
   * the amortized cost is slightly higher than performing just the ConcurrentHashMap operation.
   *
   * A memento of the reads and writes that were performed on the map are recorded in buffers. These
   * buffers are drained at the first opportunity after a write or when a read buffer is full. The
   * reads are offered in a buffer that will reject additions if contented on or if it is full and a
   * draining process is required. Due to the concurrent nature of the read and write operations a
   * strict policy ordering is not possible, but is observably strict when single threaded. The
   * buffers are drained asynchronously to minimize the request latency and uses a state machine to
   * determine when to schedule a task on an executor.
   *
   * Due to a lack of a strict ordering guarantee, a task can be executed out-of-order, such as a
   * removal followed by its addition. The state of the entry is encoded using the key field to
   * avoid additional memory. An entry is "alive" if it is in both the hash table and the page
   * replacement policy. It is "retired" if it is not in the hash table and is pending removal from
   * the page replacement policy. Finally an entry transitions to the "dead" state when it is not in
   * the hash table nor the page replacement policy. Both the retired and dead states are
   * represented by a sentinel key that should not be used for map lookups.
   *
   * Expiration is implemented in O(1) time complexity. The time-to-idle policy uses an access-order
   * queue and the time-to-live policy uses a write-order queue. This allows peeking at the oldest
   * entry in the queue to see if it has expired and, if it has not, then the younger entries must
   * not have too. If a maximum size is set then expiration will share the queues in order to
   * minimize the per-entry footprint. The expiration updates are applied in a best effort fashion.
   * The reordering of access expiration may be discarded by the read buffer if full or contended
   * on. Similarly the reordering of write expiration may be ignored for an entry if the last update
   * was within a short time window in order to avoid overwhelming the write buffer.
   *
   * Maximum size is implemented using the Window TinyLfu policy due to its high hit rate, O(1) time
   * complexity, and small footprint. A new entry starts in the eden space and remains there as long
   * as it has high temporal locality. Eventually an entry will slip from the eden space into the
   * main space. If the main space is already full, then a historic frequency filter determines
   * whether to evict the newly admitted entry or the victim entry chosen by main space's policy.
   * This process ensures that the entries in the main space have both a high recency and frequency.
   * The windowing allows the policy to have a high hit rate when entries exhibit a bursty (high
   * temporal, low frequency) access pattern. The eden space uses LRU and the main space uses
   * Segmented LRU.
   */

  static final Logger logger = Logger.getLogger(BoundedLocalCache.class.getName());

  /** The number of CPUs */
  static final int NCPU = Runtime.getRuntime().availableProcessors();
  /** The initial capacity of the write buffer. */
  static final int WRITE_BUFFER_MIN = 4;
  /** The maximum capacity of the write buffer. */
  static final int WRITE_BUFFER_MAX = 128 * ceilingPowerOfTwo(NCPU);
  /** The number of attempts to insert into the write buffer before yielding. */
  static final int WRITE_BUFFER_RETRIES = 100;
  /** The maximum weighted capacity of the map. */
  static final long MAXIMUM_CAPACITY = Long.MAX_VALUE - Integer.MAX_VALUE;
  /** The percent of the maximum weighted capacity dedicated to the main space. */
  static final double PERCENT_MAIN = 0.99d;
  /** The percent of the maximum weighted capacity dedicated to the main's protected space. */
  static final double PERCENT_MAIN_PROTECTED = 0.80d;
  /** The maximum time window between entry updates before the expiration must be reordered. */
  static final long EXPIRE_WRITE_TOLERANCE = TimeUnit.SECONDS.toNanos(1);

  final ConcurrentHashMap<Object, Node<K, V>> data;
  final PerformCleanupTask drainBuffersTask;
  final Consumer<Node<K, V>> accessPolicy;
  final CacheLoader<K, V> cacheLoader;
  final Buffer<Node<K, V>> readBuffer;
  final CacheWriter<K, V> writer;
  final NodeFactory nodeFactory;
  final Weigher<K, V> weigher;
  final Lock evictionLock;
  final Executor executor;
  final boolean isAsync;

  // The collection views
  transient Set<K> keySet;
  transient Collection<V> values;
  transient Set<Entry<K, V>> entrySet;

  /** Creates an instance based on the builder's configuration. */
  protected BoundedLocalCache(Caffeine<K, V> builder,
      @Nullable CacheLoader<K, V> cacheLoader, boolean isAsync) {
    this.isAsync = isAsync;
    this.cacheLoader = cacheLoader;
    executor = builder.getExecutor();
    writer = builder.getCacheWriter();
    weigher = builder.getWeigher(isAsync);
    drainBuffersTask = new PerformCleanupTask();
    data = new ConcurrentHashMap<>(builder.getInitialCapacity());
    evictionLock = (builder.getExecutor() instanceof ForkJoinPool)
        ? new NonReentrantLock()
        : new ReentrantLock();
    nodeFactory = NodeFactory.getFactory(builder.isStrongKeys(), builder.isWeakKeys(),
        builder.isStrongValues(), builder.isWeakValues(), builder.isSoftValues(),
        builder.expiresAfterAccess(), builder.expiresAfterWrite(), builder.refreshes(),
        builder.evicts(), (isAsync && builder.evicts()) || builder.isWeighted());
    readBuffer = evicts() || collectKeys() || collectValues() || expiresAfterAccess()
        ? new BoundedBuffer<>()
        : Buffer.disabled();
    accessPolicy = (evicts() || expiresAfterAccess()) ? this::onAccess : e -> {};

    if (evicts()) {
      setMaximum(builder.getMaximum());
    }
  }

  static int ceilingPowerOfTwo(int x) {
    // From Hacker's Delight, Chapter 3, Harry S. Warren Jr.
    return 1 << -Integer.numberOfLeadingZeros(x - 1);
  }

  /* ---------------- Shared -------------- */

  /** Returns if the node's value is currently being computed, asynchronously. */
  final boolean isComputingAsync(Node<?, ?> node) {
    return isAsync && !Async.isReady((CompletableFuture<?>) node.getValue());
  }

  @GuardedBy("evictionLock")
  protected AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock")
  protected AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock")
  protected AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock")
  protected WriteOrderDeque<Node<K, V>> writeOrderDeque() {
    throw new UnsupportedOperationException();
  }

  /** If the page replacement policy buffers writes. */
  protected boolean buffersWrites() {
    return false;
  }

  protected MpscGrowableArrayQueue<Runnable> writeBuffer() {
    throw new UnsupportedOperationException();
  }

  @Override
  public final Executor executor() {
    return executor;
  }

  /** Returns whether this cache notifies a writer when an entry is modified. */
  protected boolean hasWriter() {
    return (writer != CacheWriter.disabledWriter());
  }

  /* ---------------- Stats Support -------------- */

  @Override
  public boolean isRecordingStats() {
    return false;
  }

  @Override
  public StatsCounter statsCounter() {
    return StatsCounter.disabledStatsCounter();
  }

  @Override
  public Ticker statsTicker() {
    return Ticker.disabledTicker();
  }

  /* ---------------- Removal Listener Support -------------- */

  @Override
  public RemovalListener<K, V> removalListener() {
    return null;
  }

  @Override
  public boolean hasRemovalListener() {
    return false;
  }

  @Override
  public void notifyRemoval(@Nullable K key, @Nullable V value, RemovalCause cause) {
    requireState(hasRemovalListener(), "Notification should be guarded with a check");
    Runnable task = () -> {
      try {
        removalListener().onRemoval(key, value, cause);
      } catch (Throwable t) {
        logger.log(Level.WARNING, "Exception thrown by removal listener", t);
      }
    };
    try {
      executor().execute(task);
    } catch (Throwable t) {
      logger.log(Level.SEVERE, "Exception thrown when submitting removal listener", t);
      task.run();
    }
  }

  /* ---------------- Reference Support -------------- */

  /** Returns if the keys are weak reference garbage collected. */
  protected boolean collectKeys() {
    return false;
  }

  /** Returns if the values are weak or soft reference garbage collected. */
  protected boolean collectValues() {
    return false;
  }

  @Nullable
  protected ReferenceQueue<K> keyReferenceQueue() {
    return null;
  }

  @Nullable
  protected ReferenceQueue<V> valueReferenceQueue() {
    return null;
  }

  /* ---------------- Expiration Support -------------- */

  /** Returns if the cache expires entries after an access time threshold. */
  protected boolean expiresAfterAccess() {
    return false;
  }

  /** How long after the last access to an entry the map will retain that entry. */
  protected long expiresAfterAccessNanos() {
    throw new UnsupportedOperationException();
  }

  protected void setExpiresAfterAccessNanos(long expireAfterAccessNanos) {
    throw new UnsupportedOperationException();
  }

  /** Returns if the cache expires entries after an write time threshold. */
  protected boolean expiresAfterWrite() {
    return false;
  }

  /** How long after the last write to an entry the map will retain that entry. */
  protected long expiresAfterWriteNanos() {
    throw new UnsupportedOperationException();
  }

  protected void setExpiresAfterWriteNanos(long expireAfterWriteNanos) {
    throw new UnsupportedOperationException();
  }

  /** Returns if the cache refreshes entries after an write time threshold. */
  protected boolean refreshAfterWrite() {
    return false;
  }

  /** How long after the last write an entry becomes a candidate for refresh. */
  protected long refreshAfterWriteNanos() {
    throw new UnsupportedOperationException();
  }

  protected void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean hasWriteTime() {
    return expiresAfterWrite() || refreshAfterWrite();
  }

  @Override
  public Ticker expirationTicker() {
    return Ticker.disabledTicker();
  }

  /* ---------------- Eviction Support -------------- */

  /** Returns if the cache evicts entries due to a maximum size or weight threshold. */
  protected boolean evicts() {
    return false;
  }

  /** Returns if entries may be assigned different weights. */
  protected boolean isWeighted() {
    return (weigher != Weigher.singletonWeigher());
  }

  protected FrequencySketch<K> frequencySketch() {
    throw new UnsupportedOperationException();
  }

  /** Returns if an access to an entry can skip notifying the eviction policy. */
  protected boolean fastpath() {
    return false;
  }

  /** Returns the maximum weighted size. */
  protected long maximum() {
    throw new UnsupportedOperationException();
  }

  /** Returns the maximum weighted size of the eden space. */
  protected long edenMaximum() {
    throw new UnsupportedOperationException();
  }

  /** Returns the maximum weighted size of the main's protected space. */
  protected long mainProtectedMaximum() {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetMaximum(long maximum) {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetEdenMaximum(long maximum) {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetMainProtectedMaximum(long maximum) {
    throw new UnsupportedOperationException();
  }

  /**
   * Sets the maximum weighted size of the cache. The caller may need to perform a maintenance cycle
   * to eagerly evicts entries until the cache shrinks to the appropriate size.
   */
  @GuardedBy("evictionLock")
  void setMaximum(long maximum) {
    requireArgument(maximum >= 0);

    long max = Math.min(maximum, MAXIMUM_CAPACITY);
    long eden = max - (long) (max * PERCENT_MAIN);
    long mainProtected = (long) ((max - eden) * PERCENT_MAIN_PROTECTED);

    lazySetMaximum(max);
    lazySetEdenMaximum(eden);
    lazySetMainProtectedMaximum(mainProtected);

    if ((frequencySketch() != null) && !isWeighted() && (weightedSize() >= (max >>> 1))) {
      // Lazily initialize when close to the maximum size
      frequencySketch().ensureCapacity(max);
    }
  }

  /** Returns the combined weight of the values in the cache. */
  long adjustedWeightedSize() {
    return Math.max(0, weightedSize());
  }

  /** Returns the uncorrected combined weight of the values in the cache. */
  protected long weightedSize() {
    throw new UnsupportedOperationException();
  }

  /** Returns the uncorrected combined weight of the values in the eden space. */
  protected long edenWeightedSize() {
    throw new UnsupportedOperationException();
  }

  /** Returns the uncorrected combined weight of the values in the main's protected space. */
  protected long mainProtectedWeightedSize() {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetWeightedSize(long weightedSize) {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetEdenWeightedSize(long weightedSize) {
    throw new UnsupportedOperationException();
  }

  @GuardedBy("evictionLock") // must write under lock
  protected void lazySetMainProtectedWeightedSize(long weightedSize) {
    throw new UnsupportedOperationException();
  }

  /** Evicts entries if the cache exceeds the maximum. */
  @GuardedBy("evictionLock")
  void evictEntries() {
    if (!evicts()) {
      return;
    }
    int candidates = evictFromEden();
    evictFromMain(candidates);
  }

  /**
   * Evicts entries from the eden space into the main space while the eden size exceeds a maximum.
   *
   * @return the number of candidate entries evicted from the eden space
   */
  @GuardedBy("evictionLock")
  int evictFromEden() {
    int candidates = 0;
    Node<K, V> node = accessOrderEdenDeque().peek();
    while (edenWeightedSize() > edenMaximum()) {
      // The pending operations will adjust the size to reflect the correct weight
      if (node == null) {
        break;
      }

      Node<K, V> next = node.getNextInAccessOrder();
      if (node.getWeight() != 0) {
        node.makeMainProbation();
        accessOrderEdenDeque().remove(node);
        accessOrderProbationDeque().add(node);
        candidates++;

        lazySetEdenWeightedSize(edenWeightedSize() - node.getPolicyWeight());
      }
      node = next;
    }

    return candidates;
  }

  /**
   * Evicts entries from the main space if the cache exceeds the maximum capacity. The main space
   * determines whether admitting an entry (coming from the eden space) is preferable to retaining
   * the eviction policy's victim. This is decision is made using a frequency filter so that the
   * least frequently used entry is removed.
   *
   * The eden space candidates were previously placed in the MRU position and the eviction policy's
   * victim is at the LRU position. The two ends of the queue are evaluated while an eviction is
   * required. The number of remaining candidates is provided and decremented on eviction, so that
   * when there are no more candidates the victim is evicted.
   *
   * @param candidates the number of candidate entries evicted from the eden space
   */
  @GuardedBy("evictionLock")
  void evictFromMain(int candidates) {
    int victimQueue = PROBATION;
    Node<K, V> victim = accessOrderProbationDeque().peekFirst();
    Node<K, V> candidate = accessOrderProbationDeque().peekLast();
    while (weightedSize() > maximum()) {
      // Stop trying to evict candidates and always prefer the victim
      if (candidates == 0) {
        candidate = null;
      }

      // Try evicting from the protected and eden queues
      if ((candidate == null) && (victim == null)) {
        if (victimQueue == PROBATION) {
          victim = accessOrderProtectedDeque().peekFirst();
          victimQueue = PROTECTED;
          continue;
        } else if (victimQueue == PROTECTED) {
          victim = accessOrderEdenDeque().peekFirst();
          victimQueue = EDEN;
          continue;
        }

        // The pending operations will adjust the size to reflect the correct weight
        break;
      }

      // Skip over entries with zero weight
      if ((victim != null) && (victim.getPolicyWeight() == 0)) {
        victim = victim.getNextInAccessOrder();
        continue;
      } else if ((candidate != null) && (candidate.getPolicyWeight() == 0)) {
        candidate = candidate.getPreviousInAccessOrder();
        candidates--;
        continue;
      }

      // Evict immediately if only one of the entries is present
      if (victim == null) {
        candidates--;
        Node<K, V> evict = candidate;
        candidate = candidate.getPreviousInAccessOrder();
        evictEntry(evict, RemovalCause.SIZE, 0L);
        continue;
      } else if (candidate == null) {
        Node<K, V> evict = victim;
        victim = victim.getNextInAccessOrder();
        evictEntry(evict, RemovalCause.SIZE, 0L);
        continue;
      }

      // Evict immediately if an entry was collected
      K victimKey = victim.getKey();
      K candidateKey = candidate.getKey();
      if (victimKey == null) {
        Node<K, V> evict = victim;
        victim = victim.getNextInAccessOrder();
        evictEntry(evict, RemovalCause.COLLECTED, 0L);
        continue;
      } else if (candidateKey == null) {
        candidates--;
        Node<K, V> evict = candidate;
        candidate = candidate.getPreviousInAccessOrder();
        evictEntry(evict, RemovalCause.COLLECTED, 0L);
        continue;
      }

      // Evict immediately if the candidate's weight exceeds the maximum
      if (candidate.getPolicyWeight() > maximum()) {
        candidates--;
        Node<K, V> evict = candidate;
        candidate = candidate.getPreviousInAccessOrder();
        evictEntry(evict, RemovalCause.SIZE, 0L);
        continue;
      }

      // Evict the entry with the lowest frequency
      candidates--;
      if (admit(candidateKey, victimKey)) {
        Node<K, V> evict = victim;
        victim = victim.getNextInAccessOrder();
        evictEntry(evict, RemovalCause.SIZE, 0L);
        candidate = candidate.getPreviousInAccessOrder();
      } else {
        Node<K, V> evict = candidate;
        candidate = candidate.getPreviousInAccessOrder();
        evictEntry(evict, RemovalCause.SIZE, 0L);
      }
    }
  }

  /**
   * Determines if the candidate should be accepted into the main space, as determined by its
   * frequency relative to the victim. A small amount of randomness is used to protect against hash
   * collision attacks, where the victim's frequency is artificially raised so that no new entries
   * are admitted.
   *
   * @param candidateKey the key for the entry being proposed for long term retention
   * @param victimKey the key for the entry chosen by the eviction policy for replacement
   * @return if the candidate should be admitted and the victim ejected
   */
  @GuardedBy("evictionLock")
  boolean admit(K candidateKey, K victimKey) {
    int victimFreq = frequencySketch().frequency(victimKey);
    int candidateFreq = frequencySketch().frequency(candidateKey);
    if (candidateFreq > victimFreq) {
      return true;
    } else if (candidateFreq <= 5) {
      // The maximum frequency is 15 and halved to 7 after a reset to age the history. An attack
      // exploits that a hot candidate is rejected in favor of a hot victim. The threshold of a warm
      // candidate reduces the number of random acceptances to minimize the impact on the hit rate.
      return false;
    }
    int random = ThreadLocalRandom.current().nextInt();
    return ((random & 127) == 0);
  }

  /** Expires entries that have expired in the access and write queues. */
  @GuardedBy("evictionLock")
  void expireEntries() {
    long now = expirationTicker().read();
    expireAfterAccessEntries(now);
    expireAfterWriteEntries(now);
  }

  /** Expires entries in the access-order queue. */
  @GuardedBy("evictionLock")
  void expireAfterAccessEntries(long now) {
    if (!expiresAfterAccess()) {
      return;
    }

    long expirationTime = (now - expiresAfterAccessNanos());
    expireAfterAccessEntries(accessOrderEdenDeque(), expirationTime, now);
    if (evicts()) {
      expireAfterAccessEntries(accessOrderProbationDeque(), expirationTime, now);
      expireAfterAccessEntries(accessOrderProtectedDeque(), expirationTime, now);
    }
  }

  /** Expires entries in an access-order queue. */
  @GuardedBy("evictionLock")
  void expireAfterAccessEntries(AccessOrderDeque<Node<K, V>> accessOrderDeque,
      long expirationTime, long now) {
    for (;;) {
      Node<K, V> node = accessOrderDeque.peekFirst();
      if ((node == null) || (node.getAccessTime() > expirationTime)) {
        return;
      }
      evictEntry(node, RemovalCause.EXPIRED, now);
    }
  }

  /** Expires entries on the write-order queue. */
  @GuardedBy("evictionLock")
  void expireAfterWriteEntries(long now) {
    if (!expiresAfterWrite()) {
      return;
    }
    long expirationTime = now - expiresAfterWriteNanos();
    for (;;) {
      final Node<K, V> node = writeOrderDeque().peekFirst();
      if ((node == null) || (node.getWriteTime() > expirationTime)) {
        break;
      }
      evictEntry(node, RemovalCause.EXPIRED, now);
    }
  }

  /** Returns if the entry has expired. */
  boolean hasExpired(Node<K, V> node, long now) {
    if (isComputingAsync(node)) {
      return false;
    }
    return (expiresAfterAccess() && (now - node.getAccessTime() >= expiresAfterAccessNanos()))
        || (expiresAfterWrite() && (now - node.getWriteTime() >= expiresAfterWriteNanos()));
  }

  /**
   * Attempts to evict the entry based on the given removal cause. A removal due to expiration or
   * size may be ignored if the entry was updated and is no longer eligible for eviction.
   *
   * @param node the entry to evict
   * @param cause the reason to evict
   * @param now the current time, used only if expiring
   */
  @GuardedBy("evictionLock")
  @SuppressWarnings("PMD.CollapsibleIfStatements")
  void evictEntry(Node<K, V> node, RemovalCause cause, long now) {
    K key = node.getKey();
    V value = node.getValue();
    boolean[] removed = new boolean[1];
    boolean[] resurrect = new boolean[1];
    RemovalCause actualCause = (key == null) || (value == null) ? RemovalCause.COLLECTED : cause;

    data.computeIfPresent(node.getKeyReference(), (k, n) -> {
      if (n != node) {
        return n;
      }
      if (actualCause == RemovalCause.EXPIRED) {
        boolean expired = false;
        if (expiresAfterAccess()) {
          long expirationTime = now - expiresAfterAccessNanos();
          expired |= n.getAccessTime() <= expirationTime;
        }
        if (expiresAfterWrite()) {
          long expirationTime = now - expiresAfterWriteNanos();
          expired |= n.getWriteTime() <= expirationTime;
        }
        if (!expired) {
          resurrect[0] = true;
          return n;
        }
      } else if (actualCause == RemovalCause.SIZE) {
        int weight;
        synchronized (node) {
          weight = node.getWeight();
        }
        if (weight == 0) {
          resurrect[0] = true;
          return n;
        }
      }
      writer.delete(key, value, actualCause);
      removed[0] = true;
      return null;
    });

    // The entry is no longer eligible for eviction
    if (resurrect[0]) {
      return;
    }

    // If the eviction fails due to a concurrent removal of the victim, that removal may cancel out
    // the addition that triggered this eviction. The victim is eagerly unlinked before the removal
    // task so that if an eviction is still required then a new victim will be chosen for removal.
    makeDead(node);
    if (node.inEden() && (evicts() || expiresAfterAccess())) {
      accessOrderEdenDeque().remove(node);
    } else if (evicts()) {
      if (node.inMainProbation()) {
        accessOrderProbationDeque().remove(node);
      } else {
        accessOrderProtectedDeque().remove(node);
      }
    }
    if (expiresAfterWrite()) {
      writeOrderDeque().remove(node);
    }

    if (removed[0]) {
      statsCounter().recordEviction(node.getWeight());
      if (hasRemovalListener()) {
        // Notify the listener only if the entry was evicted. This must be performed as the last
        // step during eviction to safe guard against the executor rejecting the notification task.
        notifyRemoval(key, value, actualCause);
      }
    }
  }

  /**
   * Performs the post-processing work required after a read.
   *
   * @param node the entry in the page replacement policy
   * @param now the current expiration time, in nanoseconds
   * @param recordHit if the hit count should be incremented
   */
  void afterRead(Node<K, V> node, long now, boolean recordHit) {
    if (recordHit) {
      statsCounter().recordHits(1);
    }
    node.setAccessTime(now);

    boolean delayable = skipReadBuffer() || (readBuffer.offer(node) != Buffer.FULL);
    if (shouldDrainBuffers(delayable)) {
      scheduleDrainBuffers();
    }
    refreshIfNeeded(node, now);
  }

  /** Returns if the cache should bypass the read buffer. */
  boolean skipReadBuffer() {
    return fastpath() && frequencySketch().isNotInitialized();
  }

  /**
   * Asynchronously refreshes the entry if eligible.
   *
   * @param node the entry in the cache to refresh
   * @param now the current time, in nanoseconds
   */
  void refreshIfNeeded(Node<K, V> node, long now) {
    if (!refreshAfterWrite()) {
      return;
    }
    K key;
    V oldValue;
    long oldWriteTime = node.getWriteTime();
    long refreshWriteTime = isAsync ? Long.MAX_VALUE : now;
    if (((now - oldWriteTime) > refreshAfterWriteNanos())
        && ((key = node.getKey()) != null) && ((oldValue = node.getValue()) != null)
        && node.casWriteTime(oldWriteTime, refreshWriteTime)) {
      try {
        CompletableFuture<V> refreshFuture;
        if (isAsync) {
          @SuppressWarnings("unchecked")
          CompletableFuture<V> future = (CompletableFuture<V>) oldValue;
          if (Async.isReady(future)) {
            refreshFuture = future.thenCompose(value ->
              cacheLoader.asyncReload(key, value, executor));
          } else {
            // no-op if load is pending
            node.casWriteTime(refreshWriteTime, oldWriteTime);
            return;
          }
        } else {
          refreshFuture = cacheLoader.asyncReload(key, oldValue, executor);
        }
        refreshFuture.whenComplete((newValue, error) -> {
          long loadTime = statsTicker().read() - now;
          if (error != null) {
            logger.log(Level.WARNING, "Exception thrown during refresh", error);
            node.casWriteTime(refreshWriteTime, oldWriteTime);
            statsCounter().recordLoadFailure(loadTime);
            return;
          }

          @SuppressWarnings("unchecked")
          V value = (isAsync && (newValue != null)) ? (V) refreshFuture : newValue;

          boolean[] discard = new boolean[1];
          compute(key, (k, currentValue) -> {
            if (currentValue == null) {
              return value;
            } else if ((currentValue == oldValue) && (node.getWriteTime() == refreshWriteTime)) {
              return value;
            }
            discard[0] = true;
            return currentValue;
          }, /* recordMiss */ false, /* recordLoad */ false);

          if (discard[0] && hasRemovalListener()) {
            notifyRemoval(key, value, RemovalCause.REPLACED);
          }
          if (newValue == null) {
            statsCounter().recordLoadFailure(loadTime);
          } else {
            statsCounter().recordLoadSuccess(loadTime);
          }
        });
      } catch (Throwable t) {
        node.casWriteTime(refreshWriteTime, oldWriteTime);
        logger.log(Level.SEVERE, "Exception thrown when submitting refresh task", t);
      }
    }
  }

  /**
   * Performs the post-processing work required after a write.
   *
   * @param node the node that was written to
   * @param task the pending operation to be applied
   * @param now the current expiration time, in nanoseconds
   */
  void afterWrite(@Nullable Node<K, V> node, Runnable task, long now) {
    if (node != null) {
      node.setAccessTime(now);
      node.setWriteTime(now);
    }
    if (buffersWrites()) {
      for (int i = 0; i < WRITE_BUFFER_RETRIES; i++) {
        if (writeBuffer().offer(task)) {
          scheduleAfterWrite();
          return;
        }
        scheduleDrainBuffers();
      }

      // The maintenance task may be scheduled but not running due to all of the executor's threads
      // being busy. If all of the threads are writing into the cache then no progress can be made
      // without assistance.
      try {
        performCleanUp(task);
      } catch (RuntimeException e) {
        logger.log(Level.SEVERE, "Exception thrown when performing the maintenance task", e);
      }
    } else {
      scheduleAfterWrite();
    }
  }

  /**
   * Conditionally schedules the asynchronous maintenance task after a write operation. If the
   * task status was IDLE or REQUIRED then the maintenance task is scheduled immediately. If it
   * is already processing then it is set to transition to REQUIRED upon completion so that a new
   * execution is triggered by the next operation.
   */
  void scheduleAfterWrite() {
    for (;;) {
      switch (drainStatus()) {
        case IDLE:
          casDrainStatus(IDLE, REQUIRED);
          scheduleDrainBuffers();
          return;
        case REQUIRED:
          scheduleDrainBuffers();
          return;
        case PROCESSING_TO_IDLE:
          if (casDrainStatus(PROCESSING_TO_IDLE, PROCESSING_TO_REQUIRED)) {
            return;
          }
          continue;
        case PROCESSING_TO_REQUIRED:
          return;
        default:
          throw new IllegalStateException();
      }
    }
  }

  /**
   * Attempts to schedule an asynchronous task to apply the pending operations to the page
   * replacement policy. If the executor rejects the task then it is run directly.
   */
  void scheduleDrainBuffers() {
    if (drainStatus() >= PROCESSING_TO_IDLE) {
      return;
    }
    if (evictionLock.tryLock()) {
      try {
        int drainStatus = drainStatus();
        if (drainStatus >= PROCESSING_TO_IDLE) {
          return;
        }
        lazySetDrainStatus(PROCESSING_TO_IDLE);
        executor().execute(drainBuffersTask);
      } catch (Throwable t) {
        logger.log(Level.WARNING, "Exception thrown when submitting maintenance task", t);
        maintenance(/* ignored */ null);
      } finally {
        evictionLock.unlock();
      }
    }
  }

  @Override
  public void cleanUp() {
    try {
      performCleanUp(/* ignored */ null);
    } catch (RuntimeException e) {
      logger.log(Level.SEVERE, "Exception thrown when performing the maintenance task", e);
    }
  }

  /**
   * Performs the maintenance work, blocking until the lock is acquired. Any exception thrown, such
   * as by {@link CacheWriter#delete()}, is propagated to the caller.
   *
   * @param task an additional pending task to run, or {@code null} if not present
   */
  void performCleanUp(@Nullable Runnable task) {
    evictionLock.lock();
    try {
      maintenance(task);
    } finally {
      evictionLock.unlock();
    }
  }

  /**
   * Performs the pending maintenance work and sets the state flags during processing to avoid
   * excess scheduling attempts. The read buffer, write buffer, and reference queues are
   * drained, followed by expiration, and size-based eviction.
   *
   * @param task an additional pending task to run, or {@code null} if not present
   */
  @GuardedBy("evictionLock")
  void maintenance(@Nullable Runnable task) {
    lazySetDrainStatus(PROCESSING_TO_IDLE);

    try {
      drainReadBuffer();

      drainWriteBuffer();
      if (task != null) {
        task.run();
      }

      drainKeyReferences();
      drainValueReferences();

      expireEntries();
      evictEntries();
    } finally {
      if ((drainStatus() != PROCESSING_TO_IDLE) || !casDrainStatus(PROCESSING_TO_IDLE, IDLE)) {
        lazySetDrainStatus(REQUIRED);
      }
    }
  }

  /** Drains the weak key references queue. */
  @GuardedBy("evictionLock")
  void drainKeyReferences() {
    if (!collectKeys()) {
      return;
    }
    Reference<? extends K> keyRef;
    while ((keyRef = keyReferenceQueue().poll()) != null) {
      Node<K, V> node = data.get(keyRef);
      if (node != null) {
        evictEntry(node, RemovalCause.COLLECTED, 0L);
      }
    }
  }

  /** Drains the weak / soft value references queue. */
  @GuardedBy("evictionLock")
  void drainValueReferences() {
    if (!collectValues()) {
      return;
    }
    Reference<? extends V> valueRef;
    while ((valueRef = valueReferenceQueue().poll()) != null) {
      @SuppressWarnings("unchecked")
      InternalReference<V> ref = (InternalReference<V>) valueRef;
      Node<K, V> node = data.get(ref.getKeyReference());
      if ((node != null) && (valueRef == node.getValueReference())) {
        evictEntry(node, RemovalCause.COLLECTED, 0L);
      }
    }
  }

  /** Drains the read buffer. */
  @GuardedBy("evictionLock")
  void drainReadBuffer() {
    if (!skipReadBuffer()) {
      readBuffer.drainTo(accessPolicy);
    }
  }

  /** Updates the node's location in the page replacement policy. */
  @GuardedBy("evictionLock")
  void onAccess(Node<K, V> node) {
    if (evicts()) {
      K key = node.getKey();
      if (key == null) {
        return;
      }
      frequencySketch().increment(key);
      if (node.inEden()) {
        reorder(accessOrderEdenDeque(), node);
      } else if (node.inMainProbation()) {
        reorderProbation(node);
      } else {
        reorder(accessOrderProtectedDeque(), node);
      }
    } else if (expiresAfterAccess()) {
      reorder(accessOrderEdenDeque(), node);
    }
  }

  /** Promote the node from probation to protected on an access. */
  @GuardedBy("evictionLock")
  void reorderProbation(Node<K, V> node) {
    if (!accessOrderProbationDeque().contains(node)) {
      // Ignore stale accesses for an entry that is no longer present
      return;
    } else if (node.getPolicyWeight() > mainProtectedMaximum()) {
      return;
    }

    long mainProtectedWeightedSize = mainProtectedWeightedSize() + node.getPolicyWeight();
    accessOrderProbationDeque().remove(node);
    accessOrderProtectedDeque().add(node);
    node.makeMainProtected();

    long mainProtectedMaximum = mainProtectedMaximum();
    while (mainProtectedWeightedSize > mainProtectedMaximum) {
      Node<K, V> demoted = accessOrderProtectedDeque().pollFirst();
      if (demoted == null) {
        break;
      }
      demoted.makeMainProbation();
      accessOrderProbationDeque().add(demoted);
      mainProtectedWeightedSize -= node.getPolicyWeight();
    }

    lazySetMainProtectedWeightedSize(mainProtectedWeightedSize);
  }

  /** Updates the node's location in the policy's deque. */
  static <K, V> void reorder(LinkedDeque<Node<K, V>> deque, Node<K, V> node) {
    // An entry may be scheduled for reordering despite having been removed. This can occur when the
    // entry was concurrently read while a writer was removing it. If the entry is no longer linked
    // then it does not need to be processed.
    if (deque.contains(node)) {
      deque.moveToBack(node);
    }
  }

  /** Drains the write buffer. */
  @GuardedBy("evictionLock")
  void drainWriteBuffer() {
    if (!buffersWrites()) {
      return;
    }
    for (int i = 0; i < WRITE_BUFFER_MAX; i++) {
      Runnable task = writeBuffer().poll();
      if (task == null) {
        break;
      }
      task.run();
    }
  }

  /**
   * Atomically transitions the node to the <tt>dead</tt> state and decrements the
   * <tt>weightedSize</tt>.
   *
   * @param node the entry in the page replacement policy
   */
  @GuardedBy("evictionLock")
  void makeDead(Node<K, V> node) {
    synchronized (node) {
      if (node.isDead()) {
        return;
      }
      if (evicts()) {
        // The node's policy weight may be out of sync due to a pending update waiting to be
        // processed. At this point the node's weight is finalized, so the weight can be safely
        // taken from the node's perspective and the sizes will be adjusted correctly.
        if (node.inEden()) {
          lazySetEdenWeightedSize(edenWeightedSize() - node.getWeight());
        } else if (node.inMainProtected()) {
          lazySetMainProtectedWeightedSize(mainProtectedWeightedSize() - node.getWeight());
        }
        lazySetWeightedSize(weightedSize() - node.getWeight());
      }
      node.die();
    }
  }

  /** Adds the node to the page replacement policy. */
  final class AddTask implements Runnable {
    final Node<K, V> node;
    final int weight;

    AddTask(Node<K, V> node, int weight) {
      this.weight = weight;
      this.node = node;
    }

    @Override
    @GuardedBy("evictionLock")
    public void run() {
      if (evicts()) {
        node.setPolicyWeight(weight);
        long weightedSize = weightedSize();
        lazySetWeightedSize(weightedSize + weight);
        lazySetEdenWeightedSize(edenWeightedSize() + weight);

        long maximum = maximum();
        if (weightedSize >= (maximum >>> 1)) {
          // Lazily initialize when close to the maximum
          long capacity = isWeighted() ? data.mappingCount() : maximum;
          frequencySketch().ensureCapacity(capacity);
        }

        K key = node.getKey();
        if (key != null) {
          frequencySketch().increment(key);
        }
      }

      // ignore out-of-order write operations
      boolean isAlive;
      synchronized (node) {
        isAlive = node.isAlive();
      }
      if (isAlive) {
        if (expiresAfterWrite()) {
          writeOrderDeque().add(node);
        }
        if (evicts() || expiresAfterAccess()) {
          accessOrderEdenDeque().add(node);
        }
      }

      // Ensure that in-flight async computation cannot expire
      if (isComputingAsync(node)) {
        CompletableFuture<?> future = (CompletableFuture<?>) node.getValue();
        if (future != null) {
          node.setAccessTime(Long.MAX_VALUE);
          node.setWriteTime(Long.MAX_VALUE);
          future.thenRun(() -> {
            long now = expirationTicker().read();
            node.setAccessTime(now);
            node.setWriteTime(now);
          });
        }
      }
    }
  }

  /** Removes a node from the page replacement policy. */
  final class RemovalTask implements Runnable {
    final Node<K, V> node;

    RemovalTask(Node<K, V> node) {
      this.node = node;
    }

    @Override
    @GuardedBy("evictionLock")
    public void run() {
      // add may not have been processed yet
      if (node.inEden() && (evicts() || expiresAfterAccess())) {
        accessOrderEdenDeque().remove(node);
      } else if (evicts()) {
        if (node.inMainProbation()) {
          accessOrderProbationDeque().remove(node);
        } else {
          accessOrderProtectedDeque().remove(node);
        }
      }
      if (expiresAfterWrite()) {
        writeOrderDeque().remove(node);
      }
      makeDead(node);
    }
  }

  /** Updates the weighted size and evicts an entry on overflow. */
  final class UpdateTask implements Runnable {
    final int weightDifference;
    final Node<K, V> node;

    public UpdateTask(Node<K, V> node, int weightDifference) {
      this.weightDifference = weightDifference;
      this.node = node;
    }

    @Override
    @GuardedBy("evictionLock")
    public void run() {
      if (evicts()) {
        if (node.inEden()) {
          lazySetEdenWeightedSize(edenWeightedSize() + weightDifference);
        } else if (node.inMainProtected()) {
          lazySetMainProtectedWeightedSize(mainProtectedMaximum() + weightDifference);
        }
        lazySetWeightedSize(weightedSize() + weightDifference);
        node.setPolicyWeight(node.getPolicyWeight() + weightDifference);
      }
      if (evicts() || expiresAfterAccess()) {
        onAccess(node);
      }
      if (expiresAfterWrite()) {
        reorder(writeOrderDeque(), node);
      }
    }
  }

  /* ---------------- Concurrent Map Support -------------- */

  @Override
  public boolean isEmpty() {
    return data.isEmpty();
  }

  @Override
  public int size() {
    return data.size();
  }

  @Override
  public long estimatedSize() {
    return data.mappingCount();
  }

  @Override
  public void clear() {
    long now = expirationTicker().read();

    evictionLock.lock();
    try {
      // Apply all pending writes
      Runnable task;
      while (buffersWrites() && (task = writeBuffer().poll()) != null) {
        task.run();
      }

      // Discard all entries
      if (evicts() || expiresAfterAccess()) {
        removeNodes(accessOrderEdenDeque(), now);
      }
      if (evicts()) {
        removeNodes(accessOrderProbationDeque(), now);
        removeNodes(accessOrderProtectedDeque(), now);
      }
      if (expiresAfterWrite()) {
        removeNodes(writeOrderDeque(), now);
      }
      data.values().forEach(node -> removeNode(node, now));

      // Discard all pending reads
      readBuffer.drainTo(e -> {});
    } finally {
      evictionLock.unlock();
    }
  }

  @GuardedBy("evictionLock")
  void removeNodes(LinkedDeque<Node<K, V>> deque, long now) {
    Node<K, V> node;
    while ((node = deque.peek()) != null) {
      removeNode(node, now);
      deque.poll();
    }
  }

  @GuardedBy("evictionLock")
  void removeNode(Node<K, V> node, long now) {
    K key = node.getKey();
    V value = node.getValue();
    boolean[] removed = new boolean[1];
    RemovalCause cause;
    if ((key == null) || (value == null)) {
      cause = RemovalCause.COLLECTED;
    } else if (hasExpired(node, now)) {
      cause = RemovalCause.EXPIRED;
    } else {
      cause = RemovalCause.EXPLICIT;
    }

    data.computeIfPresent(node.getKeyReference(), (k, n) -> {
      if (n == node) {
        writer.delete(key, value, cause);
        removed[0] = true;
        return null;
      }
      return n;
    });

    if (removed[0] && hasRemovalListener()) {
      notifyRemoval(key, value, cause);
    }

    makeDead(node);
  }

  @Override
  public boolean containsKey(Object key) {
    Node<K, V> node = data.get(nodeFactory.newLookupKey(key));
    return (node != null) && (node.getValue() != null)
        && !hasExpired(node, expirationTicker().read());
  }

  @Override
  public boolean containsValue(Object value) {
    requireNonNull(value);

    long now = expirationTicker().read();
    for (Node<K, V> node : data.values()) {
      if (node.containsValue(value) && !hasExpired(node, now) && (node.getKey() != null)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public V get(Object key) {
    return getIfPresent(key, /* recordStats */ false);
  }

  @Override
  public V getIfPresent(Object key, boolean recordStats) {
    Node<K, V> node = data.get(nodeFactory.newLookupKey(key));
    if (node == null) {
      if (recordStats) {
        statsCounter().recordMisses(1);
      }
      return null;
    }
    long now = expirationTicker().read();
    if (hasExpired(node, now)) {
      if (recordStats) {
        statsCounter().recordMisses(1);
      }
      scheduleDrainBuffers();
      return null;
    }
    afterRead(node, now, recordStats);
    return node.getValue();
  }

  @Override
  public V getIfPresentQuietly(Object key, long[/* 1 */] writeTime) {
    V value;
    Node<K, V> node = data.get(nodeFactory.newLookupKey(key));
    if ((node == null) || ((value = node.getValue()) == null)
        || hasExpired(node, expirationTicker().read())) {
      return null;
    }
    writeTime[0] = node.getWriteTime();
    return value;
  }

  @Override
  public Map<K, V> getAllPresent(Iterable<?> keys) {
    int misses = 0;
    long now = expirationTicker().read();
    Map<K, V> result = new LinkedHashMap<>();
    for (Object key : keys) {
      Node<K, V> node = data.get(nodeFactory.newLookupKey(key));
      if ((node == null) || hasExpired(node, now)) {
        misses++;
        continue;
      }
      @SuppressWarnings("unchecked")
      K castKey = (K) key;
      V value = node.getValue();

      if (value != null) {
        result.put(castKey, value);
        afterRead(node, now, /* recordHit */ false);
      }
    }
    statsCounter().recordMisses(misses);
    statsCounter().recordHits(result.size());
    return Collections.unmodifiableMap(result);
  }

  @Override
  public V put(K key, V value) {
    int weight = weigher.weigh(key, value);
    return (weight > 0)
        ? putFast(key, value, weight, /* notifyWriter */ true, /* onlyIfAbsent */ false)
        : putSlow(key, value, weight, /* notifyWriter */ true, /* onlyIfAbsent */ false);
  }

  @Override
  public V put(K key, V value, boolean notifyWriter) {
    int weight = weigher.weigh(key, value);
    return (weight > 0)
        ? putFast(key, value, weight, notifyWriter, /* onlyIfAbsent */ false)
        : putSlow(key, value, weight, notifyWriter, /* onlyIfAbsent */ false);
  }

  @Override
  public V putIfAbsent(K key, V value) {
    int weight = weigher.weigh(key, value);
    return (weight > 0)
        ? putFast(key, value, weight, /* notifyWriter */ true, /* onlyIfAbsent */ true)
        : putSlow(key, value, weight, /* notifyWriter */ true, /* onlyIfAbsent */ true);
  }

  /**
   * Adds a node to the policy and the data store. If an existing node is found, then its value is
   * updated if allowed.
   *
   * This implementation is optimized for writing values with a non-zero weight. A zero weight is
   * incompatible due to the potential for the update to race with eviction, where the entry should
   * no longer be eligible if the update was successful. This implementation is ~50% faster than
   * {@link #putSlow} due to not incurring the penalty of a compute and lambda in the common case.
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @param notifyWriter if the writer should be notified for an inserted or updated entry
   * @param onlyIfAbsent a write is performed only if the key is not already associated with a value
   * @return the prior value in or null if no mapping was found
   */
  V putFast(K key, V value, int newWeight, boolean notifyWriter, boolean onlyIfAbsent) {
    requireNonNull(key);
    requireNonNull(value);
    requireState(newWeight != 0);

    Node<K, V> node = null;
    long now = expirationTicker().read();
    for (;;) {
      Node<K, V> prior = data.get(nodeFactory.newLookupKey(key));
      if (prior == null) {
        if (node == null) {
          node = nodeFactory.newNode(key, keyReferenceQueue(),
              value, valueReferenceQueue(), newWeight, now);
        }
        if (notifyWriter && hasWriter()) {
          Node<K, V> computed = node;
          prior = data.computeIfAbsent(node.getKeyReference(), k -> {
            writer.write(key, value);
            return computed;
          });
          if (prior == node) {
            afterWrite(node, new AddTask(node, newWeight), now);
            return null;
          }
        } else {
          prior = data.putIfAbsent(node.getKeyReference(), node);
          if (prior == null) {
            afterWrite(node, new AddTask(node, newWeight), now);
            return null;
          }
        }
      }

      V oldValue;
      int oldWeight;
      boolean expired = false;
      boolean mayUpdate = true;
      synchronized (prior) {
        if (!prior.isAlive()) {
          continue;
        }
        oldValue = prior.getValue();
        oldWeight = prior.getWeight();
        if (oldValue == null) {
          writer.delete(key, null, RemovalCause.COLLECTED);
        } else if (hasExpired(prior, now)) {
          writer.delete(key, oldValue, RemovalCause.EXPIRED);
          expired = true;
        } else if (onlyIfAbsent) {
          mayUpdate = false;
        }

        if (notifyWriter && (expired || (mayUpdate && (value != oldValue)))) {
          writer.write(key, value);
        }
        if (mayUpdate) {
          prior.setValue(value, valueReferenceQueue());
          prior.setWeight(newWeight);
        }
      }

      if (hasRemovalListener()) {
        if (expired) {
          notifyRemoval(key, oldValue, RemovalCause.EXPIRED);
        } else if (oldValue == null) {
          notifyRemoval(key, oldValue, RemovalCause.COLLECTED);
        } else if (mayUpdate && (value != oldValue)) {
          notifyRemoval(key, oldValue, RemovalCause.REPLACED);
        }
      }

      int weightedDifference = mayUpdate ? (newWeight - oldWeight) : 0;
      if ((oldValue == null) || (weightedDifference != 0) || expired) {
        afterWrite(prior, new UpdateTask(prior, weightedDifference), now);
      } else if (!onlyIfAbsent && expiresAfterWrite()
          && ((now - prior.getWriteTime()) > EXPIRE_WRITE_TOLERANCE)) {
        afterWrite(prior, new UpdateTask(prior, weightedDifference), now);
      } else {
        if (!onlyIfAbsent) {
          prior.setWriteTime(now);
        }
        afterRead(prior, now, /* recordHit */ false);
      }

      return expired ? null : oldValue;
    }
  }

  /**
   * Adds a node to the policy and the data store. If an existing node is found, then its value is
   * updated if allowed.
   *
   * This implementation is strict by using a compute to block other writers to that entry. This
   * guards against an eviction trying to discard an entry concurrently (and successfully) updated
   * to have a zero weight. The penalty is 50% of the throughput when compared to {@link #putFast}.
   *
   * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @param notifyWriter if the writer should be notified for an inserted or updated entry
   * @param onlyIfAbsent a write is performed only if the key is not already associated with a value
   * @return the prior value or null if no mapping was found
   */
  V putSlow(K key, V value, int newWeight, boolean notifyWriter, boolean onlyIfAbsent) {
    requireNonNull(key);
    requireNonNull(value);

    @SuppressWarnings("unchecked")
    K[] nodeKey = (K[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    @SuppressWarnings({"unchecked", "rawtypes"})
    RemovalCause[] cause = new RemovalCause[1];
    long now = expirationTicker().read();

    int[] oldWeight = new int[1];
    Object keyRef = nodeFactory.newReferenceKey(key, keyReferenceQueue());
    Node<K, V> node = data.compute(keyRef, (kr, n) -> {
      if (n == null) {
        if (notifyWriter) {
          writer.write(key, value);
        }
        return nodeFactory.newNode(kr, value, valueReferenceQueue(), newWeight, now);
      }

      synchronized (n) {
        nodeKey[0] = n.getKey();
        oldValue[0] = n.getValue();
        oldWeight[0] = n.getWeight();
        if ((nodeKey[0] == null) || (oldValue[0] == null)) {
          cause[0] = RemovalCause.COLLECTED;
        } else if (hasExpired(n, now)) {
          cause[0] = RemovalCause.EXPIRED;
        }
        if (cause[0] != null) {
          writer.delete(nodeKey[0], oldValue[0], cause[0]);
        } else if (onlyIfAbsent && (oldValue[0] != null)) {
          return n;
        }

        if (value != oldValue[0]) {
          if (cause[0] == null) {
            cause[0] = RemovalCause.REPLACED;
          }
          if (notifyWriter) {
            writer.write(key, value);
          }
        }

        n.setValue(value, valueReferenceQueue());
        n.setWeight(newWeight);
        n.setAccessTime(now);
        n.setWriteTime(now);
        return n;
      }
    });

    if (cause[0] != null) {
      if (cause[0].wasEvicted()) {
        statsCounter().recordEviction(oldWeight[0]);
      }
      if (hasRemovalListener()) {
        notifyRemoval(nodeKey[0], oldValue[0], cause[0]);
      }
    }

    if ((oldValue[0] == null) && (cause[0] == null)) {
      afterWrite(node, new AddTask(node, newWeight), now);
    } else if (onlyIfAbsent && (oldValue[0] != null) && (cause[0] == null)) {
      afterRead(node, now, /* recordHit */ false);
    } else {
      int weightedDifference = newWeight - oldWeight[0];
      if (expiresAfterWrite() || (oldValue[0] == null) || (weightedDifference != 0)
          || ((cause[0] != null) && (cause[0] != RemovalCause.REPLACED))) {
        afterWrite(node, new UpdateTask(node, weightedDifference), now);
      } else {
        afterRead(node, now, /* recordHit */ false);
      }
    }

    return (cause[0] == null) || (cause[0] == RemovalCause.REPLACED) ? oldValue[0] : null;
  }

  @Override
  public V remove(Object key) {
    return hasWriter()
        ? removeWithWriter(key)
        : removeNoWriter(key);
  }

  /**
   * Removes the mapping for a key without notifying the writer.
   *
   * @param key key whose mapping is to be removed
   * @return the removed value or null if no mapping was found
   */
  V removeNoWriter(Object key) {
    Node<K, V> node;
    Object lookupKey = nodeFactory.newLookupKey(key);
    if (!data.containsKey(lookupKey) || ((node = data.remove(lookupKey)) == null)) {
      return null;
    }

    V oldValue;
    synchronized (node) {
      oldValue = node.getValue();
      if (node.isAlive()) {
        node.retire();
      }
    }

    RemovalCause cause;
    if (oldValue == null) {
      cause = RemovalCause.COLLECTED;
    } else if (hasExpired(node, expirationTicker().read())) {
      cause = RemovalCause.EXPIRED;
    } else {
      cause = RemovalCause.EXPLICIT;
    }

    if (hasRemovalListener()) {
      @SuppressWarnings("unchecked")
      K castKey = (K) key;
      notifyRemoval(castKey, oldValue, cause);
    }
    afterWrite(node, new RemovalTask(node), 0L);
    return (cause == RemovalCause.EXPLICIT) ? oldValue : null;
  }

  /**
   * Removes the mapping for a key after notifying the writer.
   *
   * @param key key whose mapping is to be removed
   * @return the removed value or null if no mapping was found
   */
  V removeWithWriter(Object key) {
    @SuppressWarnings("unchecked")
    K castKey = (K) key;
    @SuppressWarnings({"unchecked", "rawtypes"})
    Node<K, V>[] node = new Node[1];
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    long now = expirationTicker().read();
    RemovalCause[] cause = new RemovalCause[1];

    data.computeIfPresent(nodeFactory.newLookupKey(key), (k, n) -> {
      synchronized (n) {
        oldValue[0] = n.getValue();
        if (oldValue[0] == null) {
          cause[0] = RemovalCause.COLLECTED;
        } else if (hasExpired(n, now)) {
          cause[0] = RemovalCause.EXPIRED;
        } else {
          cause[0] = RemovalCause.EXPLICIT;
        }
        writer.delete(castKey, oldValue[0], cause[0]);
        n.retire();
      }
      node[0] = n;
      return null;
    });

    if (cause[0] != null) {
      afterWrite(node[0], new RemovalTask(node[0]), now);
      if (hasRemovalListener()) {
        notifyRemoval(castKey, oldValue[0], cause[0]);
      }
    }
    return (cause[0] == RemovalCause.EXPLICIT) ? oldValue[0] : null;
  }

  @Override
  public boolean remove(Object key, Object value) {
    requireNonNull(key);

    Object lookupKey = nodeFactory.newLookupKey(key);
    if ((value == null) || !data.containsKey(lookupKey)) {
      return false;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    Node<K, V> removed[] = new Node[1];
    @SuppressWarnings("unchecked")
    K[] oldKey = (K[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    RemovalCause[] cause = new RemovalCause[1];

    long now = expirationTicker().read();
    data.computeIfPresent(lookupKey, (kR, node) -> {
      synchronized (node) {
        oldKey[0] = node.getKey();
        oldValue[0] = node.getValue();
        if (oldKey[0] == null) {
          cause[0] = RemovalCause.COLLECTED;
        } else if (hasExpired(node, now)) {
          cause[0] = RemovalCause.EXPIRED;
        } else if (node.containsValue(value)) {
          cause[0] = RemovalCause.EXPLICIT;
        } else {
          return node;
        }
        writer.delete(oldKey[0], oldValue[0], cause[0]);
        removed[0] = node;
        node.retire();
        return null;
      }
    });

    if (removed[0] == null) {
      return false;
    } else if (hasRemovalListener()) {
      notifyRemoval(oldKey[0], oldValue[0], cause[0]);
    }
    afterWrite(removed[0], new RemovalTask(removed[0]), now);
    return (cause[0] == RemovalCause.EXPLICIT);
  }

  @Override
  public V replace(K key, V value) {
    requireNonNull(key);
    requireNonNull(value);

    int[] oldWeight = new int[1];
    @SuppressWarnings("unchecked")
    K[] nodeKey = (K[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    long now = expirationTicker().read();
    int weight = weigher.weigh(key, value);
    Node<K, V> node = data.computeIfPresent(nodeFactory.newLookupKey(key), (k, n) -> {
      synchronized (n) {
        nodeKey[0] = n.getKey();
        oldValue[0] = n.getValue();
        oldWeight[0] = n.getWeight();
        if ((nodeKey[0] == null) || (oldValue[0] == null) || hasExpired(n, now)) {
          oldValue[0] = null;
          return n;
        }

        if (value != oldValue[0]) {
          writer.write(nodeKey[0], value);
        }
        n.setValue(value, valueReferenceQueue());
        n.setWriteTime(now);
        n.setWeight(weight);
        return n;
      }
    });

    if (oldValue[0] == null) {
      return null;
    }

    int weightedDifference = (weight - oldWeight[0]);
    if (expiresAfterWrite() || (weightedDifference != 0)) {
      afterWrite(node, new UpdateTask(node, weightedDifference), now);
    } else {
      afterRead(node, now, /* recordHit */ false);
    }

    if (hasRemovalListener() && (value != oldValue[0])) {
      notifyRemoval(nodeKey[0], oldValue[0], RemovalCause.REPLACED);
    }
    return oldValue[0];
  }

  @Override
  public boolean replace(K key, V oldValue, V newValue) {
    requireNonNull(key);
    requireNonNull(oldValue);
    requireNonNull(newValue);

    int weight = weigher.weigh(key, newValue);
    boolean[] replaced = new boolean[1];
    @SuppressWarnings("unchecked")
    K[] nodeKey = (K[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] prevValue = (V[]) new Object[1];
    int[] oldWeight = new int[1];
    long now = expirationTicker().read();
    Node<K, V> node = data.computeIfPresent(nodeFactory.newLookupKey(key), (k, n) -> {
      synchronized (n) {
        nodeKey[0] = n.getKey();
        prevValue[0] = n.getValue();
        oldWeight[0] = n.getWeight();
        if ((nodeKey[0] == null) || (prevValue[0] == null)
            || hasExpired(n, now) || !n.containsValue(oldValue)) {
          return n;
        }

        if (newValue != prevValue[0]) {
          writer.write(key, newValue);
        }
        n.setValue(newValue, valueReferenceQueue());
        n.setWeight(weight);
        n.setWriteTime(now);
        replaced[0] = true;
      }
      return n;
    });

    if (!replaced[0]) {
      return false;
    }

    int weightedDifference = (weight - oldWeight[0]);
    if (expiresAfterWrite() || (weightedDifference != 0)) {
      afterWrite(node, new UpdateTask(node, weightedDifference), now);
    } else {
      afterRead(node, now, /* recordHit */ false);
    }

    if (hasRemovalListener() && (oldValue != newValue)) {
      notifyRemoval(nodeKey[0], prevValue[0], RemovalCause.REPLACED);
    }
    return true;
  }

  @Override
  public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
    requireNonNull(function);

    BiFunction<K, V, V> remappingFunction = (key, oldValue) -> {
      V newValue = requireNonNull(function.apply(key, oldValue));
      if (oldValue != newValue) {
        writer.write(key, newValue);
      }
      return newValue;
    };
    for (K key : keySet()) {
      long now = expirationTicker().read();
      Object lookupKey = nodeFactory.newLookupKey(key);
      remap(key, lookupKey, remappingFunction, now, /* computeIfAbsent */ false);
    }
  }

  @Override
  public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction,
      boolean recordStats, boolean recordLoad) {
    requireNonNull(key);
    requireNonNull(mappingFunction);
    long now = expirationTicker().read();

    // An optimistic fast path to avoid unnecessary locking
    Node<K, V> node = data.get(nodeFactory.newLookupKey(key));
    if (node != null) {
      V value = node.getValue();
      if ((value != null) && !hasExpired(node, now)) {
        afterRead(node, now, /* recordHit */ true);
        return value;
      }
    }
    if (recordStats) {
      mappingFunction = statsAware(mappingFunction, recordLoad);
    }
    Object keyRef = nodeFactory.newReferenceKey(key, keyReferenceQueue());
    return doComputeIfAbsent(key, keyRef, mappingFunction, now);
  }

  /** Returns the current value from a computeIfAbsent invocation. */
  V doComputeIfAbsent(K key, Object keyRef,
      Function<? super K, ? extends V> mappingFunction, long now) {
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] newValue = (V[]) new Object[1];
    @SuppressWarnings("unchecked")
    K[] nodeKey = (K[]) new Object[1];
    @SuppressWarnings({"unchecked", "rawtypes"})
    Node<K, V>[] removed = new Node[1];

    int[] weight = new int[2]; // old, new
    RemovalCause[] cause = new RemovalCause[1];
    Node<K, V> node = data.compute(keyRef, (k, n) -> {
      if (n == null) {
        newValue[0] = mappingFunction.apply(key);
        if (newValue[0] == null) {
          return null;
        }
        weight[1] = weigher.weigh(key, newValue[0]);
        return nodeFactory.newNode(key, keyReferenceQueue(),
            newValue[0], valueReferenceQueue(), weight[1], now);
      }

      synchronized (n) {
        nodeKey[0] = n.getKey();
        weight[0] = n.getWeight();
        oldValue[0] = n.getValue();
        if ((nodeKey[0] == null) || (oldValue[0] == null)) {
          cause[0] = RemovalCause.COLLECTED;
        } else if (hasExpired(n, now)) {
          cause[0] = RemovalCause.EXPIRED;
          n.setAccessTime(now);
          n.setWriteTime(now);
        } else {
          return n;
        }

        writer.delete(nodeKey[0], oldValue[0], cause[0]);
        newValue[0] = mappingFunction.apply(key);
        if (newValue[0] == null) {
          removed[0] = n;
          n.retire();
          return null;
        }
        weight[1] = weigher.weigh(key, newValue[0]);
        n.setValue(newValue[0], valueReferenceQueue());
        n.setWeight(weight[1]);
        return n;
      }
    });

    if (node == null) {
      if (removed[0] != null) {
        afterWrite(null, new RemovalTask(removed[0]), now);
      }
      return null;
    }
    if (cause[0] != null) {
      if (hasRemovalListener()) {
        notifyRemoval(nodeKey[0], oldValue[0], cause[0]);
      }
      statsCounter().recordEviction(weight[0]);
    }
    if (newValue[0] == null) {
      afterRead(node, now, /* recordHit */ true);
      return oldValue[0];
    }
    if ((oldValue[0] == null) && (cause[0] == null)) {
      afterWrite(node, new AddTask(node, weight[1]), now);
    } else {
      int weightedDifference = (weight[1] - weight[0]);
      afterWrite(node, new UpdateTask(node, weightedDifference), now);
    }

    return newValue[0];
  }

  @Override
  public V computeIfPresent(K key,
      BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
    requireNonNull(key);
    requireNonNull(remappingFunction);

    // A optimistic fast path to avoid unnecessary locking
    Object lookupKey = nodeFactory.newLookupKey(key);
    Node<K, V> node = data.get(lookupKey);
    long now;
    if ((node == null) || (node.getValue() == null)
        || hasExpired(node, (now = expirationTicker().read()))) {
      scheduleDrainBuffers();
      return null;
    }

    boolean computeIfAbsent = false;
    BiFunction<? super K, ? super V, ? extends V> statsAwareRemappingFunction =
        statsAware(remappingFunction, /* recordMiss */ false, /* recordLoad */ true);
    return remap(key, lookupKey, statsAwareRemappingFunction, now, computeIfAbsent);
  }

  @Override
  public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction,
      boolean recordMiss, boolean recordLoad) {
    requireNonNull(key);
    requireNonNull(remappingFunction);

    long now = expirationTicker().read();
    boolean computeIfAbsent = true;
    Object keyRef = nodeFactory.newReferenceKey(key, keyReferenceQueue());
    BiFunction<? super K, ? super V, ? extends V> statsAwareRemappingFunction =
        statsAware(remappingFunction, recordMiss, recordLoad);
    return remap(key, keyRef, statsAwareRemappingFunction, now, computeIfAbsent);
  }

  @Override
  public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    requireNonNull(key);
    requireNonNull(value);
    requireNonNull(remappingFunction);

    boolean computeIfAbsent = true;
    long now = expirationTicker().read();
    Object keyRef = nodeFactory.newReferenceKey(key, keyReferenceQueue());
    BiFunction<? super K, ? super V, ? extends V> mergeFunction = (k, oldValue) ->
        (oldValue == null) ? value : statsAware(remappingFunction).apply(oldValue, value);
    return remap(key, keyRef, mergeFunction, now, computeIfAbsent);
  }

  /**
   * Attempts to compute a mapping for the specified key and its current mapped value (or
   * {@code null} if there is no current mapping).
   * <p>
   * An entry that has expired or been reference collected is evicted and the computation continues
   * as if the entry had not been present. This method does not pre-screen and does not wrap the
   * remappingFuntion to be statistics aware.
   *
   * @param key key with which the specified value is to be associated
   * @param keyRef the key to associate with or a lookup only key if not <tt>computeIfAbsent</tt>
   * @param remappingFunction the function to compute a value
   * @param now the current time, according to the ticker
   * @param computeIfAbsent if an absent entry can be computed
   * @return the new value associated with the specified key, or null if none
   */
  @SuppressWarnings("PMD.EmptyIfStmt")
  V remap(K key, Object keyRef, BiFunction<? super K, ? super V, ? extends V> remappingFunction,
      long now, boolean computeIfAbsent) {
    @SuppressWarnings("unchecked")
    K[] nodeKey = (K[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] oldValue = (V[]) new Object[1];
    @SuppressWarnings("unchecked")
    V[] newValue = (V[]) new Object[1];
    @SuppressWarnings({"unchecked", "rawtypes"})
    Node<K, V>[] removed = new Node[1];

    int[] weight = new int[2]; // old, new
    RemovalCause[] cause = new RemovalCause[1];

    Node<K, V> node = data.compute(keyRef, (kr, n) -> {
      if (n == null) {
        if (!computeIfAbsent) {
          return null;
        }
        newValue[0] = remappingFunction.apply(key, null);
        if (newValue[0] == null) {
          return null;
        }
        weight[1] = weigher.weigh(key, newValue[0]);
        return nodeFactory.newNode(keyRef, newValue[0],
            valueReferenceQueue(), weight[1], now);
      }

      synchronized (n) {
        nodeKey[0] = n.getKey();
        oldValue[0] = n.getValue();
        if ((nodeKey[0] == null) || (oldValue[0] == null)) {
          cause[0] = RemovalCause.COLLECTED;
        } else if (hasExpired(n, now)) {
          cause[0] = RemovalCause.EXPIRED;
        }
        if (cause[0] != null) {
          writer.delete(nodeKey[0], oldValue[0], cause[0]);
          if (!computeIfAbsent) {
            removed[0] = n;
            n.retire();
            return null;
          }
        }

        newValue[0] = remappingFunction.apply(nodeKey[0],
            (cause[0] == null) ? oldValue[0] : null);
        if (newValue[0] == null) {
          if (cause[0] == null) {
            cause[0] = RemovalCause.EXPLICIT;
          }
          removed[0] = n;
          n.retire();
          return null;
        }

        weight[0] = n.getWeight();
        weight[1] = weigher.weigh(key, newValue[0]);
        n.setValue(newValue[0], valueReferenceQueue());
        n.setWeight(weight[1]);
        n.setWriteTime(now);
        n.setAccessTime(now);
        if ((cause[0] == null) && (newValue[0] != oldValue[0])) {
          cause[0] = RemovalCause.REPLACED;
        }
        return n;
      }
    });

    if (cause[0] != null) {
      if (cause[0].wasEvicted()) {
        statsCounter().recordEviction(weight[0]);
      }
      if (hasRemovalListener()) {
        notifyRemoval(nodeKey[0], oldValue[0], cause[0]);
      }
    }

    if (removed[0] != null) {
      afterWrite(removed[0], new RemovalTask(removed[0]), now);
    } else if (node == null) {
      // absent and not computable
    } else if ((oldValue[0] == null) && (cause[0] == null)) {
      afterWrite(node, new AddTask(node, weight[1]), now);
    } else {
      int weightedDifference = weight[1] - weight[0];
      if (expiresAfterWrite() || (weightedDifference != 0)) {
        afterWrite(node, new UpdateTask(node, weightedDifference), now);
      } else {
        afterRead(node, now, /* recordHit */ false);
        if (cause[0] == RemovalCause.COLLECTED) {
          scheduleDrainBuffers();
        }
      }
    }

    return newValue[0];
  }

  @Override
  public Set<K> keySet() {
    final Set<K> ks = keySet;
    return (ks == null) ? (keySet = new KeySetView<>(this)) : ks;
  }

  @Override
  public Collection<V> values() {
    final Collection<V> vs = values;
    return (vs == null) ? (values = new ValuesView<>(this)) : vs;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    final Set<Entry<K, V>> es = entrySet;
    return (es == null) ? (entrySet = new EntrySetView<>(this)) : es;
  }

  /**
   * Returns an unmodifiable snapshot map ordered in eviction order, either ascending or descending.
   * Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
   *
   * @param limit the maximum number of entries
   * @param transformer a function that unwraps the value
   * @param hottest the iteration order
   * @return an unmodifiable snapshot in a specified order
   */
  @SuppressWarnings("GuardedByChecker")
  Map<K, V> evictionOrder(int limit, Function<V, V> transformer, boolean hottest) {
    Supplier<Iterator<Node<K, V>>> iteratorSupplier = () -> {
      Comparator<Node<K, V>> comparator = Comparator.comparingInt(node -> {
          K key = node.getKey();
          return (key == null) ? 0 : frequencySketch().frequency(key);
      });
      if (hottest) {
        PeekingIterator<Node<K, V>> secondary = PeekingIterator.comparing(
            accessOrderProbationDeque().descendingIterator(),
            accessOrderEdenDeque().descendingIterator(), comparator);
        return PeekingIterator.concat(accessOrderProtectedDeque().descendingIterator(), secondary);
      } else {
        PeekingIterator<Node<K, V>> primary = PeekingIterator.comparing(
            accessOrderEdenDeque().iterator(), accessOrderProbationDeque().iterator(),
            comparator.reversed());
        return PeekingIterator.concat(primary, accessOrderProtectedDeque().iterator());
      }
    };
    return snapshot(iteratorSupplier, transformer, limit);
  }

  /**
   * Returns an unmodifiable snapshot map ordered in access expiration order, either ascending or
   * descending. Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
   *
   * @param limit the maximum number of entries
   * @param transformer a function that unwraps the value
   * @param oldest the iteration order
   * @return an unmodifiable snapshot in a specified order
   */
  @SuppressWarnings("GuardedByChecker")
  Map<K, V> expireAfterAcessOrder(int limit, Function<V, V> transformer, boolean oldest) {
    if (!evicts()) {
      Supplier<Iterator<Node<K, V>>> iteratorSupplier = () -> oldest
          ? accessOrderEdenDeque().iterator()
          : accessOrderEdenDeque().descendingIterator();
      return snapshot(iteratorSupplier, transformer, limit);
    }

    Supplier<Iterator<Node<K, V>>> iteratorSupplier = () -> {
        Comparator<Node<K, V>> comparator = Comparator.comparingLong(Node::getAccessTime);
        PeekingIterator<Node<K, V>> first, second, third;
        if (oldest) {
          first = accessOrderEdenDeque().iterator();
          second = accessOrderProbationDeque().iterator();
          third = accessOrderProtectedDeque().iterator();
        } else {
          comparator = comparator.reversed();
          first = accessOrderEdenDeque().descendingIterator();
          second = accessOrderProbationDeque().descendingIterator();
          third = accessOrderProtectedDeque().descendingIterator();
        }
        return PeekingIterator.comparing(
            PeekingIterator.comparing(first, second, comparator), third, comparator);
    };
    return snapshot(iteratorSupplier, transformer, limit);
  }

  /**
   * Returns an unmodifiable snapshot map ordered in write expiration order, either ascending or
   * descending. Beware that obtaining the mappings is <em>NOT</em> a constant-time operation.
   *
   * @param limit the maximum number of entries
   * @param transformer a function that unwraps the value
   * @param oldest the iteration order
   * @return an unmodifiable snapshot in a specified order
   */
  @SuppressWarnings("GuardedByChecker")
  Map<K, V> expireAfterWriteOrder(int limit, Function<V, V> transformer, boolean oldest) {
    Supplier<Iterator<Node<K, V>>> iteratorSupplier = () -> oldest
        ? writeOrderDeque().iterator()
        : writeOrderDeque().descendingIterator();
    return snapshot(iteratorSupplier, transformer, limit);
  }

  /**
   * Returns an unmodifiable snapshot map ordered by the provided iterator. Beware that obtaining
   * the mappings is <em>NOT</em> a constant-time operation.
   *
   * @param iteratorSupplier the iterator
   * @param limit the maximum number of entries
   * @param transformer a function that unwraps the value
   * @return an unmodifiable snapshot in the iterator's order
   */
  Map<K, V> snapshot(Supplier<Iterator<Node<K, V>>> iteratorSupplier,
      Function<V, V> transformer, int limit) {
    requireArgument(limit >= 0);
    evictionLock.lock();
    try {
      maintenance(/* ignored */ null);

      int initialCapacity =
          isWeighted() ? 16 : Math.min(limit, evicts() ? (int) adjustedWeightedSize() : size());
      Iterator<Node<K, V>> iterator = iteratorSupplier.get();
      Map<K, V> map = new LinkedHashMap<>(initialCapacity);
      while ((map.size() < limit) && iterator.hasNext()) {
        Node<K, V> node = iterator.next();
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && node.isAlive()) {
          map.put(key, transformer.apply(value));
        }
      }
      return Collections.unmodifiableMap(map);
    } finally {
      evictionLock.unlock();
    }
  }

  /** An adapter to safely externalize the keys. */
  static final class KeySetView<K, V> extends AbstractSet<K> {
    final BoundedLocalCache<K, V> cache;

    KeySetView(BoundedLocalCache<K, V> cache) {
      this.cache = requireNonNull(cache);
    }

    @Override
    public int size() {
      return cache.size();
    }

    @Override
    public void clear() {
      cache.clear();
    }

    @Override
    public boolean contains(Object obj) {
      return cache.containsKey(obj);
    }

    @Override
    public boolean remove(Object obj) {
      return (cache.remove(obj) != null);
    }

    @Override
    public Iterator<K> iterator() {
      return new KeyIterator<>(cache);
    }

    @Override
    public Spliterator<K> spliterator() {
      return new KeySpliterator<>(cache);
    }

    @Override
    public Object[] toArray() {
      if (cache.collectKeys()) {
        List<Object> keys = new ArrayList<>(size());
        for (Object key : this) {
          keys.add(key);
        }
        return keys.toArray();
      }
      return cache.data.keySet().toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
      if (cache.collectKeys()) {
        List<Object> keys = new ArrayList<>(size());
        for (Object key : this) {
          keys.add(key);
        }
        return keys.toArray(array);
      }
      return cache.data.keySet().toArray(array);
    }
  }

  /** An adapter to safely externalize the key iterator. */
  static final class KeyIterator<K, V> implements Iterator<K> {
    final Iterator<Entry<K, V>> iterator;
    final BoundedLocalCache<K, V> cache;
    K current;

    KeyIterator(BoundedLocalCache<K, V> cache) {
      this.iterator = cache.entrySet().iterator();
      this.cache = cache;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public K next() {
      K next = iterator.next().getKey();
      current = next;
      return current;
    }

    @Override
    public void remove() {
      requireState(current != null);
      cache.remove(current);
      current = null;
    }
  }

  /** An adapter to safely externalize the key spliterator. */
  static final class KeySpliterator<K, V> implements Spliterator<K> {
    final Spliterator<Node<K, V>> spliterator;
    final BoundedLocalCache<K, V> cache;

    KeySpliterator(BoundedLocalCache<K, V> cache) {
      this(cache, cache.data.values().spliterator());
    }

    KeySpliterator(BoundedLocalCache<K, V> cache, Spliterator<Node<K, V>> spliterator) {
      this.spliterator = requireNonNull(spliterator);
      this.cache = requireNonNull(cache);
    }

    @Override
    public void forEachRemaining(Consumer<? super K> action) {
      requireNonNull(action);
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(key);
        }
      };
      spliterator.forEachRemaining(consumer);
    }

    @Override
    public boolean tryAdvance(Consumer<? super K> action) {
      requireNonNull(action);
      boolean[] advanced = { false };
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(key);
          advanced[0] = true;
        }
      };
      for (;;) {
        if (spliterator.tryAdvance(consumer)) {
          if (advanced[0]) {
            return true;
          }
          continue;
        }
        return false;
      }
    }

    @Override
    public Spliterator<K> trySplit() {
      Spliterator<Node<K, V>> split = spliterator.trySplit();
      return (split == null) ? null : new KeySpliterator<>(cache, split);
    }

    @Override
    public long estimateSize() {
      return spliterator.estimateSize();
    }

    @Override
    public int characteristics() {
      return Spliterator.DISTINCT | Spliterator.CONCURRENT | Spliterator.NONNULL;
    }
  }

  /** An adapter to safely externalize the values. */
  static final class ValuesView<K, V> extends AbstractCollection<V> {
    final BoundedLocalCache<K, V> cache;

    ValuesView(BoundedLocalCache<K, V> cache) {
      this.cache = requireNonNull(cache);
    }

    @Override
    public int size() {
      return cache.size();
    }

    @Override
    public void clear() {
      cache.clear();
    }

    @Override
    public boolean contains(Object o) {
      return cache.containsValue(o);
    }

    @Override
    public boolean removeIf(Predicate<? super V> filter) {
      requireNonNull(filter);
      boolean removed = false;
      for (Entry<K, V> entry : cache.entrySet()) {
        if (filter.test(entry.getValue())) {
          removed |= cache.remove(entry.getKey(), entry.getValue());
        }
      }
      return removed;
    }

    @Override
    public Iterator<V> iterator() {
      return new ValueIterator<>(cache);
    }

    @Override
    public Spliterator<V> spliterator() {
      return new ValueSpliterator<>(cache);
    }
  }

  /** An adapter to safely externalize the value iterator. */
  static final class ValueIterator<K, V> implements Iterator<V> {
    final Iterator<Entry<K, V>> iterator;

    ValueIterator(BoundedLocalCache<K, V> cache) {
      this.iterator = cache.entrySet().iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public V next() {
      return iterator.next().getValue();
    }

    @Override
    public void remove() {
      iterator.remove();
    }
  }

  /** An adapter to safely externalize the value spliterator. */
  static final class ValueSpliterator<K, V> implements Spliterator<V> {
    final Spliterator<Node<K, V>> spliterator;
    final BoundedLocalCache<K, V> cache;

    ValueSpliterator(BoundedLocalCache<K, V> cache) {
      this(cache, cache.data.values().spliterator());
    }

    ValueSpliterator(BoundedLocalCache<K, V> cache, Spliterator<Node<K, V>> spliterator) {
      this.spliterator = requireNonNull(spliterator);
      this.cache = requireNonNull(cache);
    }

    @Override
    public void forEachRemaining(Consumer<? super V> action) {
      requireNonNull(action);
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(value);
        }
      };
      spliterator.forEachRemaining(consumer);
    }

    @Override
    public boolean tryAdvance(Consumer<? super V> action) {
      requireNonNull(action);
      boolean[] advanced = { false };
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(value);
          advanced[0] = true;
        }
      };
      for (;;) {
        if (spliterator.tryAdvance(consumer)) {
          if (advanced[0]) {
            return true;
          }
          continue;
        }
        return false;
      }
    }

    @Override
    public Spliterator<V> trySplit() {
      Spliterator<Node<K, V>> split = spliterator.trySplit();
      return (split == null) ? null : new ValueSpliterator<>(cache, split);
    }

    @Override
    public long estimateSize() {
      return spliterator.estimateSize();
    }

    @Override
    public int characteristics() {
      return Spliterator.CONCURRENT | Spliterator.NONNULL;
    }
  }

  /** An adapter to safely externalize the entries. */
  static final class EntrySetView<K, V> extends AbstractSet<Entry<K, V>> {
    final BoundedLocalCache<K, V> cache;

    EntrySetView(BoundedLocalCache<K, V> cache) {
      this.cache = requireNonNull(cache);
    }

    @Override
    public int size() {
      return cache.size();
    }

    @Override
    public void clear() {
      cache.clear();
    }

    @Override
    public boolean contains(Object obj) {
      if (!(obj instanceof Entry<?, ?>)) {
        return false;
      }
      Entry<?, ?> entry = (Entry<?, ?>) obj;
      Node<K, V> node = cache.data.get(cache.nodeFactory.newLookupKey(entry.getKey()));
      return (node != null) && Objects.equals(node.getValue(), entry.getValue());
    }

    @Override
    public boolean remove(Object obj) {
      if (!(obj instanceof Entry<?, ?>)) {
        return false;
      }
      Entry<?, ?> entry = (Entry<?, ?>) obj;
      return cache.remove(entry.getKey(), entry.getValue());
    }

    @Override
    public boolean removeIf(Predicate<? super Entry<K, V>> filter) {
      requireNonNull(filter);
      boolean removed = false;
      for (Entry<K, V> entry : this) {
        if (filter.test(entry)) {
          removed |= cache.remove(entry.getKey(), entry.getValue());
        }
      }
      return removed;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
      return new EntryIterator<>(cache);
    }

    @Override
    public Spliterator<Entry<K, V>> spliterator() {
      return new EntrySpliterator<>(cache);
    }
  }

  /** An adapter to safely externalize the entry iterator. */
  static final class EntryIterator<K, V> implements Iterator<Entry<K, V>> {
    final BoundedLocalCache<K, V> cache;
    final Iterator<Node<K, V>> iterator;
    final long now;

    K key;
    V value;
    K removalKey;
    Node<K, V> next;

    EntryIterator(BoundedLocalCache<K, V> cache) {
      this.iterator = cache.data.values().iterator();
      this.now = cache.expirationTicker().read();
      this.cache = cache;
    }

    @Override
    public boolean hasNext() {
      if (next != null) {
        return true;
      }
      for (;;) {
        if (iterator.hasNext()) {
          next = iterator.next();
          value = next.getValue();
          key = next.getKey();
          if (cache.hasExpired(next, now) || (key == null) || (value == null) || !next.isAlive()) {
            value = null;
            next = null;
            key = null;
            continue;
          }
          return true;
        }
        return false;
      }
    }

    @Override
    public Entry<K, V> next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Entry<K, V> entry = new WriteThroughEntry<>(cache, key, value);
      removalKey = key;
      value = null;
      next = null;
      key = null;
      return entry;
    }

    @Override
    public void remove() {
      requireState(removalKey != null);
      cache.remove(removalKey);
      removalKey = null;
    }
  }

  /** An adapter to safely externalize the entry spliterator. */
  static final class EntrySpliterator<K, V> implements Spliterator<Entry<K, V>> {
    final Spliterator<Node<K, V>> spliterator;
    final BoundedLocalCache<K, V> cache;

    EntrySpliterator(BoundedLocalCache<K, V> cache) {
      this(cache, cache.data.values().spliterator());
    }

    EntrySpliterator(BoundedLocalCache<K, V> cache, Spliterator<Node<K, V>> spliterator) {
      this.spliterator = requireNonNull(spliterator);
      this.cache = requireNonNull(cache);
    }

    @Override
    public void forEachRemaining(Consumer<? super Entry<K, V>> action) {
      requireNonNull(action);
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(new WriteThroughEntry<>(cache, key, value));
        }
      };
      spliterator.forEachRemaining(consumer);
    }

    @Override
    public boolean tryAdvance(Consumer<? super Entry<K, V>> action) {
      requireNonNull(action);
      boolean[] advanced = { false };
      long now = cache.expirationTicker().read();
      Consumer<Node<K, V>> consumer = node -> {
        K key = node.getKey();
        V value = node.getValue();
        if ((key != null) && (value != null) && !cache.hasExpired(node, now) && node.isAlive()) {
          action.accept(new WriteThroughEntry<>(cache, key, value));
          advanced[0] = true;
        }
      };
      for (;;) {
        if (spliterator.tryAdvance(consumer)) {
          if (advanced[0]) {
            return true;
          }
          continue;
        }
        return false;
      }
    }

    @Override
    public Spliterator<Entry<K, V>> trySplit() {
      Spliterator<Node<K, V>> split = spliterator.trySplit();
      return (split == null) ? null : new EntrySpliterator<>(cache, split);
    }

    @Override
    public long estimateSize() {
      return spliterator.estimateSize();
    }

    @Override
    public int characteristics() {
      return Spliterator.DISTINCT | Spliterator.CONCURRENT | Spliterator.NONNULL;
    }
  }

  /** A reusable task that performs the maintenance work; used to avoid wrapping by ForkJoinPool. */
  final class PerformCleanupTask extends ForkJoinTask<Void> implements Runnable {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean exec() {
      try {
        run();
      } catch (Throwable t) {
        logger.log(Level.SEVERE, "Exception thrown when performing the maintenance task", t);
      }

      // Indicates that the task has not completed to allow subsequent submissions to execute
      return false;
    }

    @Override
    public void run() {
      performCleanUp(/* ignored */ null);
    }

    /**
     * This method cannot be ignored due to being final, so a hostile user supplied Executor could
     * forcibly complete the task and halt future executions. There are easier ways to intentionally
     * harm a system, so this is assumed to not happen in practice.
     */
    // public final void quietlyComplete() {}

    @Override public Void getRawResult() { return null; }
    @Override public void setRawResult(Void v) {}
    @Override public void complete(Void value) {}
    @Override public void completeExceptionally(Throwable ex) {}
    @Override public boolean cancel(boolean mayInterruptIfRunning) { return false; }
  }

  /** Creates a serialization proxy based on the common configuration shared by all cache types. */
  static <K, V> SerializationProxy<K, V> makeSerializationProxy(
      BoundedLocalCache<?, ?> cache, boolean isWeighted) {
    SerializationProxy<K, V> proxy = new SerializationProxy<>();
    proxy.weakKeys = cache.collectKeys();
    proxy.weakValues = cache.nodeFactory.weakValues();
    proxy.softValues = cache.nodeFactory.softValues();
    proxy.isRecordingStats = cache.isRecordingStats();
    proxy.removalListener = cache.removalListener();
    proxy.ticker = cache.expirationTicker();
    proxy.writer = cache.writer;
    if (cache.expiresAfterAccess()) {
      proxy.expiresAfterAccessNanos = cache.expiresAfterAccessNanos();
    }
    if (cache.expiresAfterWrite()) {
      proxy.expiresAfterWriteNanos = cache.expiresAfterWriteNanos();
    }
    if (cache.evicts()) {
      if (isWeighted) {
        proxy.weigher = cache.weigher;
        proxy.maximumWeight = cache.maximum();
      } else {
        proxy.maximumSize = cache.maximum();
      }
    }
    return proxy;
  }

  /* ---------------- Manual Cache -------------- */

  static class BoundedLocalManualCache<K, V> implements
      LocalManualCache<BoundedLocalCache<K, V>, K, V>, Serializable {
    private static final long serialVersionUID = 1;

    final BoundedLocalCache<K, V> cache;
    final boolean isWeighted;
    Policy<K, V> policy;

    BoundedLocalManualCache(Caffeine<K, V> builder) {
      this(builder, null);
    }

    BoundedLocalManualCache(Caffeine<K, V> builder, CacheLoader<? super K, V> loader) {
      cache = LocalCacheFactory.newBoundedLocalCache(builder, loader, /* async */ false);
      isWeighted = builder.isWeighted();
    }

    @Override
    public BoundedLocalCache<K, V> cache() {
      return cache;
    }

    @Override
    public Policy<K, V> policy() {
      return (policy == null)
          ? (policy = new BoundedPolicy<>(cache, Function.identity(), isWeighted))
          : policy;
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
    }

    Object writeReplace() {
      return makeSerializationProxy(cache, isWeighted);
    }
  }

  static final class BoundedPolicy<K, V> implements Policy<K, V> {
    final BoundedLocalCache<K, V> cache;
    final Function<V, V> transformer;
    final boolean isWeighted;

    Optional<Eviction<K, V>> eviction;
    Optional<Expiration<K, V>> refreshes;
    Optional<Expiration<K, V>> afterWrite;
    Optional<Expiration<K, V>> afterAccess;

    BoundedPolicy(BoundedLocalCache<K, V> cache, Function<V, V> transformer, boolean isWeighted) {
      this.transformer = transformer;
      this.isWeighted = isWeighted;
      this.cache = cache;
    }

    @Override public boolean isRecordingStats() {
      return cache.isRecordingStats();
    }
    @Override public Optional<Eviction<K, V>> eviction() {
      return cache.evicts()
          ? (eviction == null) ? (eviction = Optional.of(new BoundedEviction())) : eviction
          : Optional.empty();
    }
    @Override public Optional<Expiration<K, V>> expireAfterAccess() {
      if (!cache.expiresAfterAccess()) {
        return Optional.empty();
      }
      return (afterAccess == null)
          ? (afterAccess = Optional.of(new BoundedExpireAfterAccess()))
          : afterAccess;
    }
    @Override public Optional<Expiration<K, V>> expireAfterWrite() {
      if (!cache.expiresAfterWrite()) {
        return Optional.empty();
      }
      return (afterWrite == null)
          ? (afterWrite = Optional.of(new BoundedExpireAfterWrite()))
          : afterWrite;
    }
    @Override
    public Optional<Expiration<K, V>> refreshAfterWrite() {
      if (!cache.refreshAfterWrite()) {
        return Optional.empty();
      }
      return (refreshes == null)
          ? (refreshes = Optional.of(new BoundedRefreshAfterWrite()))
          : refreshes;
    }

    final class BoundedEviction implements Eviction<K, V> {
      @Override public boolean isWeighted() {
        return isWeighted;
      }
      @Override public OptionalInt weightOf(@Nonnull K key) {
        requireNonNull(key);
        if (!isWeighted) {
          return OptionalInt.empty();
        }
        Node<K, V> node = cache.data.get(cache.nodeFactory.newLookupKey(key));
        if (node == null) {
          return OptionalInt.empty();
        }
        synchronized (node) {
          return OptionalInt.of(node.getWeight());
        }
      }
      @Override public OptionalLong weightedSize() {
        if (cache.evicts() && isWeighted()) {
          cache.evictionLock.lock();
          try {
            return OptionalLong.of(cache.adjustedWeightedSize());
          } finally {
            cache.evictionLock.unlock();
          }
        }
        return OptionalLong.empty();
      }
      @Override public long getMaximum() {
        return cache.maximum();
      }
      @Override public void setMaximum(long maximum) {
        cache.evictionLock.lock();
        try {
          cache.setMaximum(maximum);
          cache.maintenance(/* ignored */ null);
        } finally {
          cache.evictionLock.unlock();
        }
      }
      @Override public Map<K, V> coldest(int limit) {
        return cache.evictionOrder(limit, transformer, /* hottest */ false);
      }
      @Override public Map<K, V> hottest(int limit) {
        return cache.evictionOrder(limit, transformer, /* hottest */ true);
      }
    }

    final class BoundedExpireAfterAccess implements Expiration<K, V> {
      @Override public OptionalLong ageOf(K key, TimeUnit unit) {
        requireNonNull(key);
        requireNonNull(unit);
        Object lookupKey = cache.nodeFactory.newLookupKey(key);
        Node<?, ?> node = cache.data.get(lookupKey);
        if (node == null) {
          return OptionalLong.empty();
        }
        long age = cache.expirationTicker().read() - node.getAccessTime();
        return (age > cache.expiresAfterAccessNanos())
            ? OptionalLong.empty()
            : OptionalLong.of(unit.convert(age, TimeUnit.NANOSECONDS));
      }
      @Override public long getExpiresAfter(TimeUnit unit) {
        return unit.convert(cache.expiresAfterAccessNanos(), TimeUnit.NANOSECONDS);
      }
      @Override public void setExpiresAfter(long duration, TimeUnit unit) {
        requireArgument(duration >= 0);
        cache.setExpiresAfterAccessNanos(unit.toNanos(duration));
        cache.scheduleAfterWrite();
      }
      @Override public Map<K, V> oldest(int limit) {
        return cache.expireAfterAcessOrder(limit, transformer, /* oldest */ true);
      }
      @Override public Map<K, V> youngest(int limit) {
        return cache.expireAfterAcessOrder(limit, transformer, /* oldest */ false);
      }
    }

    final class BoundedExpireAfterWrite implements Expiration<K, V> {
      @Override public OptionalLong ageOf(K key, TimeUnit unit) {
        requireNonNull(key);
        requireNonNull(unit);
        Object lookupKey = cache.nodeFactory.newLookupKey(key);
        Node<?, ?> node = cache.data.get(lookupKey);
        if (node == null) {
          return OptionalLong.empty();
        }
        long age = cache.expirationTicker().read() - node.getWriteTime();
        return (age > cache.expiresAfterWriteNanos())
            ? OptionalLong.empty()
            : OptionalLong.of(unit.convert(age, TimeUnit.NANOSECONDS));
      }
      @Override public long getExpiresAfter(TimeUnit unit) {
        return unit.convert(cache.expiresAfterWriteNanos(), TimeUnit.NANOSECONDS);
      }
      @Override public void setExpiresAfter(long duration, TimeUnit unit) {
        requireArgument(duration >= 0);
        cache.setExpiresAfterWriteNanos(unit.toNanos(duration));
        cache.scheduleAfterWrite();
      }
      @Override public Map<K, V> oldest(int limit) {
        return cache.expireAfterWriteOrder(limit, transformer, /* oldest */ true);
      }
      @Override public Map<K, V> youngest(int limit) {
        return cache.expireAfterWriteOrder(limit, transformer, /* oldest */ false);
      }
    }

    final class BoundedRefreshAfterWrite implements Expiration<K, V> {
      @Override public OptionalLong ageOf(K key, TimeUnit unit) {
        requireNonNull(key);
        requireNonNull(unit);
        Object lookupKey = cache.nodeFactory.newLookupKey(key);
        Node<?, ?> node = cache.data.get(lookupKey);
        if (node == null) {
          return OptionalLong.empty();
        }
        long age = cache.expirationTicker().read() - node.getWriteTime();
        return (age > cache.refreshAfterWriteNanos())
            ? OptionalLong.empty()
            : OptionalLong.of(unit.convert(age, TimeUnit.NANOSECONDS));
      }
      @Override public long getExpiresAfter(TimeUnit unit) {
        return unit.convert(cache.refreshAfterWriteNanos(), TimeUnit.NANOSECONDS);
      }
      @Override public void setExpiresAfter(long duration, TimeUnit unit) {
        requireArgument(duration >= 0);
        cache.setRefreshAfterWriteNanos(unit.toNanos(duration));
        cache.scheduleAfterWrite();
      }
      @SuppressWarnings("PMD.SimplifiedTernary") // false positive (#1424)
      @Override public Map<K, V> oldest(int limit) {
        return cache.expiresAfterWrite()
            ? expireAfterWrite().get().oldest(limit)
            : sortedByWriteTime(limit, /* ascending */ true);
      }
      @SuppressWarnings("PMD.SimplifiedTernary") // false positive (#1424)
      @Override public Map<K, V> youngest(int limit) {
        return cache.expiresAfterWrite()
            ? expireAfterWrite().get().youngest(limit)
            : sortedByWriteTime(limit, /* ascending */ false);
      }
      Map<K, V> sortedByWriteTime(int limit, boolean ascending) {
        Comparator<Node<K, V>> comparator = Comparator.comparingLong(Node::getWriteTime);
        Iterator<Node<K, V>> iterator = cache.data.values().stream().parallel().sorted(
            ascending ? comparator : comparator.reversed()).limit(limit).iterator();
        return cache.snapshot(() -> iterator, transformer, limit);
      }
    }
  }

  /* ---------------- Loading Cache -------------- */

  static final class BoundedLocalLoadingCache<K, V> extends BoundedLocalManualCache<K, V>
      implements LocalLoadingCache<BoundedLocalCache<K, V>, K, V> {
    private static final long serialVersionUID = 1;

    final boolean hasBulkLoader;
    final Function<K, V> mappingFunction;

    BoundedLocalLoadingCache(Caffeine<K, V> builder, CacheLoader<? super K, V> loader) {
      super(builder, loader);
      requireNonNull(loader);
      hasBulkLoader = hasLoadAll(loader);
      mappingFunction = key -> {
        try {
          return loader.load(key);
        } catch (RuntimeException e) {
          throw e;
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          throw new CompletionException(e);
        } catch (Exception e) {
          throw new CompletionException(e);
        }
      };
    }

    @Override
    public CacheLoader<? super K, V> cacheLoader() {
      return cache.cacheLoader;
    }

    @Override
    public Function<K, V> mappingFunction() {
      return mappingFunction;
    }

    @Override
    public boolean hasBulkLoader() {
      return hasBulkLoader;
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
    }

    @Override
    Object writeReplace() {
      @SuppressWarnings("unchecked")
      SerializationProxy<K, V> proxy = (SerializationProxy<K, V>) super.writeReplace();
      if (cache.refreshAfterWrite()) {
        proxy.refreshAfterWriteNanos = cache.refreshAfterWriteNanos();
      }
      proxy.loader = cache.cacheLoader;
      return proxy;
    }
  }

  /* ---------------- Async Loading Cache -------------- */

  static final class BoundedLocalAsyncLoadingCache<K, V>
      extends LocalAsyncLoadingCache<BoundedLocalCache<K, CompletableFuture<V>>, K, V>
      implements Serializable {
    private static final long serialVersionUID = 1;

    final boolean isWeighted;
    Policy<K, V> policy;

    @SuppressWarnings("unchecked")
    BoundedLocalAsyncLoadingCache(Caffeine<K, V> builder, AsyncCacheLoader<? super K, V> loader) {
      super((BoundedLocalCache<K, CompletableFuture<V>>) LocalCacheFactory.newBoundedLocalCache(
          builder, asyncLoader(loader, builder), /* async */ true), loader);
      isWeighted = builder.isWeighted();
    }

    private static <K, V> CacheLoader<K, V> asyncLoader(
        AsyncCacheLoader<? super K, V> loader, Caffeine<?, ?> builder) {
      Executor executor = builder.getExecutor();
      return new CacheLoader<K, V>() {
        @Override public V load(K key) {
          @SuppressWarnings("unchecked")
          V newValue = (V) loader.asyncLoad(key, executor);
          return newValue;
        }
        @Override public V reload(K key, V oldValue) {
          @SuppressWarnings("unchecked")
          V newValue = (V) loader.asyncReload(key, oldValue, executor);
          return newValue;
        }
        @Override public CompletableFuture<V> asyncReload(K key, V oldValue, Executor executor) {
          return loader.asyncReload(key, oldValue, executor);
        }
      };
    }

    @Override
    protected Policy<K, V> policy() {
      if (policy == null) {
        @SuppressWarnings("unchecked")
        BoundedLocalCache<K, V> castCache = (BoundedLocalCache<K, V>) cache;
        Function<CompletableFuture<V>, V> transformer = Async::getIfReady;
        @SuppressWarnings("unchecked")
        Function<V, V> castTransformer = (Function<V, V>) transformer;
        policy = new BoundedPolicy<>(castCache, castTransformer, isWeighted);
      }
      return policy;
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
    }

    Object writeReplace() {
      SerializationProxy<K, V> proxy = makeSerializationProxy(cache, isWeighted);
      if (cache.refreshAfterWrite()) {
        proxy.refreshAfterWriteNanos = cache.refreshAfterWriteNanos();
      }
      proxy.loader = loader;
      proxy.async = true;
      return proxy;
    }
  }
}

/** The namespace for field padding through inheritance. */
final class BLCHeader {

  static abstract class PadDrainStatus<K, V> extends AbstractMap<K, V> {
    long p00, p01, p02, p03, p04, p05, p06, p07;
    long p10, p11, p12, p13, p14, p15, p16;
  }

  /** Enforces a memory layout to avoid false sharing by padding the drain status. */
  static abstract class DrainStatusRef<K, V> extends PadDrainStatus<K, V> {
    static final long DRAIN_STATUS_OFFSET =
        UnsafeAccess.objectFieldOffset(DrainStatusRef.class, "drainStatus");

    /** A drain is not taking place. */
    static final int IDLE = 0;
    /** A drain is required due to a pending write modification. */
    static final int REQUIRED = 1;
    /** A drain is in progress and will transition to idle. */
    static final int PROCESSING_TO_IDLE = 2;
    /** A drain is in progress and will transition to required. */
    static final int PROCESSING_TO_REQUIRED = 3;

    /** The draining status of the buffers. */
    volatile int drainStatus = IDLE;

    /**
     * Returns whether maintenance work is needed.
     *
     * @param delayable if draining the read buffer can be delayed
     */
    boolean shouldDrainBuffers(boolean delayable) {
      switch (drainStatus()) {
        case IDLE:
          return !delayable;
        case REQUIRED:
          return true;
        case PROCESSING_TO_IDLE:
        case PROCESSING_TO_REQUIRED:
          return false;
        default:
          throw new IllegalStateException();
      }
    }

    int drainStatus() {
      return UnsafeAccess.UNSAFE.getInt(this, DRAIN_STATUS_OFFSET);
    }

    void lazySetDrainStatus(int drainStatus) {
      UnsafeAccess.UNSAFE.putOrderedInt(this, DRAIN_STATUS_OFFSET, drainStatus);
    }

    boolean casDrainStatus(int expect, int update) {
      return UnsafeAccess.UNSAFE.compareAndSwapInt(this, DRAIN_STATUS_OFFSET, expect, update);
    }
  }
}
