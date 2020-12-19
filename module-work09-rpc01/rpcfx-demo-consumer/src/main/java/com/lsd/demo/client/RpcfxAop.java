package com.lsd.demo.client;

import com.lsd.rpcfx.core.client.InvokerMetadata;
import com.lsd.rpcfx.core.client.RpcfxClient;
import com.lsd.rpcfx.core.client.RpcfxClientNetty;
import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 10:48 PM
 * @Modified By：
 */
@Component("RpcfxAop")
public class RpcfxAop implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        if (!(mi instanceof ProxyMethodInvocation)) {
            throw new IllegalStateException("MethodInvocation is not a Spring ProxyMethodInvocation: " + mi);
        } else {

            RpcfxClient rpcfxClientAop = new RpcfxClientNetty();

            ProxyMethodInvocation pmi = (ProxyMethodInvocation)mi;

            Method method =  pmi.getMethod();

            Class clazz =  method.getDeclaringClass();
            Object[] args = pmi.getArguments();

            InvokerMetadata metadata = new InvokerMetadata();
            metadata.setServiceClass(clazz.getName());
            metadata.setArgs(args);
            metadata.setMethodName(method.getName());
            metadata.setMediaType("application/json; charset=utf-8");
            metadata.setServerUrl("127.0.0.1:8080");

            return rpcfxClientAop.invoke(metadata);
//            return rpcfxClientAop.execute(pjp);
        }
    }
}
