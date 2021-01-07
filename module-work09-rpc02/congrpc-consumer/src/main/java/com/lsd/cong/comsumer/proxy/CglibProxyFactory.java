package com.lsd.cong.comsumer.proxy;

import com.lsd.cong.core.config.SPIType;
import com.lsd.cong.core.consumer.LoadBalance;
import com.lsd.cong.core.consumer.Router;
import com.lsd.cong.core.exception.CongRpcException;
import com.lsd.cong.comsumer.OkHttpClientExchanger;
import com.lsd.cong.comsumer.ProxyFactory;
import com.lsd.cong.comsumer.ServiceDiscovery;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerClientInterceptor;

/**
 * @Author: nhsoft.lsd
 */
public class CglibProxyFactory extends HttpInvokerClientInterceptor implements ProxyFactory, FactoryBean<Object>, InitializingBean {

    private Object proxy;

    @Autowired
    private ServiceDiscovery serviceDiscovery;

    @Autowired
    private LoadBalance loadBalance;

    @Autowired
    private Router router;

    @Override
    public String getSPIType() {
        return SPIType.PROXY_CGLIB;
    }

    @Override
    public <T> T getProxy() throws CongRpcException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getServiceInterface());
        enhancer.setCallbacks(new Callback[]{new OkHttpClientExchanger(serviceDiscovery, loadBalance, router, this.getServiceInterface().getName())});
        return (T)enhancer.create();

    }

    @Override
    public Object getObject() throws Exception {
        return proxy;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getServiceInterface();
    }

    @Override
    public void afterPropertiesSet() {
        this.proxy = getProxy();
    }


}
