package com.lsd.cong.comsumer;

import com.lsd.cong.core.consumer.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @Author: nhsoft.lsd
 */
public class OkHttpClientExchanger implements ClientExchanger, MethodInterceptor {

    private final ServiceDiscovery serviceDiscovery;

    private final LoadBalance loadBalance;

    private final Router router;

    private final String serviceName;

    public OkHttpClientExchanger(ServiceDiscovery serviceDiscovery, LoadBalance loadBalance, Router router, String serviceName) {
        this.serviceDiscovery = serviceDiscovery;
        this.loadBalance = loadBalance;
        this.router = router;
        this.serviceName = serviceName;
    }

    @Override
    public String getSPIType() {
        return com.lsd.cong.core.config.SPIType.CLIENT_EXCHANGE_OK_HTTP;
    }

    @Override
    public Object exchange(Invoker invoker, Invocation invocation) throws Throwable {

        return invoker.invoke(invocation);

    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        Invocation invocation = new Invocation();
        invocation.setMethod(method.getName());
        invocation.setArguments(args);
        invocation.setServiceName(serviceName);
        invocation.setMediaType("application/json; charset=utf-8");

        Invoker invoker = getInvoker(invocation);

        invocation.setServiceUrl(invoker.getUrl().getURL());

        return exchange(invoker, invocation);
    }

    private Invoker getInvoker(Invocation invocation) {

        List<URL> urls = serviceDiscovery.getURL(serviceName);

        // Dictionary
        List<Invoker> invokers = toInvokers(urls);

        // Router
        invokers = router.route(invokers, invocation);

        // LoadBalance
        Invoker invoker = loadBalance.select(invokers, invocation);

        return invoker;
    }

    private List<Invoker> toInvokers(List<URL> urls) {

        if (urls == null)
            return null;

        List<Invoker> invokers = new ArrayList<>();
        urls.forEach(url -> {
            OkHttpInvoker httpInvoker = new OkHttpInvoker();
            httpInvoker.setUrl(url);
            invokers.add(httpInvoker);
        });

        return invokers;

    }
}
