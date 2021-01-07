package com.lsd.cong.core.consumer;

import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface LoadBalance {

    /**
     * select a invoker via load balance
     * @param invokers
     * @param invocation
     * @return
     */
    Invoker select(List<Invoker> invokers, Invocation invocation);
}
