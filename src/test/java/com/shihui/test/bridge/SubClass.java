package com.shihui.test.bridge;


import java.lang.reflect.Method;

public class SubClass implements SuperClass<String> {
    @Override
    public String method(String param) {
        return "bridge test";
    }

    public static void main(String[] args) {
        Method[] methods = SubClass.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isBridge()) {
                System.err.println("" + method.getName() +
                        ";" + method.getParameterTypes()[0]);
            }
        }
    }
}
