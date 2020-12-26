package com.lsd.rpcfx.core.server;

import com.lsd.rpcfx.core.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:29 PM
 * @Modified By：
 */
public class DefaultRpcfxResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        return applicationContext.getBean(serviceClass);
    }
}
