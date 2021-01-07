package com.lsd.cong.core.consumer;

import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface Router {

    List<Invoker> route(List<Invoker> invokers, Invocation invocation);
}
