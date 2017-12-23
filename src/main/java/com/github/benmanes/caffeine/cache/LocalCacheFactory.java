// Copyright 2016 Ben Manes. All Rights Reserved.
package com.github.benmanes.caffeine.cache;

import com.github.benmanes.caffeine.base.UnsafeAccess;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;
import java.lang.IllegalStateException;
import java.lang.Runnable;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.lang.ref.ReferenceQueue;

/**
 * <em>WARNING: GENERATED CODE</em>
 *
 * A factory for caches optimized for a particular configuration.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 */
@SuppressWarnings({"unchecked", "unused", "PMD", "MissingOverride"})
final class LocalCacheFactory {
  /**
   * Returns a cache optimized for this configuration.
   */
  static <K, V> BoundedLocalCache<K, V> newBoundedLocalCache(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
    StringBuilder sb = new StringBuilder();
    if (builder.isStrongKeys()) {
      sb.append('S');
    } else {
      sb.append('W');
    }
    if (builder.isStrongValues()) {
      sb.append('S');
    } else {
      sb.append('I');
    }
    if (builder.removalListener != null) {
      sb.append("Li");
    }
    if (builder.isRecordingStats()) {
      sb.append('S');
    }
    if (builder.evicts()) {
      sb.append('M');
      if (builder.isWeighted()) {
        sb.append('W');
      } else {
        sb.append('S');
      }
    }
    if (builder.expiresAfterAccess()) {
      sb.append('A');
    }
    if (builder.expiresAfterWrite()) {
      sb.append('W');
    }
    if (builder.refreshes()) {
      sb.append('R');
    }
    switch (sb.toString()) {
      case "SI": return new SI<>(builder, cacheLoader, async);
      case "SIA": return new SIA<>(builder, cacheLoader, async);
      case "SIAR": return new SIAR<>(builder, cacheLoader, async);
      case "SIAW": return new SIAW<>(builder, cacheLoader, async);
      case "SIAWR": return new SIAWR<>(builder, cacheLoader, async);
      case "SILi": return new SILi<>(builder, cacheLoader, async);
      case "SILiA": return new SILiA<>(builder, cacheLoader, async);
      case "SILiAR": return new SILiAR<>(builder, cacheLoader, async);
      case "SILiAW": return new SILiAW<>(builder, cacheLoader, async);
      case "SILiAWR": return new SILiAWR<>(builder, cacheLoader, async);
      case "SILiMS": return new SILiMS<>(builder, cacheLoader, async);
      case "SILiMSA": return new SILiMSA<>(builder, cacheLoader, async);
      case "SILiMSAR": return new SILiMSAR<>(builder, cacheLoader, async);
      case "SILiMSAW": return new SILiMSAW<>(builder, cacheLoader, async);
      case "SILiMSAWR": return new SILiMSAWR<>(builder, cacheLoader, async);
      case "SILiMSR": return new SILiMSR<>(builder, cacheLoader, async);
      case "SILiMSW": return new SILiMSW<>(builder, cacheLoader, async);
      case "SILiMSWR": return new SILiMSWR<>(builder, cacheLoader, async);
      case "SILiMW": return new SILiMW<>(builder, cacheLoader, async);
      case "SILiMWA": return new SILiMWA<>(builder, cacheLoader, async);
      case "SILiMWAR": return new SILiMWAR<>(builder, cacheLoader, async);
      case "SILiMWAW": return new SILiMWAW<>(builder, cacheLoader, async);
      case "SILiMWAWR": return new SILiMWAWR<>(builder, cacheLoader, async);
      case "SILiMWR": return new SILiMWR<>(builder, cacheLoader, async);
      case "SILiMWW": return new SILiMWW<>(builder, cacheLoader, async);
      case "SILiMWWR": return new SILiMWWR<>(builder, cacheLoader, async);
      case "SILiR": return new SILiR<>(builder, cacheLoader, async);
      case "SILiS": return new SILiS<>(builder, cacheLoader, async);
      case "SILiSA": return new SILiSA<>(builder, cacheLoader, async);
      case "SILiSAR": return new SILiSAR<>(builder, cacheLoader, async);
      case "SILiSAW": return new SILiSAW<>(builder, cacheLoader, async);
      case "SILiSAWR": return new SILiSAWR<>(builder, cacheLoader, async);
      case "SILiSMS": return new SILiSMS<>(builder, cacheLoader, async);
      case "SILiSMSA": return new SILiSMSA<>(builder, cacheLoader, async);
      case "SILiSMSAR": return new SILiSMSAR<>(builder, cacheLoader, async);
      case "SILiSMSAW": return new SILiSMSAW<>(builder, cacheLoader, async);
      case "SILiSMSAWR": return new SILiSMSAWR<>(builder, cacheLoader, async);
      case "SILiSMSR": return new SILiSMSR<>(builder, cacheLoader, async);
      case "SILiSMSW": return new SILiSMSW<>(builder, cacheLoader, async);
      case "SILiSMSWR": return new SILiSMSWR<>(builder, cacheLoader, async);
      case "SILiSMW": return new SILiSMW<>(builder, cacheLoader, async);
      case "SILiSMWA": return new SILiSMWA<>(builder, cacheLoader, async);
      case "SILiSMWAR": return new SILiSMWAR<>(builder, cacheLoader, async);
      case "SILiSMWAW": return new SILiSMWAW<>(builder, cacheLoader, async);
      case "SILiSMWAWR": return new SILiSMWAWR<>(builder, cacheLoader, async);
      case "SILiSMWR": return new SILiSMWR<>(builder, cacheLoader, async);
      case "SILiSMWW": return new SILiSMWW<>(builder, cacheLoader, async);
      case "SILiSMWWR": return new SILiSMWWR<>(builder, cacheLoader, async);
      case "SILiSR": return new SILiSR<>(builder, cacheLoader, async);
      case "SILiSW": return new SILiSW<>(builder, cacheLoader, async);
      case "SILiSWR": return new SILiSWR<>(builder, cacheLoader, async);
      case "SILiW": return new SILiW<>(builder, cacheLoader, async);
      case "SILiWR": return new SILiWR<>(builder, cacheLoader, async);
      case "SIMS": return new SIMS<>(builder, cacheLoader, async);
      case "SIMSA": return new SIMSA<>(builder, cacheLoader, async);
      case "SIMSAR": return new SIMSAR<>(builder, cacheLoader, async);
      case "SIMSAW": return new SIMSAW<>(builder, cacheLoader, async);
      case "SIMSAWR": return new SIMSAWR<>(builder, cacheLoader, async);
      case "SIMSR": return new SIMSR<>(builder, cacheLoader, async);
      case "SIMSW": return new SIMSW<>(builder, cacheLoader, async);
      case "SIMSWR": return new SIMSWR<>(builder, cacheLoader, async);
      case "SIMW": return new SIMW<>(builder, cacheLoader, async);
      case "SIMWA": return new SIMWA<>(builder, cacheLoader, async);
      case "SIMWAR": return new SIMWAR<>(builder, cacheLoader, async);
      case "SIMWAW": return new SIMWAW<>(builder, cacheLoader, async);
      case "SIMWAWR": return new SIMWAWR<>(builder, cacheLoader, async);
      case "SIMWR": return new SIMWR<>(builder, cacheLoader, async);
      case "SIMWW": return new SIMWW<>(builder, cacheLoader, async);
      case "SIMWWR": return new SIMWWR<>(builder, cacheLoader, async);
      case "SIR": return new SIR<>(builder, cacheLoader, async);
      case "SIS": return new SIS<>(builder, cacheLoader, async);
      case "SISA": return new SISA<>(builder, cacheLoader, async);
      case "SISAR": return new SISAR<>(builder, cacheLoader, async);
      case "SISAW": return new SISAW<>(builder, cacheLoader, async);
      case "SISAWR": return new SISAWR<>(builder, cacheLoader, async);
      case "SISMS": return new SISMS<>(builder, cacheLoader, async);
      case "SISMSA": return new SISMSA<>(builder, cacheLoader, async);
      case "SISMSAR": return new SISMSAR<>(builder, cacheLoader, async);
      case "SISMSAW": return new SISMSAW<>(builder, cacheLoader, async);
      case "SISMSAWR": return new SISMSAWR<>(builder, cacheLoader, async);
      case "SISMSR": return new SISMSR<>(builder, cacheLoader, async);
      case "SISMSW": return new SISMSW<>(builder, cacheLoader, async);
      case "SISMSWR": return new SISMSWR<>(builder, cacheLoader, async);
      case "SISMW": return new SISMW<>(builder, cacheLoader, async);
      case "SISMWA": return new SISMWA<>(builder, cacheLoader, async);
      case "SISMWAR": return new SISMWAR<>(builder, cacheLoader, async);
      case "SISMWAW": return new SISMWAW<>(builder, cacheLoader, async);
      case "SISMWAWR": return new SISMWAWR<>(builder, cacheLoader, async);
      case "SISMWR": return new SISMWR<>(builder, cacheLoader, async);
      case "SISMWW": return new SISMWW<>(builder, cacheLoader, async);
      case "SISMWWR": return new SISMWWR<>(builder, cacheLoader, async);
      case "SISR": return new SISR<>(builder, cacheLoader, async);
      case "SISW": return new SISW<>(builder, cacheLoader, async);
      case "SISWR": return new SISWR<>(builder, cacheLoader, async);
      case "SIW": return new SIW<>(builder, cacheLoader, async);
      case "SIWR": return new SIWR<>(builder, cacheLoader, async);
      case "SS": return new SS<>(builder, cacheLoader, async);
      case "SSA": return new SSA<>(builder, cacheLoader, async);
      case "SSAR": return new SSAR<>(builder, cacheLoader, async);
      case "SSAW": return new SSAW<>(builder, cacheLoader, async);
      case "SSAWR": return new SSAWR<>(builder, cacheLoader, async);
      case "SSLi": return new SSLi<>(builder, cacheLoader, async);
      case "SSLiA": return new SSLiA<>(builder, cacheLoader, async);
      case "SSLiAR": return new SSLiAR<>(builder, cacheLoader, async);
      case "SSLiAW": return new SSLiAW<>(builder, cacheLoader, async);
      case "SSLiAWR": return new SSLiAWR<>(builder, cacheLoader, async);
      case "SSLiMS": return new SSLiMS<>(builder, cacheLoader, async);
      case "SSLiMSA": return new SSLiMSA<>(builder, cacheLoader, async);
      case "SSLiMSAR": return new SSLiMSAR<>(builder, cacheLoader, async);
      case "SSLiMSAW": return new SSLiMSAW<>(builder, cacheLoader, async);
      case "SSLiMSAWR": return new SSLiMSAWR<>(builder, cacheLoader, async);
      case "SSLiMSR": return new SSLiMSR<>(builder, cacheLoader, async);
      case "SSLiMSW": return new SSLiMSW<>(builder, cacheLoader, async);
      case "SSLiMSWR": return new SSLiMSWR<>(builder, cacheLoader, async);
      case "SSLiMW": return new SSLiMW<>(builder, cacheLoader, async);
      case "SSLiMWA": return new SSLiMWA<>(builder, cacheLoader, async);
      case "SSLiMWAR": return new SSLiMWAR<>(builder, cacheLoader, async);
      case "SSLiMWAW": return new SSLiMWAW<>(builder, cacheLoader, async);
      case "SSLiMWAWR": return new SSLiMWAWR<>(builder, cacheLoader, async);
      case "SSLiMWR": return new SSLiMWR<>(builder, cacheLoader, async);
      case "SSLiMWW": return new SSLiMWW<>(builder, cacheLoader, async);
      case "SSLiMWWR": return new SSLiMWWR<>(builder, cacheLoader, async);
      case "SSLiR": return new SSLiR<>(builder, cacheLoader, async);
      case "SSLiS": return new SSLiS<>(builder, cacheLoader, async);
      case "SSLiSA": return new SSLiSA<>(builder, cacheLoader, async);
      case "SSLiSAR": return new SSLiSAR<>(builder, cacheLoader, async);
      case "SSLiSAW": return new SSLiSAW<>(builder, cacheLoader, async);
      case "SSLiSAWR": return new SSLiSAWR<>(builder, cacheLoader, async);
      case "SSLiSMS": return new SSLiSMS<>(builder, cacheLoader, async);
      case "SSLiSMSA": return new SSLiSMSA<>(builder, cacheLoader, async);
      case "SSLiSMSAR": return new SSLiSMSAR<>(builder, cacheLoader, async);
      case "SSLiSMSAW": return new SSLiSMSAW<>(builder, cacheLoader, async);
      case "SSLiSMSAWR": return new SSLiSMSAWR<>(builder, cacheLoader, async);
      case "SSLiSMSR": return new SSLiSMSR<>(builder, cacheLoader, async);
      case "SSLiSMSW": return new SSLiSMSW<>(builder, cacheLoader, async);
      case "SSLiSMSWR": return new SSLiSMSWR<>(builder, cacheLoader, async);
      case "SSLiSMW": return new SSLiSMW<>(builder, cacheLoader, async);
      case "SSLiSMWA": return new SSLiSMWA<>(builder, cacheLoader, async);
      case "SSLiSMWAR": return new SSLiSMWAR<>(builder, cacheLoader, async);
      case "SSLiSMWAW": return new SSLiSMWAW<>(builder, cacheLoader, async);
      case "SSLiSMWAWR": return new SSLiSMWAWR<>(builder, cacheLoader, async);
      case "SSLiSMWR": return new SSLiSMWR<>(builder, cacheLoader, async);
      case "SSLiSMWW": return new SSLiSMWW<>(builder, cacheLoader, async);
      case "SSLiSMWWR": return new SSLiSMWWR<>(builder, cacheLoader, async);
      case "SSLiSR": return new SSLiSR<>(builder, cacheLoader, async);
      case "SSLiSW": return new SSLiSW<>(builder, cacheLoader, async);
      case "SSLiSWR": return new SSLiSWR<>(builder, cacheLoader, async);
      case "SSLiW": return new SSLiW<>(builder, cacheLoader, async);
      case "SSLiWR": return new SSLiWR<>(builder, cacheLoader, async);
      case "SSMS": return new SSMS<>(builder, cacheLoader, async);
      case "SSMSA": return new SSMSA<>(builder, cacheLoader, async);
      case "SSMSAR": return new SSMSAR<>(builder, cacheLoader, async);
      case "SSMSAW": return new SSMSAW<>(builder, cacheLoader, async);
      case "SSMSAWR": return new SSMSAWR<>(builder, cacheLoader, async);
      case "SSMSR": return new SSMSR<>(builder, cacheLoader, async);
      case "SSMSW": return new SSMSW<>(builder, cacheLoader, async);
      case "SSMSWR": return new SSMSWR<>(builder, cacheLoader, async);
      case "SSMW": return new SSMW<>(builder, cacheLoader, async);
      case "SSMWA": return new SSMWA<>(builder, cacheLoader, async);
      case "SSMWAR": return new SSMWAR<>(builder, cacheLoader, async);
      case "SSMWAW": return new SSMWAW<>(builder, cacheLoader, async);
      case "SSMWAWR": return new SSMWAWR<>(builder, cacheLoader, async);
      case "SSMWR": return new SSMWR<>(builder, cacheLoader, async);
      case "SSMWW": return new SSMWW<>(builder, cacheLoader, async);
      case "SSMWWR": return new SSMWWR<>(builder, cacheLoader, async);
      case "SSR": return new SSR<>(builder, cacheLoader, async);
      case "SSS": return new SSS<>(builder, cacheLoader, async);
      case "SSSA": return new SSSA<>(builder, cacheLoader, async);
      case "SSSAR": return new SSSAR<>(builder, cacheLoader, async);
      case "SSSAW": return new SSSAW<>(builder, cacheLoader, async);
      case "SSSAWR": return new SSSAWR<>(builder, cacheLoader, async);
      case "SSSMS": return new SSSMS<>(builder, cacheLoader, async);
      case "SSSMSA": return new SSSMSA<>(builder, cacheLoader, async);
      case "SSSMSAR": return new SSSMSAR<>(builder, cacheLoader, async);
      case "SSSMSAW": return new SSSMSAW<>(builder, cacheLoader, async);
      case "SSSMSAWR": return new SSSMSAWR<>(builder, cacheLoader, async);
      case "SSSMSR": return new SSSMSR<>(builder, cacheLoader, async);
      case "SSSMSW": return new SSSMSW<>(builder, cacheLoader, async);
      case "SSSMSWR": return new SSSMSWR<>(builder, cacheLoader, async);
      case "SSSMW": return new SSSMW<>(builder, cacheLoader, async);
      case "SSSMWA": return new SSSMWA<>(builder, cacheLoader, async);
      case "SSSMWAR": return new SSSMWAR<>(builder, cacheLoader, async);
      case "SSSMWAW": return new SSSMWAW<>(builder, cacheLoader, async);
      case "SSSMWAWR": return new SSSMWAWR<>(builder, cacheLoader, async);
      case "SSSMWR": return new SSSMWR<>(builder, cacheLoader, async);
      case "SSSMWW": return new SSSMWW<>(builder, cacheLoader, async);
      case "SSSMWWR": return new SSSMWWR<>(builder, cacheLoader, async);
      case "SSSR": return new SSSR<>(builder, cacheLoader, async);
      case "SSSW": return new SSSW<>(builder, cacheLoader, async);
      case "SSSWR": return new SSSWR<>(builder, cacheLoader, async);
      case "SSW": return new SSW<>(builder, cacheLoader, async);
      case "SSWR": return new SSWR<>(builder, cacheLoader, async);
      case "WI": return new WI<>(builder, cacheLoader, async);
      case "WIA": return new WIA<>(builder, cacheLoader, async);
      case "WIAR": return new WIAR<>(builder, cacheLoader, async);
      case "WIAW": return new WIAW<>(builder, cacheLoader, async);
      case "WIAWR": return new WIAWR<>(builder, cacheLoader, async);
      case "WILi": return new WILi<>(builder, cacheLoader, async);
      case "WILiA": return new WILiA<>(builder, cacheLoader, async);
      case "WILiAR": return new WILiAR<>(builder, cacheLoader, async);
      case "WILiAW": return new WILiAW<>(builder, cacheLoader, async);
      case "WILiAWR": return new WILiAWR<>(builder, cacheLoader, async);
      case "WILiMS": return new WILiMS<>(builder, cacheLoader, async);
      case "WILiMSA": return new WILiMSA<>(builder, cacheLoader, async);
      case "WILiMSAR": return new WILiMSAR<>(builder, cacheLoader, async);
      case "WILiMSAW": return new WILiMSAW<>(builder, cacheLoader, async);
      case "WILiMSAWR": return new WILiMSAWR<>(builder, cacheLoader, async);
      case "WILiMSR": return new WILiMSR<>(builder, cacheLoader, async);
      case "WILiMSW": return new WILiMSW<>(builder, cacheLoader, async);
      case "WILiMSWR": return new WILiMSWR<>(builder, cacheLoader, async);
      case "WILiMW": return new WILiMW<>(builder, cacheLoader, async);
      case "WILiMWA": return new WILiMWA<>(builder, cacheLoader, async);
      case "WILiMWAR": return new WILiMWAR<>(builder, cacheLoader, async);
      case "WILiMWAW": return new WILiMWAW<>(builder, cacheLoader, async);
      case "WILiMWAWR": return new WILiMWAWR<>(builder, cacheLoader, async);
      case "WILiMWR": return new WILiMWR<>(builder, cacheLoader, async);
      case "WILiMWW": return new WILiMWW<>(builder, cacheLoader, async);
      case "WILiMWWR": return new WILiMWWR<>(builder, cacheLoader, async);
      case "WILiR": return new WILiR<>(builder, cacheLoader, async);
      case "WILiS": return new WILiS<>(builder, cacheLoader, async);
      case "WILiSA": return new WILiSA<>(builder, cacheLoader, async);
      case "WILiSAR": return new WILiSAR<>(builder, cacheLoader, async);
      case "WILiSAW": return new WILiSAW<>(builder, cacheLoader, async);
      case "WILiSAWR": return new WILiSAWR<>(builder, cacheLoader, async);
      case "WILiSMS": return new WILiSMS<>(builder, cacheLoader, async);
      case "WILiSMSA": return new WILiSMSA<>(builder, cacheLoader, async);
      case "WILiSMSAR": return new WILiSMSAR<>(builder, cacheLoader, async);
      case "WILiSMSAW": return new WILiSMSAW<>(builder, cacheLoader, async);
      case "WILiSMSAWR": return new WILiSMSAWR<>(builder, cacheLoader, async);
      case "WILiSMSR": return new WILiSMSR<>(builder, cacheLoader, async);
      case "WILiSMSW": return new WILiSMSW<>(builder, cacheLoader, async);
      case "WILiSMSWR": return new WILiSMSWR<>(builder, cacheLoader, async);
      case "WILiSMW": return new WILiSMW<>(builder, cacheLoader, async);
      case "WILiSMWA": return new WILiSMWA<>(builder, cacheLoader, async);
      case "WILiSMWAR": return new WILiSMWAR<>(builder, cacheLoader, async);
      case "WILiSMWAW": return new WILiSMWAW<>(builder, cacheLoader, async);
      case "WILiSMWAWR": return new WILiSMWAWR<>(builder, cacheLoader, async);
      case "WILiSMWR": return new WILiSMWR<>(builder, cacheLoader, async);
      case "WILiSMWW": return new WILiSMWW<>(builder, cacheLoader, async);
      case "WILiSMWWR": return new WILiSMWWR<>(builder, cacheLoader, async);
      case "WILiSR": return new WILiSR<>(builder, cacheLoader, async);
      case "WILiSW": return new WILiSW<>(builder, cacheLoader, async);
      case "WILiSWR": return new WILiSWR<>(builder, cacheLoader, async);
      case "WILiW": return new WILiW<>(builder, cacheLoader, async);
      case "WILiWR": return new WILiWR<>(builder, cacheLoader, async);
      case "WIMS": return new WIMS<>(builder, cacheLoader, async);
      case "WIMSA": return new WIMSA<>(builder, cacheLoader, async);
      case "WIMSAR": return new WIMSAR<>(builder, cacheLoader, async);
      case "WIMSAW": return new WIMSAW<>(builder, cacheLoader, async);
      case "WIMSAWR": return new WIMSAWR<>(builder, cacheLoader, async);
      case "WIMSR": return new WIMSR<>(builder, cacheLoader, async);
      case "WIMSW": return new WIMSW<>(builder, cacheLoader, async);
      case "WIMSWR": return new WIMSWR<>(builder, cacheLoader, async);
      case "WIMW": return new WIMW<>(builder, cacheLoader, async);
      case "WIMWA": return new WIMWA<>(builder, cacheLoader, async);
      case "WIMWAR": return new WIMWAR<>(builder, cacheLoader, async);
      case "WIMWAW": return new WIMWAW<>(builder, cacheLoader, async);
      case "WIMWAWR": return new WIMWAWR<>(builder, cacheLoader, async);
      case "WIMWR": return new WIMWR<>(builder, cacheLoader, async);
      case "WIMWW": return new WIMWW<>(builder, cacheLoader, async);
      case "WIMWWR": return new WIMWWR<>(builder, cacheLoader, async);
      case "WIR": return new WIR<>(builder, cacheLoader, async);
      case "WIS": return new WIS<>(builder, cacheLoader, async);
      case "WISA": return new WISA<>(builder, cacheLoader, async);
      case "WISAR": return new WISAR<>(builder, cacheLoader, async);
      case "WISAW": return new WISAW<>(builder, cacheLoader, async);
      case "WISAWR": return new WISAWR<>(builder, cacheLoader, async);
      case "WISMS": return new WISMS<>(builder, cacheLoader, async);
      case "WISMSA": return new WISMSA<>(builder, cacheLoader, async);
      case "WISMSAR": return new WISMSAR<>(builder, cacheLoader, async);
      case "WISMSAW": return new WISMSAW<>(builder, cacheLoader, async);
      case "WISMSAWR": return new WISMSAWR<>(builder, cacheLoader, async);
      case "WISMSR": return new WISMSR<>(builder, cacheLoader, async);
      case "WISMSW": return new WISMSW<>(builder, cacheLoader, async);
      case "WISMSWR": return new WISMSWR<>(builder, cacheLoader, async);
      case "WISMW": return new WISMW<>(builder, cacheLoader, async);
      case "WISMWA": return new WISMWA<>(builder, cacheLoader, async);
      case "WISMWAR": return new WISMWAR<>(builder, cacheLoader, async);
      case "WISMWAW": return new WISMWAW<>(builder, cacheLoader, async);
      case "WISMWAWR": return new WISMWAWR<>(builder, cacheLoader, async);
      case "WISMWR": return new WISMWR<>(builder, cacheLoader, async);
      case "WISMWW": return new WISMWW<>(builder, cacheLoader, async);
      case "WISMWWR": return new WISMWWR<>(builder, cacheLoader, async);
      case "WISR": return new WISR<>(builder, cacheLoader, async);
      case "WISW": return new WISW<>(builder, cacheLoader, async);
      case "WISWR": return new WISWR<>(builder, cacheLoader, async);
      case "WIW": return new WIW<>(builder, cacheLoader, async);
      case "WIWR": return new WIWR<>(builder, cacheLoader, async);
      case "WS": return new WS<>(builder, cacheLoader, async);
      case "WSA": return new WSA<>(builder, cacheLoader, async);
      case "WSAR": return new WSAR<>(builder, cacheLoader, async);
      case "WSAW": return new WSAW<>(builder, cacheLoader, async);
      case "WSAWR": return new WSAWR<>(builder, cacheLoader, async);
      case "WSLi": return new WSLi<>(builder, cacheLoader, async);
      case "WSLiA": return new WSLiA<>(builder, cacheLoader, async);
      case "WSLiAR": return new WSLiAR<>(builder, cacheLoader, async);
      case "WSLiAW": return new WSLiAW<>(builder, cacheLoader, async);
      case "WSLiAWR": return new WSLiAWR<>(builder, cacheLoader, async);
      case "WSLiMS": return new WSLiMS<>(builder, cacheLoader, async);
      case "WSLiMSA": return new WSLiMSA<>(builder, cacheLoader, async);
      case "WSLiMSAR": return new WSLiMSAR<>(builder, cacheLoader, async);
      case "WSLiMSAW": return new WSLiMSAW<>(builder, cacheLoader, async);
      case "WSLiMSAWR": return new WSLiMSAWR<>(builder, cacheLoader, async);
      case "WSLiMSR": return new WSLiMSR<>(builder, cacheLoader, async);
      case "WSLiMSW": return new WSLiMSW<>(builder, cacheLoader, async);
      case "WSLiMSWR": return new WSLiMSWR<>(builder, cacheLoader, async);
      case "WSLiMW": return new WSLiMW<>(builder, cacheLoader, async);
      case "WSLiMWA": return new WSLiMWA<>(builder, cacheLoader, async);
      case "WSLiMWAR": return new WSLiMWAR<>(builder, cacheLoader, async);
      case "WSLiMWAW": return new WSLiMWAW<>(builder, cacheLoader, async);
      case "WSLiMWAWR": return new WSLiMWAWR<>(builder, cacheLoader, async);
      case "WSLiMWR": return new WSLiMWR<>(builder, cacheLoader, async);
      case "WSLiMWW": return new WSLiMWW<>(builder, cacheLoader, async);
      case "WSLiMWWR": return new WSLiMWWR<>(builder, cacheLoader, async);
      case "WSLiR": return new WSLiR<>(builder, cacheLoader, async);
      case "WSLiS": return new WSLiS<>(builder, cacheLoader, async);
      case "WSLiSA": return new WSLiSA<>(builder, cacheLoader, async);
      case "WSLiSAR": return new WSLiSAR<>(builder, cacheLoader, async);
      case "WSLiSAW": return new WSLiSAW<>(builder, cacheLoader, async);
      case "WSLiSAWR": return new WSLiSAWR<>(builder, cacheLoader, async);
      case "WSLiSMS": return new WSLiSMS<>(builder, cacheLoader, async);
      case "WSLiSMSA": return new WSLiSMSA<>(builder, cacheLoader, async);
      case "WSLiSMSAR": return new WSLiSMSAR<>(builder, cacheLoader, async);
      case "WSLiSMSAW": return new WSLiSMSAW<>(builder, cacheLoader, async);
      case "WSLiSMSAWR": return new WSLiSMSAWR<>(builder, cacheLoader, async);
      case "WSLiSMSR": return new WSLiSMSR<>(builder, cacheLoader, async);
      case "WSLiSMSW": return new WSLiSMSW<>(builder, cacheLoader, async);
      case "WSLiSMSWR": return new WSLiSMSWR<>(builder, cacheLoader, async);
      case "WSLiSMW": return new WSLiSMW<>(builder, cacheLoader, async);
      case "WSLiSMWA": return new WSLiSMWA<>(builder, cacheLoader, async);
      case "WSLiSMWAR": return new WSLiSMWAR<>(builder, cacheLoader, async);
      case "WSLiSMWAW": return new WSLiSMWAW<>(builder, cacheLoader, async);
      case "WSLiSMWAWR": return new WSLiSMWAWR<>(builder, cacheLoader, async);
      case "WSLiSMWR": return new WSLiSMWR<>(builder, cacheLoader, async);
      case "WSLiSMWW": return new WSLiSMWW<>(builder, cacheLoader, async);
      case "WSLiSMWWR": return new WSLiSMWWR<>(builder, cacheLoader, async);
      case "WSLiSR": return new WSLiSR<>(builder, cacheLoader, async);
      case "WSLiSW": return new WSLiSW<>(builder, cacheLoader, async);
      case "WSLiSWR": return new WSLiSWR<>(builder, cacheLoader, async);
      case "WSLiW": return new WSLiW<>(builder, cacheLoader, async);
      case "WSLiWR": return new WSLiWR<>(builder, cacheLoader, async);
      case "WSMS": return new WSMS<>(builder, cacheLoader, async);
      case "WSMSA": return new WSMSA<>(builder, cacheLoader, async);
      case "WSMSAR": return new WSMSAR<>(builder, cacheLoader, async);
      case "WSMSAW": return new WSMSAW<>(builder, cacheLoader, async);
      case "WSMSAWR": return new WSMSAWR<>(builder, cacheLoader, async);
      case "WSMSR": return new WSMSR<>(builder, cacheLoader, async);
      case "WSMSW": return new WSMSW<>(builder, cacheLoader, async);
      case "WSMSWR": return new WSMSWR<>(builder, cacheLoader, async);
      case "WSMW": return new WSMW<>(builder, cacheLoader, async);
      case "WSMWA": return new WSMWA<>(builder, cacheLoader, async);
      case "WSMWAR": return new WSMWAR<>(builder, cacheLoader, async);
      case "WSMWAW": return new WSMWAW<>(builder, cacheLoader, async);
      case "WSMWAWR": return new WSMWAWR<>(builder, cacheLoader, async);
      case "WSMWR": return new WSMWR<>(builder, cacheLoader, async);
      case "WSMWW": return new WSMWW<>(builder, cacheLoader, async);
      case "WSMWWR": return new WSMWWR<>(builder, cacheLoader, async);
      case "WSR": return new WSR<>(builder, cacheLoader, async);
      case "WSS": return new WSS<>(builder, cacheLoader, async);
      case "WSSA": return new WSSA<>(builder, cacheLoader, async);
      case "WSSAR": return new WSSAR<>(builder, cacheLoader, async);
      case "WSSAW": return new WSSAW<>(builder, cacheLoader, async);
      case "WSSAWR": return new WSSAWR<>(builder, cacheLoader, async);
      case "WSSMS": return new WSSMS<>(builder, cacheLoader, async);
      case "WSSMSA": return new WSSMSA<>(builder, cacheLoader, async);
      case "WSSMSAR": return new WSSMSAR<>(builder, cacheLoader, async);
      case "WSSMSAW": return new WSSMSAW<>(builder, cacheLoader, async);
      case "WSSMSAWR": return new WSSMSAWR<>(builder, cacheLoader, async);
      case "WSSMSR": return new WSSMSR<>(builder, cacheLoader, async);
      case "WSSMSW": return new WSSMSW<>(builder, cacheLoader, async);
      case "WSSMSWR": return new WSSMSWR<>(builder, cacheLoader, async);
      case "WSSMW": return new WSSMW<>(builder, cacheLoader, async);
      case "WSSMWA": return new WSSMWA<>(builder, cacheLoader, async);
      case "WSSMWAR": return new WSSMWAR<>(builder, cacheLoader, async);
      case "WSSMWAW": return new WSSMWAW<>(builder, cacheLoader, async);
      case "WSSMWAWR": return new WSSMWAWR<>(builder, cacheLoader, async);
      case "WSSMWR": return new WSSMWR<>(builder, cacheLoader, async);
      case "WSSMWW": return new WSSMWW<>(builder, cacheLoader, async);
      case "WSSMWWR": return new WSSMWWR<>(builder, cacheLoader, async);
      case "WSSR": return new WSSR<>(builder, cacheLoader, async);
      case "WSSW": return new WSSW<>(builder, cacheLoader, async);
      case "WSSWR": return new WSSWR<>(builder, cacheLoader, async);
      case "WSW": return new WSW<>(builder, cacheLoader, async);
      case "WSWR": return new WSWR<>(builder, cacheLoader, async);
      default: throw new IllegalStateException(sb.toString());
    }
  }

