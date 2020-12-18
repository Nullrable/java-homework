package com.lsd.rpcfx.core.api;

import java.util.List;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 9:36 PM
 * @Modified By：
 */
public interface LoadBalancer {

    String select(List<String> urls);
}
