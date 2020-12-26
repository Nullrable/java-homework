package com.lsd.demo.client;

import com.lsd.rpcfx.core.api.LoadBalancer;
import com.lsd.rpcfx.core.api.Router;
import com.lsd.rpcfx.core.api.SimpleLoadBalancer;
import com.lsd.rpcfx.core.api.SimpleRouter;
import com.lsd.rpcfx.core.client.InvokerMetadata;
import com.lsd.rpcfx.core.client.RpcfxClient;
import com.lsd.rpcfx.core.client.RpcfxClientNetty;
import com.lsd.rpcfx.core.client.discover.ZookeeperServiceDiscover;
import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.lang.reflect.Method;
import java.util.List;
import org.I0Itec.zkclient.ZkClient;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 10:48 PM
 * @Modified By：
 */
@Component("RpcfxAop")
public class RpcfxAop implements MethodInterceptor {

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private Router router;

    @Autowired
    private LoadBalancer loadBalancer;

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
//


            ZookeeperServiceDiscover zookeeperServiceDiscover = new ZookeeperServiceDiscover(zkClient);
            List<ServiceInfo> serviceInfoList = zookeeperServiceDiscover.getServiceInfos(clazz.getName());


            //可以根据分组什么的过滤
            serviceInfoList = router.route(serviceInfoList);


            ServiceInfo serviceInfo = loadBalancer.select(serviceInfoList);


            InvokerMetadata metadata = new InvokerMetadata();
            metadata.setServiceClass(serviceInfo.getServiceClass());
            metadata.setArgs(args);
            metadata.setMethodName(method.getName());
            metadata.setMediaType("application/json; charset=utf-8");
            metadata.setServerUrl(serviceInfo.getUrl());
            return rpcfxClientAop.invoke(metadata);
        }
    }
}
