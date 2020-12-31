package com.lsd.jboss.javassist.test4buddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author: nhsoft.lsd
 */
public class HelloWorld {

    public static void main (String args[]) throws IllegalAccessException, InstantiationException {

        String helloWorld = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(HelloWorld.class.getClassLoader())
                .getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);  // Hello World!

    }
}
