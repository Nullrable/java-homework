package com.lsd.cache.redis.lettuce;

import io.lettuce.core.LettuceFutures;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.SetArgs;
import io.lettuce.core.TransactionResult;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import reactor.core.publisher.Mono;

/**
 * @author: nhsoft.lsd
 */
public class Test1 {

    public static void main (String args[]) throws Exception {

//        testSimple();

//        testSyncPing();

//        testAsyncSetAndGet();

//        testReactivePing();
    }

    public void testAsyncManualFlush() {

        StatefulRedisConnection connection = Util.create();
        RedisAsyncCommands<String, String> redisCommands = connection.async();

        // 取消自动flush
        redisCommands.setAutoFlushCommands(false);
        List<RedisFuture<?>> redisFutures = new ArrayList<>();
        int count = 5000;
        for (int i = 0; i < count; i++) {
            String key = "key-" + (i + 1);
            String value = "value-" + (i + 1);
            redisFutures.add(redisCommands.set(key, value));
            redisFutures.add(redisCommands.expire(key, 2));
        }
        long start = System.currentTimeMillis();
        redisCommands.flushCommands();
        boolean result = LettuceFutures.awaitAll(10, TimeUnit.SECONDS, redisFutures.toArray(new RedisFuture[0]));
        System.out.println(result);
        System.out.println("Lettuce cost:" +  (System.currentTimeMillis() - start) +" ms");
    }

    //Redis Transaction
    public void testSyncMulti() throws Exception {

        StatefulRedisConnection connection = Util.create();
        RedisCommands<String, String> redisCommands = connection.sync();

        redisCommands.multi();
        redisCommands.setex("name-1", 2, "throwable");
        redisCommands.setex("name-2", 2, "doge");
        TransactionResult result = redisCommands.exec();
        int index = 0;
        for (Object r : result) {
            System.out.println("Result-" + index + ":" + r);
            index++;
        }
    }

    public static void testReactivePing() throws InterruptedException {

        StatefulRedisConnection connection = Util.create();
        RedisReactiveCommands<String, String> redisCommands = connection.reactive();

        Mono<String> ping = redisCommands.ping();
        ping.subscribe(v -> System.out.println("Ping result:" + v));
        Thread.sleep(1000);

        System.out.println("00000");

        connection.close();
        Util.shutdown();
    }

    private static void testAsyncSetAndGet() throws ExecutionException, InterruptedException {

        StatefulRedisConnection connection = Util.create();
        RedisAsyncCommands<String, String> redisCommands = connection.async();

        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        RedisFuture<String> future = redisCommands.set("name", "throwable", setArgs);
        // CompletableFuture#thenAccept()
        future.thenAccept(value -> System.out.println(String.format("Set命令返回:%s", value)));
        // Future#get()
        System.out.println("=====>" + future.get());

        RedisFuture redisFuture = redisCommands.get("name");
        System.out.println("getter======>" + redisFuture.get());

        Util.shutdown();
    }

    private static void testSyncSetAndGet() {

        StatefulRedisConnection connection = Util.create();

        // <3> 创建线程安全的连接
        RedisCommands<String, String> redisCommands = connection.sync();                // <4> 创建同步命令
        SetArgs setArgs = SetArgs.Builder.nx().ex(5000);
        String result = redisCommands.set("name", "throwable", setArgs);
        System.out.println((result).equalsIgnoreCase("OK"));
        result = redisCommands.get("name");
        System.out.println(result.equals("throwable"));
        // ... 其他操作
        connection.close();   // <5> 关闭连接
    }

    private static void testSyncPing() {

        StatefulRedisConnection connection = Util.create();
        RedisCommands<String, String> redisCommands = connection.sync();

        String pong = redisCommands.ping();

        System.out.println(pong);
    }


}
