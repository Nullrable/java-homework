package com.lsd.demo;

import com.lsd.rpcfx.core.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:29 PM
 * @Modified By：
 */
public class DefaultResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        return applicationContext.getBean(serviceClass);
    }
}
