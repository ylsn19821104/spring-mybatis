package com.shihui.test.proxy;

public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello(String name) {
        System.err.println("hello " + name);
    }
}
