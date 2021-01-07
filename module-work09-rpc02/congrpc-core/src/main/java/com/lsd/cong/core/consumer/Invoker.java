package com.lsd.cong.core.consumer;


/**
 * @Author: nhsoft.lsd
 */
public interface Invoker<T> {


    URL getUrl();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws Throwable
     */
    Object invoke(Invocation invocation) throws Throwable;

}
