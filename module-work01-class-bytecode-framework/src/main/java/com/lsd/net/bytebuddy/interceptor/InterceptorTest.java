package com.lsd.net.bytebuddy.interceptor;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: nhsoft.lsd
 */
public class InterceptorTest {

    public static void main(String args[]) throws IllegalAccessException, InstantiationException {
        MemoryDatabase loggingDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(ElementMatchers.named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(InterceptorTest.class.getClassLoader())
                .getLoaded()
                .newInstance();

        loggingDatabase.load("nhsoft.lsd");
    }
}
