package com.lsd.cache.lru;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.UUID;
import java.util.concurrent.*;

/**
 *
 * 异步加载策略1：如果数据为空，从数据库加载后，返回；增加刷新线程每隔一段时间刷新缓存
 *
 * @author: nhsoft.lsd
 */
public class CacheAsyncStrategy1 {

    private static ListeningExecutorService backgroundRefreshPools;
    private static LoadingCache<String, Object> cache;

    public static void main (String[] args) throws ExecutionException {

        init(100, 10);

        while (true) {

            String s = (String)cache.get("123");

            System.out.println(s);

            try {
                Thread.sleep(1000);
            }catch (Exception e) {

            }


        }
    }

    private static void init (long maxSize, long refreshTime) {
        // 刷新线程池 -> 如果数据都没了则启用后台线程进行刷新,让用户无感知 -> 核心线程数 1, 最大线程数 2
        backgroundRefreshPools = MoreExecutors.listeningDecorator(new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()));

        // 缓存
        cache = CacheBuilder.newBuilder()
                // 缓存刷新时间
                .refreshAfterWrite(refreshTime, TimeUnit.SECONDS)
                // 设置缓存在写入invalidTime分钟后失效
                //.expireAfterWrite(refreshTime, TimeUnit.MINUTES)
                // 设置缓存个数
                .maximumSize(maxSize)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .recordStats()
                .build(new CacheLoader<String, Object>() {
                    // 当本地缓存命没有中时，调用load方法获取结果并将结果缓存
                    @Override
                    public Object load(String appKey) throws ExecutionException {
                        return getFromDB(appKey);
                    }

                    @Override
                    public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
                        return backgroundRefreshPools.submit(() -> getFromDB(key));
                    }

                    // 数据库进行查询
                    private String getFromDB (String key) {
                        return UUID.randomUUID().toString();
                    }
                });
    }
}
