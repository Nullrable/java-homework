package com.lsd.cong.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

/**
 * @Author: nhsoft.lsd
 */
@ConfigurationProperties(prefix = "cong.rpc")
@Primary
@Order(1)
public class RegistryConfig {

    public RegistryConfig() {

    }

    private String registryCenterUrl;

    public String getRegistryCenterUrl() {
        return registryCenterUrl;
    }

    public void setRegistryCenterUrl(String registryCenterUrl) {
        this.registryCenterUrl = registryCenterUrl;
    }
}
