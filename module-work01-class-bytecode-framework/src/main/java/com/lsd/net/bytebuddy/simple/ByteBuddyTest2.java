package com.lsd.net.bytebuddy.simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: nhsoft.lsd
 */
public class ByteBuddyTest2 {

    public static void main (String args[]) throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        testByteBuddyAgent();
    }


    /**
     * 4. 方法代理和自定义方法逻辑
     */
    public static void testMethodDelegation() throws IllegalAccessException, InstantiationException {

        DynamicType.Loaded<Foo> loaded = new ByteBuddy()
                .with(new NamingStrategy.SuffixingRandom("suffix"))
                .subclass(Foo.class)
                .method(ElementMatchers.named("sayHelloFoo")
                        .and(ElementMatchers.isDeclaredBy(Foo.class)
                                .and(ElementMatchers.returns(String.class))))
                .intercept(MethodDelegation.to(Bar.class))
                .make()
                .load(ByteBuddyTest2.class.getClassLoader());

        Utils.outputClazz(loaded.getBytes());

        String r = loaded
                .getLoaded()
                .newInstance()
                .sayHelloFoo();

        System.out.println(r);
        System.out.println(Bar.sayHelloBar());
    }

    /**
     * 5. 方法和字段定义
     */
    public static void testDefineMethodAndField() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?> type = new ByteBuddy()
                .subclass(Object.class)
                .name("MyClassName")
                .defineMethod("custom", String.class, Modifier.PUBLIC).withParameters(String.class) // public String custom(String arg);
                .intercept(MethodDelegation.to(Bar.class))
                .defineField("x", String.class, Modifier.PUBLIC)
                .make()
                .load( ByteBuddyTest2.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        Method m = type.getDeclaredMethod("custom", String.class);
        System.out.println(m.invoke(type.newInstance(), "Test"));
        System.out.println(type.getDeclaredField("x"));

    }

    /**
     * 重定义一个已经存在的类
     */

    public static void testByteBuddyAgent() {

        net.bytebuddy.agent.ByteBuddyAgent.install();
        DynamicType.Loaded<Foo> loaded =  new ByteBuddy()
//                .rebase() 与 redefine 区别在于，redefine 直接替换原方法，而rebase会保留原方法，原方法会改名并改为private
                .redefine(Foo.class)
                .method(ElementMatchers.named("sayHelloFoo"))
                .intercept(FixedValue.value("Hello Foo Redefined")) //这里的实现是返回一个固定的值"Hello Foo Redefined"
                .make()
                .load(Foo.class.getClassLoader(),
                        ClassReloadingStrategy.fromInstalledAgent());

        Utils.outputClazz(loaded.getBytes());

        Foo f = new Foo();

        System.out.println(f.sayHelloFoo());

    }
}
