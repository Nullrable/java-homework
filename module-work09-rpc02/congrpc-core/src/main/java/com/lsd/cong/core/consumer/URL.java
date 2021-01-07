package com.lsd.cong.core.consumer;

import java.util.List;
import java.util.Map;

/**
 * @Author: nhsoft.lsd
 */
public class URL {

    private final String protocol;

    private final String host;

    private final int port;

    private final String path;

    private final String serviceName;

    private final List<String> groups;

    private final Map<String, String> parameters;

    public URL(String protocol, String host, int port, String path, String serviceName, List<String> groups, Map<String, String> parameters) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.path = path;
        this.serviceName = serviceName;
        this.groups = groups;
        this.parameters = parameters;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public String getServiceName() {
        return serviceName;
    }

    public List<String> getGroups() {
        return groups;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public String getURL() {
        return protocol + "://" + host + ":" + port;
    }
}
