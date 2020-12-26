package com.lsd.rpcfx.core;

import com.lsd.rpcfx.core.api.LoadBalancer;
import com.lsd.rpcfx.core.api.Router;
import com.lsd.rpcfx.core.api.SimpleLoadBalancer;
import com.lsd.rpcfx.core.api.SimpleRouter;
import com.lsd.rpcfx.core.common.config.ServiceNodeConfig;
import com.lsd.rpcfx.core.common.serializer.MyZkSerializer;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nhsoft.lsd
 */
@Configuration
public class RpcfxStarter {

    @Autowired
    private ServiceNodeConfig serviceNodeConfig;

    @Bean
    @ConditionalOnMissingBean(ZkClient.class)
    public ZkClient zkClient(){

        ZkClient zkClient = new ZkClient(serviceNodeConfig.getServerUrl());
        zkClient.setZkSerializer(new MyZkSerializer());
        return zkClient;

    }

    @Bean
    @ConditionalOnMissingBean(Router.class)
    public Router router(){
        Router router = new SimpleRouter();
        return router;
    }

    @Bean
    @ConditionalOnMissingBean(LoadBalancer.class)
    public LoadBalancer loadBalancer(){
        LoadBalancer loadBalancer = new SimpleLoadBalancer();
        return loadBalancer;
    }
}