  static class SI<K, V> extends BoundedLocalCache<K, V> {
    private final ReferenceQueue<V> valueReferenceQueue = new ReferenceQueue<V>();

    SI(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
    }

    protected final ReferenceQueue<V> valueReferenceQueue() {
      return valueReferenceQueue;
    }

    protected final boolean collectValues() {
      return true;
    }
  }

  static class SIA<K, V> extends SI<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SIA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SIAR<K, V> extends SIA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIAW<K, V> extends SIA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SIAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SIAWR<K, V> extends SIAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILi<K, V> extends SI<K, V> {
    private final RemovalListener<K, V> removalListener;

    SILi(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.removalListener = builder.getRemovalListener(async);
    }

    public final RemovalListener<K, V> removalListener() {
      return removalListener;
    }

    public final boolean hasRemovalListener() {
      return true;
    }
  }

  static class SILiA<K, V> extends SILi<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SILiAR<K, V> extends SILiA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiAW<K, V> extends SILiA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiAWR<K, V> extends SILiAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMS<K, V> extends SILi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiMSA<K, V> extends SILiMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SILiMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SILiMSAR<K, V> extends SILiMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMSAW<K, V> extends SILiMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiMSAWR<K, V> extends SILiMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiMSR<K, V> extends SILiMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SILiMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMSW<K, V> extends SILiMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiMSWR<K, V> extends SILiMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMW<K, V> extends SILi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiMWA<K, V> extends SILiMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SILiMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SILiMWAR<K, V> extends SILiMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMWAW<K, V> extends SILiMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiMWAWR<K, V> extends SILiMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiMWR<K, V> extends SILiMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SILiMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiMWW<K, V> extends SILiMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiMWWR<K, V> extends SILiMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiR<K, V> extends SILi<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiS<K, V> extends SILi<K, V> {
    private final StatsCounter statsCounter;

