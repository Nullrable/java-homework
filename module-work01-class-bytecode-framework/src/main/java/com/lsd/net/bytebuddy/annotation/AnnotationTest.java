package com.lsd.net.bytebuddy.annotation;


import com.lsd.net.bytebuddy.simple.Utils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: nhsoft.lsd
 */
public class AnnotationTest {

    public static void main(String args[]) throws IllegalAccessException, InstantiationException {

        DynamicType.Loaded loaded = new ByteBuddy()
                .subclass(Object.class)
                .annotateType(new RuntimeDefinitionImpl())
                .method(ElementMatchers.named("toString"))
                .intercept(SuperMethodCall.INSTANCE)
                .annotateMethod(new RuntimeDefinitionImpl())
                .defineField("foo", Object.class)
                .annotateField(new RuntimeDefinitionImpl())
                .make()
                .load(AnnotationTest.class.getClassLoader());

        Utils.outputClazz(loaded.getBytes(), "AnnotationTest.class");
    }
}
