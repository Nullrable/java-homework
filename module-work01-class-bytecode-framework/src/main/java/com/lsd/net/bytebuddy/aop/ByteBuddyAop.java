package com.lsd.net.bytebuddy.aop;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: nhsoft.lsd
 */
public class ByteBuddyAop {

    public static void main(String[] args) throws Exception {
        Service service = new ByteBuddy()
                .subclass(Service.class) // 动态生成Service类的子类
                .method(ElementMatchers.any()) // 拦截所有方法
                .intercept(Advice.to(LoggerAdvisor.class)) // 使用LoggerAdvisor类作为拦截器，Advice是AOP的概念，似乎一般翻译为「通知」？
                .make() // 作出
                .load(Service.class.getClassLoader()) // 硬塞给ClassLoader
                .getLoaded() // 拿到Class对象
                .getConstructor() // Class.newInstance() 在Java 9中被废弃了，是个很有意思的故事，有兴趣可以去了解一下
                .newInstance();

        service.bar(123);
        service.foo(456);
    }
}