    SILiS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class SILiSA<K, V> extends SILiS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SILiSAR<K, V> extends SILiSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSAW<K, V> extends SILiSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiSAWR<K, V> extends SILiSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMS<K, V> extends SILiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiSMSA<K, V> extends SILiSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SILiSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SILiSMSAR<K, V> extends SILiSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMSAW<K, V> extends SILiSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiSMSAWR<K, V> extends SILiSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiSMSR<K, V> extends SILiSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SILiSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMSW<K, V> extends SILiSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiSMSWR<K, V> extends SILiSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMW<K, V> extends SILiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SILiSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiSMWA<K, V> extends SILiSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SILiSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SILiSMWAR<K, V> extends SILiSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMWAW<K, V> extends SILiSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiSMWAWR<K, V> extends SILiSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiSMWR<K, V> extends SILiSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SILiSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiSMWW<K, V> extends SILiSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SILiSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SILiSMWWR<K, V> extends SILiSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SILiSR<K, V> extends SILiS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SILiSW<K, V> extends SILiS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SILiSWR<K, V> extends SILiSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SILiW<K, V> extends SILi<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SILiW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SILiWR<K, V> extends SILiW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SILiWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMS<K, V> extends SI<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SIMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SIMSA<K, V> extends SIMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SIMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SIMSAR<K, V> extends SIMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMSAW<K, V> extends SIMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SIMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SIMSAWR<K, V> extends SIMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SIMSR<K, V> extends SIMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SIMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMSW<K, V> extends SIMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SIMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SIMSWR<K, V> extends SIMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMW<K, V> extends SI<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SIMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SIMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SIMWA<K, V> extends SIMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SIMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SIMWAR<K, V> extends SIMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMWAW<K, V> extends SIMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SIMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SIMWAWR<K, V> extends SIMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SIMWR<K, V> extends SIMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SIMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIMWW<K, V> extends SIMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SIMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SIMWWR<K, V> extends SIMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SIR<K, V> extends SI<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SIR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SIS<K, V> extends SI<K, V> {
    private final StatsCounter statsCounter;

    SIS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class SISA<K, V> extends SIS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SISA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SISAR<K, V> extends SISA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISAW<K, V> extends SISA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SISAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SISAWR<K, V> extends SISAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMS<K, V> extends SIS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SISMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SISMSA<K, V> extends SISMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SISMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SISMSAR<K, V> extends SISMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMSAW<K, V> extends SISMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SISMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SISMSAWR<K, V> extends SISMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SISMSR<K, V> extends SISMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SISMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMSW<K, V> extends SISMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SISMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SISMSWR<K, V> extends SISMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMW<K, V> extends SIS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SISMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SISMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SISMWA<K, V> extends SISMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SISMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SISMWAR<K, V> extends SISMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMWAW<K, V> extends SISMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SISMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SISMWAWR<K, V> extends SISMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SISMWR<K, V> extends SISMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SISMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SISMWW<K, V> extends SISMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SISMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SISMWWR<K, V> extends SISMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SISR<K, V> extends SIS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SISR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SISW<K, V> extends SIS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SISW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SISWR<K, V> extends SISW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SISWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SIW<K, V> extends SI<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SIW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SIWR<K, V> extends SIW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SIWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SS<K, V> extends BoundedLocalCache<K, V> {
    SS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
    }
  }

