package com.lsd.net.bytebuddy.accessor;

/**
 * @Author: nhsoft.lsd
 */
public interface InterceptionAccessor {

    Interceptor getInterceptor();

    void setInterceptor(Interceptor interceptor);
}
