package com.shihui.test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by hongxp on 2017/11/10.
 */
public class UnSafeTest {
    @Test
    public void testInitPrivateClass() {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);

            Player player = (Player) unsafe.allocateInstance(Player.class);
            player.setName("lili");
            System.err.println(player.getName());

            for (Field field : Player.class.getDeclaredFields()) {
                System.err.println(f.getName() + ":对应的内存偏移地址" + unsafe.objectFieldOffset(field));
            }

            System.err.println("============================");
            int ageOffset = 8;
            System.err.println(unsafe.compareAndSwapInt(player, ageOffset, 18, 20));
            System.err.println("age修改后的值:" + player.getAge());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    class Player {

        private String name;
        private int age;

        private Player() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
