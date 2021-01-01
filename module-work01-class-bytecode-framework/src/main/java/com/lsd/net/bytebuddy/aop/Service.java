package com.lsd.net.bytebuddy.aop;

/**
 * @Author: nhsoft.lsd
 */
public class Service {

    @Log
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }
}
