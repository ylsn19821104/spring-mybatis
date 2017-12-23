package com.shihui.test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterDemo {
    class Data {
        public volatile int v1 = 1;
        volatile int v2 = 2;
        protected volatile int v3 = 3;
        private volatile int v4 = 4;
    }

    AtomicIntegerFieldUpdater<Data> getUpdater(String n) {
        return AtomicIntegerFieldUpdater.newUpdater(Data.class, n);
    }

    void doIt() {
        Data data = new Data();
        System.err.println("1===>" + getUpdater("v1").getAndSet(data, 10));
        System.err.println("1===>" + getUpdater("v1").get(data));
        System.err.println("2===>" + getUpdater("v2").incrementAndGet(data));
        System.err.println("3===>" + getUpdater("v3").decrementAndGet(data));
        System.err.println("4===>" + getUpdater("v4").compareAndSet(data, 4, 5));
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        demo.doIt();
    }
}
