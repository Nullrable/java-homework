package com.lsd.modulework05starter;

import com.lsd.modulework05starter.service.HelloProperties;
import com.lsd.modulework05starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 22:35
 * @Modified By：
 */
@Configuration
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

    @Autowired
    public HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}
