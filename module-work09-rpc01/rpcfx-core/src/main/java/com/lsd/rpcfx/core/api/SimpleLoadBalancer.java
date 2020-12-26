package com.lsd.rpcfx.core.api;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class SimpleLoadBalancer implements LoadBalancer{

    @Override
    public ServiceInfo select(List<ServiceInfo> serviceInfos) {
        return !serviceInfos.isEmpty() ? serviceInfos.get(0) : null;
    }
}
