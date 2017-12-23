package com.shihui.test.cache;

import org.apache.directmemory.DirectMemory;
import org.apache.directmemory.cache.Cache;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hongxp on 2017/11/10.
 * 堆外缓存测试
 */
public class DirectMemoryCacheTest {
    @Test
    public void test() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InterruptedException {
         /*
         * numberOfBuffers 缓冲区数目
         * size  缓存大小
         * initialCapacity 初始容量
         * concurrencyLevel 并发级别，guava new MapMaker().concurrencyLevel(4)
         */
        Cache.init(10, 100, DirectMemory.DEFAULT_INITIAL_CAPACITY, DirectMemory.DEFAULT_CONCURRENCY_LEVEL);
        Cache.put("key1", "abc");
        Cache.put("key2", 123);
        Cache.put("key3", new Date());
        Cache.put("key4", "abc", 1000);
        Cache.put("key1", "abc");
        Cache.put("key2", 123);
        Cache.put("key3", new Date());
        Cache.put("key4", "abc", 1000);

        Class c = Class.forName("java.nio.Bits");
        Field maxMemoryField = c.getDeclaredField("maxMemory");
        maxMemoryField.setAccessible(true);
        Field reservedMemoryField = c.getDeclaredField("reservedMemory");
        reservedMemoryField.setAccessible(true);
        long maxMemoryValue = (long) maxMemoryField.get(null);
        AtomicLong reservedMemoryValue = (AtomicLong) reservedMemoryField.get(null);
        System.err.println("maxMemoryValue:" + maxMemoryValue);
        System.err.println("reservedMemoryValue:" + reservedMemoryValue.longValue());

        System.err.println("key1=" + Cache.retrieve("key1"));
        System.err.println("key2=" + Cache.retrieve("key2"));
        System.err.println("key3=" + Cache.retrieve("key3"));
        System.err.println("key4=" + Cache.retrieve("key4"));

        Thread.sleep(1000);
        System.err.println("******************************************");
        System.err.println("key1=" + Cache.retrieve("key1"));
        System.err.println("key4=" + Cache.retrieve("key4"));

    }
}
