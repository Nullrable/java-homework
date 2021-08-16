package com.lsd.reactor;

import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;

/**
 * @author nhsoft.lsd
 */
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    public void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
