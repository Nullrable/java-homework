package com.lsd.rpcfx.core.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nhsoft.lsd
 */
@Configuration
public class ServiceNodeConfig {

    @Value("${rpcfx.service.url:''}")
    private String serviceUrl;

    @Value("${rpcfx.zookeeper.url}")
    private String serverUrl;

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}
