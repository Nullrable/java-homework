package com.lsd.cong.comsumer.cluster;

import com.lsd.cong.core.consumer.Invocation;
import com.lsd.cong.core.consumer.Invoker;
import com.lsd.cong.core.consumer.LoadBalance;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: nhsoft.lsd
 */
public class RandomLoadBalance implements LoadBalance {

    @Override
    public Invoker select(List<Invoker> invokers, Invocation invocation) {

        //简单实现随机负载均衡
        //TODO 实现根据Invocation 根据权重实现负载均衡

        int length = invokers.size();

        int index = ThreadLocalRandom.current().nextInt(length);

        return invokers.get(index);
    }
}
