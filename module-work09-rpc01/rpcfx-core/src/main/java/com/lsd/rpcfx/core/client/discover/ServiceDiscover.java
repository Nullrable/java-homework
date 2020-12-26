package com.lsd.rpcfx.core.client.discover;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface ServiceDiscover {

    List<ServiceInfo> getServiceInfos(String serviceClass);
}
