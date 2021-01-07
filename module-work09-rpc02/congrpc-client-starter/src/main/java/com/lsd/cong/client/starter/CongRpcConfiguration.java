package com.lsd.cong.client.starter;

import com.lsd.cong.comsumer.ReferenceAutowire;
import com.lsd.cong.comsumer.ServiceDiscovery;
import com.lsd.cong.comsumer.cluster.CompatibleRouter;
import com.lsd.cong.comsumer.cluster.RandomLoadBalance;
import com.lsd.cong.comsumer.cluster.ServiceDiscoveryRegistryDirectoryZk;
import com.lsd.cong.core.config.RegistryConfig;
import com.lsd.cong.core.config.ZookeeperSerializer;
import com.lsd.cong.core.consumer.LoadBalance;
import com.lsd.cong.core.consumer.Router;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;

/**
 * @Author: nhsoft.lsd
 */
@ComponentScan(basePackages =  "com.lsd.cong")
@EnableConfigurationProperties(RegistryConfig.class)
public class CongRpcConfiguration implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Bean
    @ConditionalOnMissingBean(LoadBalance.class)
    LoadBalance createDefaultLoadBalance() {

        LoadBalance loadBalance = new RandomLoadBalance();

        return loadBalance;

    }

    @Bean
    @ConditionalOnMissingBean(Router.class)
    Router createDefaultRouter() {
        Router router = new CompatibleRouter();
        return router;

    }


    @Bean
    @ConditionalOnMissingBean(ServiceDiscovery.class)
    ServiceDiscovery createDefaultServiceDiscovery() {

        RegistryConfig registryConfig = applicationContext.getBean(RegistryConfig.class);
        ZkClient zkClient = new ZkClient(registryConfig.getRegistryCenterUrl());
        zkClient.setZkSerializer(new ZookeeperSerializer());

        ServiceDiscovery serviceDiscovery = new ServiceDiscoveryRegistryDirectoryZk(zkClient);
        return serviceDiscovery;

    }

    @Bean
    ReferenceAutowire createReferenceAutowire(){
        ReferenceAutowire referenceAutowire = new ReferenceAutowire();
        return referenceAutowire;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
