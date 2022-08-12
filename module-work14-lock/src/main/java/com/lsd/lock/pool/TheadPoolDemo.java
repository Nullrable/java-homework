package com.lsd.lock.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author nhsoft.lsd
 */
public class TheadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());
        Future<Integer> future = executorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 12312312;
            }
        });

        try {
            System.out.println(future.get());
        } catch (Exception e) {

        }
    }
}
