package com.lsd.rpcfx.core.api;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 9:36 PM
 * @Modified By：
 */
public interface LoadBalancer {

    ServiceInfo select(List<ServiceInfo> serviceInfos);
}
