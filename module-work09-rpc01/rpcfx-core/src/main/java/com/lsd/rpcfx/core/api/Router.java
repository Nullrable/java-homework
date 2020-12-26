package com.lsd.rpcfx.core.api;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface Router {

    List<ServiceInfo> route(List<ServiceInfo> serviceInfos);
}
