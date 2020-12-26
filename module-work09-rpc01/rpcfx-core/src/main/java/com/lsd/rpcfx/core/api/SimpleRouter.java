package com.lsd.rpcfx.core.api;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class SimpleRouter implements Router {

    @Override
    public List<ServiceInfo> route(List<ServiceInfo> serviceInfos) {
        return serviceInfos;
    }
}
