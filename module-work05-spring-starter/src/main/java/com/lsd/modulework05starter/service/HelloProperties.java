package com.lsd.modulework05starter.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 22:25
 * @Modified By：
 */
@ConfigurationProperties(prefix = "lsd.http")
public class HelloProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
