package com.lsd.modulework05all.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-13 14:42
 * @Modified By：
 */
public class DynamicProxyByCglib {


    public static  <T> T create(Class<T> kClass){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(kClass);
        enhancer.setCallbacks(new Callback[]{new DynamicProxyByCglibInvokeHandler(), new DynamicProxyByCglibInvokeHandler2()});   // 设置多个拦截器，NoOp.INSTANCE是一个空拦截器，不做任何处理
        enhancer.setCallbackFilter(new ServiceFilter());
        return (T)enhancer.create();

    }


}

class DynamicProxyByCglibInvokeHandler implements MethodInterceptor {


    public DynamicProxyByCglibInvokeHandler() {

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("==========>before invoke-1");
        Object result = methodProxy.invokeSuper(o, objects);  // 调用 target 的 method 方法
        System.out.println("==========>after invoke-1");
        return result;  // 返回方法的执行结果
    }
}


class DynamicProxyByCglibInvokeHandler2 implements MethodInterceptor {


    public DynamicProxyByCglibInvokeHandler2() {

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("==========>before invoke-2");
        Object result = methodProxy.invokeSuper(o, objects);  // 调用 target 的 method 方法
        System.out.println("==========>after invoke-2");
        return result;  // 返回方法的执行结果
    }
}
