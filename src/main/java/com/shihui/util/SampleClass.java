package com.shihui.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class SampleClass {
    private int i;
    private long l;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public long getL() {
        return l;
    }

    public void setL(long l) {
        this.l = l;
    }

    static Unsafe unsafe = ClassIntrospector.getUnsafe();


    private static Object helperArray[] = new Object[1];

    /**
     * 获取对象起始位置偏移量
     *
     * @return
     */
    public static long getObjectAddress(Object object) {
        helperArray[0] = object;
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        return unsafe.getLong(helperArray, baseOffset);
    }

    private final static ClassIntrospector ci = new ClassIntrospector();

    /**
     * 获取Object的大小
     *
     * @param object
     * @return
     */
    public static long getObjectSize(Object object) {
        ObjectInfo res = null;
        try {
            res = ci.introspect(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res.getDeepSize();
    }

    public static void main(String[] args) throws NoSuchFieldException {
        SampleClass sampleClass = new SampleClass();
        sampleClass.setI(88);
        sampleClass.setL(999999999L);
        Field f = SampleClass.class.getDeclaredField("i");
        long iFiledAddressShift = unsafe.objectFieldOffset(f);

        SampleClass sampleClassCopy = new SampleClass();
        long copyAddress = getObjectAddress(sampleClassCopy);
        unsafe.copyMemory(sampleClass, 0, null, copyAddress, getObjectSize(sampleClass));
        int i = unsafe.getInt(copyAddress + iFiledAddressShift);
        System.out.println(i);
        System.out.println(sampleClassCopy.getL());
    }
}
