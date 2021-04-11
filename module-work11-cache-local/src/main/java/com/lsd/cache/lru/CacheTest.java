package com.lsd.cache.lru;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: nhsoft.lsd
 */
public class CacheTest {

    final static Cache<Integer, String> cache;

    static {
        cache = CacheBuilder.newBuilder()
                //设置cache的初始大小为10，要合理设置该值
                .initialCapacity(10)
                //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
                .concurrencyLevel(5)
                //设置cache中的数据在写入之后的存活时间为10秒
                .expireAfterWrite(10, TimeUnit.SECONDS)
                //构建cache实例
                .build();
    }

    public static void main (String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Thread1(i, cache));
        }



//        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
//        executorService2.execute(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//
//                    System.out.println(Thread.currentThread().getName() + ": get " + cache.getIfPresent(1));
//
//                    try {
//                        Thread.sleep(200);
//                    }catch (Exception e) {
//
//                    }
//
//                }
//
//            }
//        });
    }


}


class Thread1 implements Runnable {

    private int i ;
    private Cache cache;

    public Thread1(int i, Cache cache) {
        this.i = i;
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {

            cache.put(i, UUID.randomUUID().toString());
            System.out.println(Calendar.getInstance().getTime() + "===>" + Thread.currentThread().getName() + ": put  "+ i +"" + cache.getIfPresent(i));

            try {
                Thread.sleep(1000);
            }catch (Exception e) {

            }
        }
    }
}