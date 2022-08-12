package com.lsd.lock.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author nhsoft.lsd
 */
public class Main {

    public static void main(String[] args) {

        Test test = new Test();

        test.permitDegrade();
        test.permitDegrade();

    }

    static class Test {

        final ReadWriteLock rwl =
                new ReentrantReadWriteLock();
        // 读锁
        final Lock r = rwl.readLock();
        // 写锁
        final Lock w = rwl.writeLock();

        public void notPermitUpgrade() {

            r.lock();

            w.lock();

            System.out.println("23423432");

            w.unlock();

            r.unlock();

        }

        public void permitDegrade() {

            w.lock();

            r.lock();

            System.out.println("23423432");


            w.unlock();



        }
    }
}