  static class SSA<K, V> extends SS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSAR<K, V> extends SSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSAW<K, V> extends SSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSAWR<K, V> extends SSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLi<K, V> extends SS<K, V> {
    private final RemovalListener<K, V> removalListener;

    SSLi(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.removalListener = builder.getRemovalListener(async);
    }

    public final RemovalListener<K, V> removalListener() {
      return removalListener;
    }

    public final boolean hasRemovalListener() {
      return true;
    }
  }

  static class SSLiA<K, V> extends SSLi<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSLiAR<K, V> extends SSLiA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiAW<K, V> extends SSLiA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiAWR<K, V> extends SSLiAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMS<K, V> extends SSLi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiMSA<K, V> extends SSLiMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSLiMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSLiMSAR<K, V> extends SSLiMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMSAW<K, V> extends SSLiMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiMSAWR<K, V> extends SSLiMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiMSR<K, V> extends SSLiMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSLiMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMSW<K, V> extends SSLiMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiMSWR<K, V> extends SSLiMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMW<K, V> extends SSLi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiMWA<K, V> extends SSLiMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSLiMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSLiMWAR<K, V> extends SSLiMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMWAW<K, V> extends SSLiMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiMWAWR<K, V> extends SSLiMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiMWR<K, V> extends SSLiMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSLiMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiMWW<K, V> extends SSLiMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiMWWR<K, V> extends SSLiMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiR<K, V> extends SSLi<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiS<K, V> extends SSLi<K, V> {
    private final StatsCounter statsCounter;

