package com.shihui.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    private int state;
    private static Unsafe UNSAFE = getUnsafe();

    public static void main(String[] args) throws NoSuchFieldException {
        Field field = UnsafeTest.class.getDeclaredField("state");
        long offset = UNSAFE.objectFieldOffset(field);
        UnsafeTest test = new UnsafeTest();
        UNSAFE.compareAndSwapInt(test, offset, 0, 1);
        System.err.println("offset:" + offset + ",state:" + test.state);

        //获取对象的偏移地址，需要将目标对象设为辅助数组的第一个元素（也是唯一的元素）。由于这是一个复杂类型元素（不是基本数据类型），
        // 它的地址存储在数组的第一个元素。然后，获取辅助数组的基本偏移量。数组的基本偏移量是指数组对象的起始地址与数组第一个元素之间的偏移量。
        Object[] helperArray = new Object[1];
        helperArray[0] = test;
        long baseOffset = UNSAFE.arrayBaseOffset(Object[].class);
        long addressOfTest = UNSAFE.getLong(helperArray, baseOffset);
        long iFiledAddressShift = UNSAFE.objectFieldOffset(field);
        int i = UNSAFE.getInt(addressOfTest + iFiledAddressShift);
        System.out.println(new StringBuilder().append(" Field I Address:").append(addressOfTest)
                .append("+").append(iFiledAddressShift).append(" Value:").append(i));
    }

    //直接调用Unsafe.getUnsafe()报错 throw new SecurityException("Unsafe")
    //Unsafe提供了一个私有的静态实例，并且通过检查classloader是否为null来避免java程序直接使用unsafe
    public static Unsafe getUnsafe() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
