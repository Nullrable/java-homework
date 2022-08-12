package com.lsd.lock;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author nhsoft.lsd
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(()->{
                    int t = 1;
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(()->{
                    int t = 3;
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2,s -> s + 1);

        System.out.println(f3.join());

    }
}

