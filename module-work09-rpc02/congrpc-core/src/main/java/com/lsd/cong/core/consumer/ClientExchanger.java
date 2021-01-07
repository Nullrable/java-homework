package com.lsd.cong.core.consumer;


/**
 * @Author: nhsoft.lsd
 */
public interface ClientExchanger extends com.lsd.cong.core.config.SPI {

    /**
     * 
     * @param invoker
     * @param invocation
     * @return
     * @throws Throwable
     */
    Object exchange(Invoker invoker, Invocation invocation) throws Throwable;
}
