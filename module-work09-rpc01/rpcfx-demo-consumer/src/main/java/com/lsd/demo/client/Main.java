package com.lsd.demo.client;


import com.alibaba.fastjson.JSON;
import com.lsd.demo.service.User;
import com.lsd.demo.service.UserService;
import com.lsd.rpcfx.core.api.LoadBalancer;
import com.lsd.rpcfx.core.api.Router;
import com.lsd.rpcfx.core.api.SimpleLoadBalancer;
import com.lsd.rpcfx.core.api.SimpleRouter;
import com.lsd.rpcfx.core.common.config.ServiceNodeConfig;
import com.lsd.rpcfx.core.common.serializer.MyZkSerializer;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:37 PM
 * @Modified By：
 */
@SpringBootApplication
@ComponentScan(value = {"com.lsd.rpcfx", "com.lsd.demo"})
public class Main {


    @Autowired
    private ServiceNodeConfig serviceNodeConfig;

    public static void main (String args[])throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);

        System.setProperty("fastjson.parser.autoTypeSupport", "true");

        UserService userService = (UserService)context.getBean("ProxyFactoryBean");

        User user = userService.findByName("dongdong");

        System.out.println(JSON.toJSONString(user));

    }

    @Bean("ProxyFactoryBean")
    public ProxyFactoryBean createProxyFactoryBean(){
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTargetClass(UserService.class);
        proxyFactoryBean.setInterceptorNames("RpcfxAop");
        return proxyFactoryBean;
    }

    @Bean
    public ZkClient zkClient(){

        ZkClient zkClient = new ZkClient(serviceNodeConfig.getServerUrl());
        zkClient.setZkSerializer(new MyZkSerializer());
        return zkClient;

    }

    @Bean
    public Router router(){
        Router router = new SimpleRouter();
        return router;
    }

    @Bean
    public LoadBalancer loadBalancer(){
        LoadBalancer loadBalancer = new SimpleLoadBalancer();
        return loadBalancer;
    }

}
