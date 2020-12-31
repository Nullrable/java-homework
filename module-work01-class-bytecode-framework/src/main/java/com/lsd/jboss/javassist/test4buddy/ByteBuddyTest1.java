package com.lsd.jboss.javassist.test4buddy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author: nhsoft.lsd
 */
public class ByteBuddyTest1 {

    public static void main (String args[]) throws IllegalAccessException, InstantiationException {


        ByteBuddy byteBuddy = new ByteBuddy();

        DynamicType.Builder builder = byteBuddy.subclass(Object.class);

        DynamicType.Loaded loaded = builder
                .name("com.lsd.jboss.javassist.test4buddy.TestHelloWord")
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyTest1.class.getClassLoader());

        Utils.outputClazz(loaded.getBytes());

        String helloWorld =
                loaded.getLoaded()
                .newInstance()
                .toString();

        System.out.println(helloWorld);  // Hello World!

    }


}
