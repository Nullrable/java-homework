package com.lsd.modulework05all.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-13 14:26
 * @Modified By：
 */
public class DynamicProxyByJava {


   public static  <T> T create(Class<T> kClass){

        return (T)Proxy.newProxyInstance(kClass.getClassLoader(), kClass.getInterfaces(), new DynamicProxyInvokeHandler(kClass));

    }


}

class DynamicProxyInvokeHandler implements InvocationHandler {

    private Class kClass;

    public DynamicProxyInvokeHandler(Class kClass) {
        this.kClass = kClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("==========>before invoke");
        Object result = method.invoke(kClass.newInstance(), args);  // 调用 target 的 method 方法
        System.out.println("==========>after invoke");
        return result;  // 返回方法的执行结果
    }
}