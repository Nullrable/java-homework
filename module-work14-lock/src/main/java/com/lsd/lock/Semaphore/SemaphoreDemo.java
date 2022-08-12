package com.lsd.lock.Semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author nhsoft.lsd
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        AtomicInteger counter = new AtomicInteger();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            Thread t = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("count:" + counter.getAndIncrement());
                    Thread.sleep(5000);
                    semaphore.release();
                } catch (InterruptedException e) {

                }
            });
            t.start();
        }
    }
}
