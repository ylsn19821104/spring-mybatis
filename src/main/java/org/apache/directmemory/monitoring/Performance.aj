package org.apache.directmemory.monitoring;

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

import static java.lang.String.format;

import org.apache.directmemory.cache.Cache;
import org.apache.directmemory.measures.Monitor;
import org.apache.directmemory.measures.MonitorService;
import org.apache.directmemory.memory.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public aspect Performance
{

    public static final String cache_prefix = "cache";

    public static final String cache_putByteArray = cache_prefix + ".putByteArray";

    public static final String cache_retrieveByteArray = cache_prefix + ".retrieveByteArray";

    public static final String cache_getPointer = cache_prefix + ".getPointer";

    public static final String cache_putObject = cache_prefix + ".put";

    public static final String cache_retrieveObject = cache_prefix + ".get";

    public static final String cache_collectLFU = cache_prefix + ".collectLFU";

    public static final String cache_collectExpired = cache_prefix + ".collectExpired";

    public static final String cache_serialize = cache_prefix + ".serializer.serialize";

    public static final String cache_deserialize = cache_prefix + ".serializer.deserialize";

    private static Logger logger = LoggerFactory.getLogger( Cache.class );

    pointcut putByteArrayPointcut( String key, byte[] payload ):
            execution(Pointer org.apache.directmemory.cache.Cache.putByteArray(java.lang.String, byte[])) &&
                    args(key, payload);

    pointcut putObjectPointcut( String key, Object object, int expiresIn ):
            execution(Pointer org.apache.directmemory.cache.Cache.put(java.lang.String, java.lang.Object, int)) &&
                    args(key, object, expiresIn);

    pointcut retrieveByteArrayPointcut( String key ):
            execution(byte[] org.apache.directmemory.cache.Cache.retrieveByteArray(java.lang.String)) &&
                    args(key);

    pointcut retrieveObjectPointcut( String key ):
            execution(java.lang.Object org.apache.directmemory.cache.Cache.retrieve(java.lang.String)) &&
                    args(key);

    pointcut getPointcut( String key ):
            execution(Pointer org.apache.directmemory.cache.Cache.getPointer(java.lang.String)) &&
                    args(key);

    pointcut collectLFUPointcut():
            execution(void org.apache.directmemory.cache.Cache.collectLFU());

    pointcut collectExpiredPointcut():
            execution(void org.apache.directmemory.cache.Cache.collectExpired());

    pointcut serializePointcut( Object obj, @SuppressWarnings( "rawtypes" ) Class clazz ):
            execution(byte[] org.apache.directmemory.serialization.ProtoStuffSerializerV1.serialize(java.lang.Object, java.lang.Class)) &&
                    args(obj, clazz);

    pointcut deserializePointcut( byte[] source, @SuppressWarnings( "rawtypes" ) Class clazz ):
            execution(java.lang.Object org.apache.directmemory.serialization.ProtoStuffSerializerV1.deserialize(byte[], java.lang.Class)) &&
                    args(source, clazz);

    Pointer around( String key, byte[] payload ): putByteArrayPointcut(key, payload) {
        MonitorService mon = Monitor.get( cache_putByteArray );
        final long startedAt = mon.start();
        Pointer entry = proceed( key, payload );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "put: [%s] %d bytes", key, payload.length ) );
        }
        mon.stop( startedAt );
        return entry;
    }

    Pointer around( String key, Object object, int expiresIn ): putObjectPointcut(key, object, expiresIn) {
        MonitorService mon = Monitor.get( cache_putObject );
        final long startedAt = mon.start();
        Pointer entry = proceed( key, object, expiresIn );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "put object: [%s]", key ) );
        }
        mon.stop( startedAt );
        return entry;
    }

    byte[] around( String key ): retrieveByteArrayPointcut(key) {
        MonitorService mon = Monitor.get( cache_retrieveByteArray );
        final long startedAt = mon.start();
        byte[] payload = proceed( key );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "retrieve: [%s]", key ) );
        }
        mon.stop( startedAt );
        return payload;
    }

    Object around( String key ): retrieveObjectPointcut(key) {
        MonitorService mon = Monitor.get( cache_retrieveObject );
        final long startedAt = mon.start();
        Object payload = proceed( key );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "retrieve object: [%s]", key ) );
        }
        mon.stop( startedAt );
        return payload;
    }

    Pointer around( String key ): getPointcut(key) {
        MonitorService mon = Monitor.get( cache_getPointer );
        final long startedAt = mon.start();
        Pointer pointer = proceed( key );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "get: [%s]", key ) );
        }
        mon.stop( startedAt );
        return pointer;
    }

    void around(): collectLFUPointcut() {
        MonitorService mon = Monitor.get( cache_collectLFU );
        final long startedAt = mon.start();
        proceed();
        if ( logger.isDebugEnabled() )
        {
            logger.debug( "collect LFU" );
        }
        mon.stop( startedAt );
    }

    void around(): collectExpiredPointcut() {
        MonitorService mon = Monitor.get( cache_collectExpired );
        final long startedAt = mon.start();
        proceed();
        if ( logger.isDebugEnabled() )
        {
            logger.debug( "collect expired" );
        }
        mon.stop( startedAt );
    }

    byte[] around( Object obj, @SuppressWarnings( "rawtypes" ) Class clazz ): serializePointcut(obj, clazz) {
        MonitorService mon = Monitor.get( cache_serialize );
        final long startedAt = mon.start();
        byte[] payload = proceed( obj, clazz );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "serialize: [%s]", clazz.getSimpleName() ) );
        }
        mon.stop( startedAt );
        return payload;
    }

    Object around( byte[] source, @SuppressWarnings( "rawtypes" ) Class clazz ): deserializePointcut(source, clazz) {
        MonitorService mon = Monitor.get( cache_deserialize );
        final long startedAt = mon.start();
        Object obj = proceed( source, clazz );
        if ( logger.isDebugEnabled() )
        {
            logger.debug( format( "deserialize: [%s]", clazz.getSimpleName() ) );
        }
        mon.stop( startedAt );
        return obj;
    }

}
