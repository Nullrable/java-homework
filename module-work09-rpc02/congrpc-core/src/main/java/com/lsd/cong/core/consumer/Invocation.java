package com.lsd.cong.core.consumer;

/**
 * @Author: nhsoft.lsd
 */
public class Invocation {

    /**
     * 获取服务名称
     * @return
     */
    private String serviceName;

    /**
     * 获取方法名称
     * @return
     */
    private String method;

    /**
     * 获取服务地址
     * @return
     */
    private String serviceUrl;

    /**
     * 获取MediaType
     * @return
     */
    private String mediaType;

    /**
     * 获取参数
     * @return
     */
    private Object[] arguments;

    /**
     * 获取分组
     * @return
     */
    private String group;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
