package com.lsd.modulework05starter.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 22:23
 * @Modified By：
 */


public class HelloService {

    @Autowired
    private HelloProperties helloProperties;

    public String sayHello() {
        return helloProperties.getName();
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }
}
