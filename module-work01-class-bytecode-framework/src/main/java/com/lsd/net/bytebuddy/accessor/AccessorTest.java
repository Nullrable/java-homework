package com.lsd.net.bytebuddy.accessor;

import com.lsd.net.bytebuddy.simple.Utils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: nhsoft.lsd
 */
public class AccessorTest {

    public static void main(String args[]) throws IllegalAccessException, InstantiationException {


        DynamicType.Loaded<UserType> dynamicUserLoaded = new ByteBuddy()
                .subclass(UserType.class)
                .method(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.toField("interceptor"))
                .defineField("interceptor", Interceptor.class, Visibility.PRIVATE)
                .implement(InterceptionAccessor.class).intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(AccessorTest.class.getClassLoader());

        Class<? extends UserType> dynamicUserType =
                dynamicUserLoaded.getLoaded();

        Utils.outputClazz(dynamicUserLoaded.getBytes(), "t1.class");

        DynamicType.Loaded<InstanceCreator> loaded =  new ByteBuddy()
                .subclass(InstanceCreator.class)
                .method(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class)))
                .intercept(MethodDelegation.toConstructor(dynamicUserType))
                .make()
                .load(dynamicUserType.getClassLoader());

        Utils.outputClazz(loaded.getBytes(), "t2.class");

        InstanceCreator factory = loaded.getLoaded().newInstance();

        UserType userType = (UserType) factory.makeInstance();
        ((InterceptionAccessor) userType).setInterceptor(new HelloWorldInterceptor());
        System.out.println(userType.doSomething());

    }
}
