package com.lsd.reactor;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;


/**
 * @author nhsoft.lsd
 */
public class FluxTest2 {

    public static void main(String args[]) throws InterruptedException {

//        Flux<String> bridge = Flux.create(sink -> {
//
//            myMessageProcessor.register(
//                    new MyMessageListener<String>() {
//
//                        public void onMessage(List<String> messages) {
//                            for(String s : messages) {
//                                sink.next(s);
//                            }
//                        }
//                    });
//            sink.onRequest(n -> {
//                List<String> messages = myMessageProcessor.request(n);
//                for(String s : message) {
//                    sink.next(s);
//                }
//            });
//        });

//        Function<Flux<String>, Flux<String>> filterAndMap =
//                f -> f.filter(color -> !color.equals("orange"))
//                        .map(String::toUpperCase);
//
//        Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
//                .doOnNext(System.out::println)
//                .transform(filterAndMap)
//                .subscribe(d -> System.out.println("Subscriber to Transformed MapAndFilter: "+d));

//        AtomicInteger ai = new AtomicInteger();
//        Function<Flux<String>, Flux<String>> filterAndMap = f -> {
//            if (ai.incrementAndGet() == 1) {
//                return f.filter(color -> !color.equals("orange"))
//                        .map(String::toUpperCase);
//            }
//            return f.filter(color -> !color.equals("purple"))
//                    .map(String::toUpperCase);
//        };
//
//        Flux<String> composedFlux =
//                Flux.fromIterable(Arrays.asList("blue", "green", "orange", "purple"))
//                        .doOnNext(System.out::println)
//                        .compose(filterAndMap);
//
//        composedFlux.subscribe(d -> System.out.println("Subscriber 1 to Composed MapAndFilter :"+d));
//        composedFlux.subscribe(d -> System.out.println("Subscriber 2 to Composed MapAndFilter: "+d));

//        UnicastProcessor hotSource  = UnicastProcessor.create();
//
//        Flux<String> hotFlux = hotSource .publish()
//                .autoConnect()
//                .map(s -> String.valueOf(s).toUpperCase());
//
//        hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: "+d));
//
//        hotSource.onNext("blue");
//        hotSource.onNext("green");
//
//        hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: "+d));
//
//        hotSource.onNext("orange");
//        hotSource.onNext("purple");
//        hotSource.onComplete();

//        Flux<Integer> source = Flux.range(1, 3)
//                .doOnSubscribe(s -> System.out.println("subscribed to source"));
//
//        Flux<Integer> co = source.publish().autoConnect(2);
//
//        co.subscribe(System.out::println, e -> {}, () -> {});
//        System.out.println("done subscribing 1");
//
//        Thread.sleep(500);
//
//        co.subscribe(System.out::println, e -> {}, () -> {});
//        System.out.println("done subscribing 2");
        Flux flux = Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
                .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                .concatMap(g -> g.defaultIfEmpty(-1) //如果组为空，显示为 -1
                        .map(String::valueOf) //转换为字符串
                        .startWith(g.key())); //以该组的 key 开头

    }
}
