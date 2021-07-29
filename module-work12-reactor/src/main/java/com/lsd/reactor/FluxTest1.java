package com.lsd.reactor;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author nhsoft.lsd
 */
public class FluxTest1 {

    public static void main(String args[]) {
//        System.out.println("Thead ===>" + Thread.currentThread().getName());
//        Flux<Integer> ints = Flux.range(1, 3);
//        ints.subscribe(i -> {
//            System.out.println("Thead ===>" + Thread.currentThread().getName());
//            System.out.println(i);
//        });

//        Flux<Integer> ints = Flux.range(1, 4)
//                .map(i -> {
//                    if (i <= 3) return i;
//                    throw new RuntimeException("Got to 4");
//                });
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error: " + error),
//                () -> {System.out.println("Done");});


//        Flux<Integer> ints = Flux.range(1, 4);
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error " + error),
//                () -> {System.out.println("Done");});


//        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
//        Flux<Integer> ints = Flux.range(1, 4);
//        ints.subscribe(i -> System.out.println(i),
//                error -> System.err.println("Error " + error),
//                () -> {System.out.println("Done");},
//                s -> ss.request(10));
//        ints.subscribe(ss);

//        Flux<String> flux = Flux.generate(
//                () -> 0,
//                (state, sink) -> {
//                    sink.next("3 x " + state + " = " + 3*state);
//                    if (state == 10) sink.complete();
//                    return state + 1;
//                });
//        flux.subscribe(info -> System.out.println(info));


        Flux<String> flux = Flux.generate(
                AtomicLong::new,
                (state, sink) -> {
                    long i = state.getAndIncrement();
                    sink.next("3 x " + i + " = " + 3*i);
                    if (i == 10) sink.complete();
                    return state;
                }, (state) -> System.out.println("state: " + state));

        flux.subscribe(info -> System.out.println(info));
    }
}
