package com.lsd.cong.server.starter;


import com.lsd.cong.core.config.RegistryConfig;
import com.lsd.cong.core.config.ZookeeperSerializer;
import com.lsd.cong.core.registry.ServiceRegister;
import com.lsd.cong.provider.ProviderAutowire;
import com.lsd.cong.provider.registry.ServiceRegisterZk;
import javax.annotation.Resource;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nhsoft.lsd
 */
@Configuration
@ComponentScan(basePackages =  "com.lsd.cong")
@EnableConfigurationProperties(RegistryConfig.class)
public class CongRpcConfiguration {

    @Resource
    private RegistryConfig registryConfig;

    @Bean
    ProviderAutowire createProviderAutowire() {
        ProviderAutowire providerAutowire = new ProviderAutowire();
        return providerAutowire;
    }

    @Bean
    @ConditionalOnMissingBean(ServiceRegister.class)
    ServiceRegister createDefaultServiceRegister() {

        ZkClient zkClient = new ZkClient(registryConfig.getRegistryCenterUrl());
        zkClient.setZkSerializer(new ZookeeperSerializer());

        ServiceRegister serviceDiscovery = new ServiceRegisterZk(zkClient);
        return serviceDiscovery;

    }
}
