package com.lsd.cong.comsumer.cluster;

import com.lsd.cong.core.consumer.Invocation;
import com.lsd.cong.core.consumer.Invoker;
import com.lsd.cong.core.consumer.Router;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: nhsoft.lsd
 */
public class CompatibleRouter implements Router {

    @Override
    public List<Invoker> route(List<Invoker> invokers, Invocation invocation) {

        if (invokers == null || invokers.isEmpty()) {
            return invokers;
        }

        //TODO 简单实现分组功能
        if (invocation.getGroup() != null){
            invokers = invokers.stream().filter( invoker ->
                    invoker.getUrl().getGroups() != null
                            && invoker.getUrl().getGroups().contains(invocation.getGroup()))
                    .collect(Collectors.toList());
        }


        return invokers;

    }
}
