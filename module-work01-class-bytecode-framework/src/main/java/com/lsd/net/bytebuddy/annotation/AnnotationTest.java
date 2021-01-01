package com.lsd.net.bytebuddy.annotation;


import net.bytebuddy.ByteBuddy;

/**
 * @Author: nhsoft.lsd
 */
public class AnnotationTest {

    public static void main(String args[]) throws IllegalAccessException, InstantiationException {

        new ByteBuddy()
                .subclass(Object.class)
                .annotateType(new RuntimeDefinitionImpl())
                .make()
                .load(AnnotationTest.class.getClassLoader());

    }
}