    SSLiS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class SSLiSA<K, V> extends SSLiS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSLiSAR<K, V> extends SSLiSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSAW<K, V> extends SSLiSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiSAWR<K, V> extends SSLiSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMS<K, V> extends SSLiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiSMSA<K, V> extends SSLiSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSLiSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSLiSMSAR<K, V> extends SSLiSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMSAW<K, V> extends SSLiSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiSMSAWR<K, V> extends SSLiSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiSMSR<K, V> extends SSLiSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSLiSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMSW<K, V> extends SSLiSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiSMSWR<K, V> extends SSLiSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMW<K, V> extends SSLiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSLiSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiSMWA<K, V> extends SSLiSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSLiSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSLiSMWAR<K, V> extends SSLiSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMWAW<K, V> extends SSLiSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiSMWAWR<K, V> extends SSLiSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiSMWR<K, V> extends SSLiSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSLiSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiSMWW<K, V> extends SSLiSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSLiSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSLiSMWWR<K, V> extends SSLiSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSLiSR<K, V> extends SSLiS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSLiSW<K, V> extends SSLiS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSLiSWR<K, V> extends SSLiSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSLiW<K, V> extends SSLi<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSLiW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSLiWR<K, V> extends SSLiW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSLiWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMS<K, V> extends SS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSMSA<K, V> extends SSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSMSAR<K, V> extends SSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMSAW<K, V> extends SSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSMSAWR<K, V> extends SSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSMSR<K, V> extends SSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMSW<K, V> extends SSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSMSWR<K, V> extends SSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMW<K, V> extends SS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSMWA<K, V> extends SSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSMWAR<K, V> extends SSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMWAW<K, V> extends SSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSMWAWR<K, V> extends SSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSMWR<K, V> extends SSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSMWW<K, V> extends SSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSMWWR<K, V> extends SSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSR<K, V> extends SS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSS<K, V> extends SS<K, V> {
    private final StatsCounter statsCounter;

    SSS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class SSSA<K, V> extends SSS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSSAR<K, V> extends SSSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSAW<K, V> extends SSSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSSAWR<K, V> extends SSSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMS<K, V> extends SSS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSSMSA<K, V> extends SSSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSSMSAR<K, V> extends SSSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMSAW<K, V> extends SSSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSSMSAWR<K, V> extends SSSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSSMSR<K, V> extends SSSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMSW<K, V> extends SSSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSSMSWR<K, V> extends SSSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMW<K, V> extends SSS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(SSSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected boolean fastpath() {
      return true;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSSMWA<K, V> extends SSSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    SSSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected boolean fastpath() {
      return false;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class SSSMWAR<K, V> extends SSSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMWAW<K, V> extends SSSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSSMWAWR<K, V> extends SSSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSSMWR<K, V> extends SSSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    SSSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSSMWW<K, V> extends SSSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    SSSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class SSSMWWR<K, V> extends SSSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class SSSR<K, V> extends SSS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class SSSW<K, V> extends SSS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSSWR<K, V> extends SSSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class SSW<K, V> extends SS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    SSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class SSWR<K, V> extends SSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    SSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WI<K, V> extends BoundedLocalCache<K, V> {
    private final ReferenceQueue<K> keyReferenceQueue = new ReferenceQueue<K>();

    private final ReferenceQueue<V> valueReferenceQueue = new ReferenceQueue<V>();

    WI(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
    }

    protected final ReferenceQueue<K> keyReferenceQueue() {
      return keyReferenceQueue;
    }

    protected final boolean collectKeys() {
      return true;
    }

    protected final ReferenceQueue<V> valueReferenceQueue() {
      return valueReferenceQueue;
    }

    protected final boolean collectValues() {
      return true;
    }
  }

  static class WIA<K, V> extends WI<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WIA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WIAR<K, V> extends WIA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIAW<K, V> extends WIA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WIAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WIAWR<K, V> extends WIAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILi<K, V> extends WI<K, V> {
    private final RemovalListener<K, V> removalListener;

    WILi(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.removalListener = builder.getRemovalListener(async);
    }

    public final RemovalListener<K, V> removalListener() {
      return removalListener;
    }

    public final boolean hasRemovalListener() {
      return true;
    }
  }

  static class WILiA<K, V> extends WILi<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WILiAR<K, V> extends WILiA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiAW<K, V> extends WILiA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiAWR<K, V> extends WILiAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMS<K, V> extends WILi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiMSA<K, V> extends WILiMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WILiMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WILiMSAR<K, V> extends WILiMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMSAW<K, V> extends WILiMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiMSAWR<K, V> extends WILiMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiMSR<K, V> extends WILiMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WILiMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMSW<K, V> extends WILiMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiMSWR<K, V> extends WILiMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMW<K, V> extends WILi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiMWA<K, V> extends WILiMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WILiMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WILiMWAR<K, V> extends WILiMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMWAW<K, V> extends WILiMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiMWAWR<K, V> extends WILiMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiMWR<K, V> extends WILiMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WILiMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiMWW<K, V> extends WILiMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiMWWR<K, V> extends WILiMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiR<K, V> extends WILi<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiS<K, V> extends WILi<K, V> {
    private final StatsCounter statsCounter;

    WILiS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class WILiSA<K, V> extends WILiS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WILiSAR<K, V> extends WILiSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSAW<K, V> extends WILiSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiSAWR<K, V> extends WILiSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMS<K, V> extends WILiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiSMSA<K, V> extends WILiSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WILiSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WILiSMSAR<K, V> extends WILiSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMSAW<K, V> extends WILiSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiSMSAWR<K, V> extends WILiSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiSMSR<K, V> extends WILiSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WILiSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMSW<K, V> extends WILiSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiSMSWR<K, V> extends WILiSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMW<K, V> extends WILiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WILiSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiSMWA<K, V> extends WILiSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WILiSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WILiSMWAR<K, V> extends WILiSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMWAW<K, V> extends WILiSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiSMWAWR<K, V> extends WILiSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiSMWR<K, V> extends WILiSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WILiSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiSMWW<K, V> extends WILiSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WILiSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WILiSMWWR<K, V> extends WILiSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WILiSR<K, V> extends WILiS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WILiSW<K, V> extends WILiS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WILiSWR<K, V> extends WILiSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WILiW<K, V> extends WILi<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WILiW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WILiWR<K, V> extends WILiW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WILiWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMS<K, V> extends WI<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WIMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WIMSA<K, V> extends WIMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WIMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WIMSAR<K, V> extends WIMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMSAW<K, V> extends WIMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WIMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WIMSAWR<K, V> extends WIMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WIMSR<K, V> extends WIMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WIMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMSW<K, V> extends WIMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WIMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WIMSWR<K, V> extends WIMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMW<K, V> extends WI<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WIMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WIMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WIMWA<K, V> extends WIMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WIMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WIMWAR<K, V> extends WIMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMWAW<K, V> extends WIMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WIMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WIMWAWR<K, V> extends WIMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WIMWR<K, V> extends WIMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WIMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIMWW<K, V> extends WIMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WIMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WIMWWR<K, V> extends WIMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WIR<K, V> extends WI<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WIR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WIS<K, V> extends WI<K, V> {
    private final StatsCounter statsCounter;

    WIS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class WISA<K, V> extends WIS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WISA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WISAR<K, V> extends WISA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISAW<K, V> extends WISA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WISAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WISAWR<K, V> extends WISAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMS<K, V> extends WIS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WISMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WISMSA<K, V> extends WISMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WISMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WISMSAR<K, V> extends WISMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMSAW<K, V> extends WISMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WISMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WISMSAWR<K, V> extends WISMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WISMSR<K, V> extends WISMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WISMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMSW<K, V> extends WISMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WISMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WISMSWR<K, V> extends WISMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMW<K, V> extends WIS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WISMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WISMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WISMWA<K, V> extends WISMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WISMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WISMWAR<K, V> extends WISMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMWAW<K, V> extends WISMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WISMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WISMWAWR<K, V> extends WISMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WISMWR<K, V> extends WISMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WISMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WISMWW<K, V> extends WISMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WISMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WISMWWR<K, V> extends WISMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WISR<K, V> extends WIS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WISR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WISW<K, V> extends WIS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WISW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WISWR<K, V> extends WISW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WISWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WIW<K, V> extends WI<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WIW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WIWR<K, V> extends WIW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WIWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WS<K, V> extends BoundedLocalCache<K, V> {
    private final ReferenceQueue<K> keyReferenceQueue = new ReferenceQueue<K>();

    WS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
    }

    protected final ReferenceQueue<K> keyReferenceQueue() {
      return keyReferenceQueue;
    }

    protected final boolean collectKeys() {
      return true;
    }
  }

  static class WSA<K, V> extends WS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSAR<K, V> extends WSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSAW<K, V> extends WSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSAWR<K, V> extends WSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLi<K, V> extends WS<K, V> {
    private final RemovalListener<K, V> removalListener;

    WSLi(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.removalListener = builder.getRemovalListener(async);
    }

    public final RemovalListener<K, V> removalListener() {
      return removalListener;
    }

    public final boolean hasRemovalListener() {
      return true;
    }
  }

  static class WSLiA<K, V> extends WSLi<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSLiAR<K, V> extends WSLiA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiAW<K, V> extends WSLiA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiAWR<K, V> extends WSLiAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMS<K, V> extends WSLi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiMSA<K, V> extends WSLiMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSLiMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSLiMSAR<K, V> extends WSLiMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMSAW<K, V> extends WSLiMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiMSAWR<K, V> extends WSLiMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiMSR<K, V> extends WSLiMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSLiMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMSW<K, V> extends WSLiMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiMSWR<K, V> extends WSLiMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMW<K, V> extends WSLi<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiMWA<K, V> extends WSLiMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSLiMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSLiMWAR<K, V> extends WSLiMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMWAW<K, V> extends WSLiMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiMWAWR<K, V> extends WSLiMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiMWR<K, V> extends WSLiMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSLiMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiMWW<K, V> extends WSLiMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiMWWR<K, V> extends WSLiMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiR<K, V> extends WSLi<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiS<K, V> extends WSLi<K, V> {
    private final StatsCounter statsCounter;

    WSLiS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class WSLiSA<K, V> extends WSLiS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSLiSAR<K, V> extends WSLiSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSAW<K, V> extends WSLiSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiSAWR<K, V> extends WSLiSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMS<K, V> extends WSLiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiSMSA<K, V> extends WSLiSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSLiSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSLiSMSAR<K, V> extends WSLiSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMSAW<K, V> extends WSLiSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiSMSAWR<K, V> extends WSLiSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiSMSR<K, V> extends WSLiSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSLiSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMSW<K, V> extends WSLiSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiSMSWR<K, V> extends WSLiSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMW<K, V> extends WSLiS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSLiSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiSMWA<K, V> extends WSLiSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSLiSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSLiSMWAR<K, V> extends WSLiSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMWAW<K, V> extends WSLiSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiSMWAWR<K, V> extends WSLiSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiSMWR<K, V> extends WSLiSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSLiSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiSMWW<K, V> extends WSLiSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSLiSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSLiSMWWR<K, V> extends WSLiSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSLiSR<K, V> extends WSLiS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSLiSW<K, V> extends WSLiS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSLiSWR<K, V> extends WSLiSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSLiW<K, V> extends WSLi<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSLiW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSLiWR<K, V> extends WSLiW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSLiWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMS<K, V> extends WS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSMSA<K, V> extends WSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSMSAR<K, V> extends WSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMSAW<K, V> extends WSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSMSAWR<K, V> extends WSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSMSR<K, V> extends WSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMSW<K, V> extends WSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSMSWR<K, V> extends WSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMW<K, V> extends WS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSMWA<K, V> extends WSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSMWAR<K, V> extends WSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMWAW<K, V> extends WSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSMWAWR<K, V> extends WSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSMWR<K, V> extends WSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSMWW<K, V> extends WSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSMWWR<K, V> extends WSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSR<K, V> extends WS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSS<K, V> extends WS<K, V> {
    private final StatsCounter statsCounter;

    WSS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.statsCounter = builder.getStatsCounterSupplier().get();
    }

    public final boolean isRecordingStats() {
      return true;
    }

    public final Ticker statsTicker() {
      return Ticker.systemTicker();
    }

    public final StatsCounter statsCounter() {
      return statsCounter;
    }
  }

  static class WSSA<K, V> extends WSS<K, V> {
    private final Ticker ticker;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private volatile long expiresAfterAccessNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSSAR<K, V> extends WSSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSAW<K, V> extends WSSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSSAWR<K, V> extends WSSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMS<K, V> extends WSS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMS.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSSMS(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSSMSA<K, V> extends WSSMS<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSSMSA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSSMSAR<K, V> extends WSSMSA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMSAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMSAW<K, V> extends WSSMSA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSSMSAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSSMSAWR<K, V> extends WSSMSAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMSAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSSMSR<K, V> extends WSSMS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSSMSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMSW<K, V> extends WSSMS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSSMSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSSMSWR<K, V> extends WSSMSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMW<K, V> extends WSS<K, V> {
    protected static final long MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "maximum");

    protected static final long EDEN_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "edenMaximum");

    protected static final long MAIN_PROTECTED_MAXIMUM_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "mainProtectedMaximum");

    protected static final long WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "weightedSize");

    protected static final long EDEN_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "edenWeightedSize");

    protected static final long MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET = UnsafeAccess.objectFieldOffset(WSSMW.class, "mainProtectedWeightedSize");

    private volatile long maximum;

    private volatile long edenMaximum;

    private volatile long mainProtectedMaximum;

    private volatile long weightedSize;

    private volatile long edenWeightedSize;

    private volatile long mainProtectedWeightedSize;

    private final FrequencySketch<K> sketch;

    private final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque;

    private final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSSMW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.sketch = new FrequencySketch<K>();
      if (builder.hasInitialCapacity()) {
        long capacity = Math.min(builder.getMaximum(), builder.getInitialCapacity());
        this.sketch.ensureCapacity(capacity);
      }
      this.accessOrderEdenDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProbationDeque = new AccessOrderDeque<Node<K, V>>();
      this.accessOrderProtectedDeque = new AccessOrderDeque<Node<K, V>>();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    protected final boolean evicts() {
      return true;
    }

    protected final long maximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAXIMUM_OFFSET);
    }

    protected final void lazySetMaximum(long maximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAXIMUM_OFFSET, maximum);
    }

