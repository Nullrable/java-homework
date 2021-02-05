package com.lsd.cache.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author: nhsoft.lsd
 */
class Util {

    static RedisClient redisClient = null;

    public static StatefulRedisConnection create() {
        RedisURI redisUri = RedisURI.builder()                    // <1> 创建单机连接的连接信息
                .withHost("localhost")
                .withPort(63790)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        redisClient = RedisClient.create(redisUri);   // <2> 创建客户端
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        return connection;
    }

    public static void shutdown() {
        redisClient.shutdown();
    }
}
