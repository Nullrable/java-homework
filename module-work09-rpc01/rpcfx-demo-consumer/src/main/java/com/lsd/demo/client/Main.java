package com.lsd.demo.client;


import com.alibaba.fastjson.JSON;
import com.lsd.demo.service.User;
import com.lsd.demo.service.UserService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:37 PM
 * @Modified By：
 */
@SpringBootApplication
public class Main {



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



}
