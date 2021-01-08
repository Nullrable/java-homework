package com.lsd.cong.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: nhsoft.lsd
 */
@ConfigurationProperties(prefix = "cong.rpc")
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