    protected final long edenMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_MAXIMUM_OFFSET);
    }

    protected final void lazySetEdenMaximum(long edenMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_MAXIMUM_OFFSET, edenMaximum);
    }

    protected final long mainProtectedMaximum() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET);
    }

    protected final void lazySetMainProtectedMaximum(long mainProtectedMaximum) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_MAXIMUM_OFFSET, mainProtectedMaximum);
    }

    protected final long weightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetWeightedSize(long weightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, WEIGHTED_SIZE_OFFSET, weightedSize);
    }

    protected final long edenWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, EDEN_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetEdenWeightedSize(long edenWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, EDEN_WEIGHTED_SIZE_OFFSET, edenWeightedSize);
    }

    protected final long mainProtectedWeightedSize() {
      return UnsafeAccess.UNSAFE.getLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET);
    }

    protected final void lazySetMainProtectedWeightedSize(long mainProtectedWeightedSize) {
      UnsafeAccess.UNSAFE.putLong(this, MAIN_PROTECTED_WEIGHTED_SIZE_OFFSET, mainProtectedWeightedSize);
    }

    protected final FrequencySketch<K> frequencySketch() {
      return sketch;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderEdenDeque() {
      return accessOrderEdenDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProbationDeque() {
      return accessOrderProbationDeque;
    }

    protected final AccessOrderDeque<Node<K, V>> accessOrderProtectedDeque() {
      return accessOrderProtectedDeque;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSSMWA<K, V> extends WSSMW<K, V> {
    private final Ticker ticker;

    private volatile long expiresAfterAccessNanos;

    WSSMWA(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.expiresAfterAccessNanos = builder.getExpiresAfterAccessNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean expiresAfterAccess() {
      return true;
    }

    protected final long expiresAfterAccessNanos() {
      return expiresAfterAccessNanos;
    }

    protected final void setExpiresAfterAccessNanos(long expiresAfterAccessNanos) {
      this.expiresAfterAccessNanos = expiresAfterAccessNanos;
    }
  }

  static final class WSSMWAR<K, V> extends WSSMWA<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMWAR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMWAW<K, V> extends WSSMWA<K, V> {
    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSSMWAW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSSMWAWR<K, V> extends WSSMWAW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMWAWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSSMWR<K, V> extends WSSMW<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    WSSMWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSSMWW<K, V> extends WSSMW<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    WSSMWW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }
  }

  static final class WSSMWWR<K, V> extends WSSMWW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSMWWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static final class WSSR<K, V> extends WSS<K, V> {
    private final Ticker ticker;

    private volatile long refreshAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSSR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static class WSSW<K, V> extends WSS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSSWR<K, V> extends WSSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }

  static class WSW<K, V> extends WS<K, V> {
    private final Ticker ticker;

    private final WriteOrderDeque<Node<K, V>> writeOrderDeque;

    private volatile long expiresAfterWriteNanos;

    private final MpscGrowableArrayQueue<Runnable> writeBuffer;

    WSW(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.ticker = builder.getTicker();
      this.writeOrderDeque = new WriteOrderDeque<Node<K, V>>();
      this.expiresAfterWriteNanos = builder.getExpiresAfterWriteNanos();
      this.writeBuffer = new MpscGrowableArrayQueue<>(WRITE_BUFFER_MIN, WRITE_BUFFER_MAX);
    }

    public final Ticker expirationTicker() {
      return ticker;
    }

    protected final WriteOrderDeque<Node<K, V>> writeOrderDeque() {
      return writeOrderDeque;
    }

    protected final boolean expiresAfterWrite() {
      return true;
    }

    protected final long expiresAfterWriteNanos() {
      return expiresAfterWriteNanos;
    }

    protected final void setExpiresAfterWriteNanos(long expiresAfterWriteNanos) {
      this.expiresAfterWriteNanos = expiresAfterWriteNanos;
    }

    protected final MpscGrowableArrayQueue<Runnable> writeBuffer() {
      return writeBuffer;
    }

    protected final boolean buffersWrites() {
      return true;
    }
  }

  static final class WSWR<K, V> extends WSW<K, V> {
    private volatile long refreshAfterWriteNanos;

    WSWR(Caffeine<K, V> builder, CacheLoader<? super K, V> cacheLoader, boolean async) {
      super(builder, (CacheLoader<K, V>) cacheLoader, async);
      this.refreshAfterWriteNanos = builder.getRefreshAfterWriteNanos();
    }

    protected final boolean refreshAfterWrite() {
      return true;
    }

    protected final long refreshAfterWriteNanos() {
      return refreshAfterWriteNanos;
    }

    protected final void setRefreshAfterWriteNanos(long refreshAfterWriteNanos) {
      this.refreshAfterWriteNanos = refreshAfterWriteNanos;
    }
  }
}
