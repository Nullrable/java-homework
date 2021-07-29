package com.lsd.reactor;

import reactor.core.publisher.Flux;

import java.util.List;


/**
 * @author nhsoft.lsd
 */
public class FluxTest2 {

    public static void main(String args[]) {

        Flux<String> bridge = Flux.create(sink -> {

            myMessageProcessor.register(
                    new MyMessageListener<String>() {

                        public void onMessage(List<String> messages) {
                            for(String s : messages) {
                                sink.next(s);
                            }
                        }
                    });
            sink.onRequest(n -> {
                List<String> messages = myMessageProcessor.request(n);
                for(String s : message) {
                    sink.next(s);
                }
            });
        });
    }
}
