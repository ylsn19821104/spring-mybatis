package org.apache.directmemory.memory;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.limit;
import static com.google.common.collect.Ordering.from;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Predicate;

public abstract class AbstractMemoryManager<V>
{

    protected static final long NEVER_EXPIRES = 0L;

    protected final Set<Pointer<V>> pointers = Collections.newSetFromMap( new ConcurrentHashMap<Pointer<V>, Boolean>() );

    protected boolean returnNullWhenFull = true;

    protected final AtomicLong used = new AtomicLong( 0L );

    public AbstractMemoryManager()
    {
        super();
    }

    abstract public Pointer<V> store( byte[] payload, long expiresIn );

    public Pointer<V> store( byte[] payload )
    {
        return store( payload, 0 );
    }

    abstract public Pointer<V> free( Pointer<V> pointer );

    public Pointer<V> update( Pointer<V> pointer, byte[] payload )
    {
        if ( pointer.getCapacity() >= payload.length )
        {
            pointer.reset();
            pointer.setFree( false );
            pointer.getMemoryBuffer().writeBytes( payload );
            pointer.hit();
            return pointer;
        }
        else
        {
            free( pointer );
            return store( payload );
        }
    }

    public long used()
    {
        return used.get();
    }

    public long collectExpired()
    {
        int limit = 50;
        return free( limit( filter( pointers, relative ), limit ) )
            + free( limit( filter( pointers, absolute ), limit ) );

    }

    final Predicate<Pointer<V>> relative = new Predicate<Pointer<V>>()
    {

        @Override
        public boolean apply( Pointer<V> input )
        {
            return !input.isFree() && input.isExpired();
        }

    };

    final Predicate<Pointer<V>> absolute = new Predicate<Pointer<V>>()
    {

        @Override
        public boolean apply( Pointer<V> input )
        {
            return !input.isFree() && input.isExpired();
        }

    };

    public void collectLFU()
    {

        int limit = pointers.size() / 10;

        Iterable<Pointer<V>> result = from( new Comparator<Pointer<V>>()
        {

            @Override
            public int compare( Pointer<V> o1, Pointer<V> o2 )
            {
                float f1 = o1.getFrequency();
                float f2 = o2.getFrequency();

                return Float.compare( f1, f2 );
            }

        } ).sortedCopy( limit( filter( pointers, new Predicate<Pointer<V>>()
        {

            @Override
            public boolean apply( Pointer<V> input )
            {
                return !input.isFree();
            }

        } ), limit ) );

        free( result );

    }

    protected long free( Iterable<Pointer<V>> pointers )
    {
        long howMuch = 0;
        for ( Pointer<V> expired : pointers )
        {
            howMuch += expired.getCapacity();
            free( expired );
        }
        return howMuch;
    }

    protected boolean returnsNullWhenFull()
    {
        return returnNullWhenFull;
    }

    public Set<Pointer<V>> getPointers()
    {
        return Collections.unmodifiableSet( pointers );
    }

    public <T extends V> Pointer<V> allocate( final Class<T> type, final int size, final long expiresIn,
                                              final long expires )
    {

        Pointer<V> p = store( new byte[size], expiresIn );
        if ( p != null && p.getMemoryBuffer() != null )
            p.getMemoryBuffer().clear();

        if ( p != null )
            p.setClazz( type );

        return p;
    }

}