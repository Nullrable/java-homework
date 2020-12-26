package com.lsd.rpcfx.core.server.register;

import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020/12/23 7:05 下午
 * @Modified By：
 */
public interface ServiceRegister {

    void register(ServiceInfo serviceInfo);

    void register(List<ServiceInfo> serviceInfos);
}
