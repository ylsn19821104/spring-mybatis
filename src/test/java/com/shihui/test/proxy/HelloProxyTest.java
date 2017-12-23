package com.shihui.test.proxy;

import org.junit.Test;

public class HelloProxyTest {
    @Test
    public void test() {
        HelloServiceProxy helloHandler = new HelloServiceProxy();
        HelloService proxy = (HelloService) helloHandler.bind(new HelloServiceImpl());
        proxy.sayHello("张三");
    }
}
