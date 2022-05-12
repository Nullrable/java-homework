package com.lsd.lock.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nhsoft.lsd
 */
public class ReentrantLockDemo {

    private ReentrantLock lock;
    private Condition notEmpty;
    private Condition notFull;
    private int stock = 0;

    private static int MAX_STOCK = 20;

    public static void main(String[] args) {

        new ReentrantLockDemo();
    }

    public ReentrantLockDemo() {
        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();

        Thread provider = new Thread(new Provider());
        provider.setName("Provider" + 0);
        provider.start();

        Thread provider1 = new Thread(new Provider());
        provider1.setName("Provider" + 1);
        provider1.start();

        Thread consumer = new Thread(new Consumer());
        consumer.setName("Consumer" + 0);
        consumer.start();

    }

    class Provider implements Runnable {

        @Override
        public void run() {

            for (; ; ) {
                try {
                    lock.lock();
                    while (stock == MAX_STOCK) {
                        System.out.println(Thread.currentThread().getName() + "-当前库存已满，生产者进入等待");
                        notFull.await();
                        System.out.println(Thread.currentThread().getName() + "-当前库存：" + stock + "，生产者重新获取锁并被唤醒");
                    }

                    stock++;

                    notEmpty.signalAll();

                    System.out.println(Thread.currentThread().getName() + "-当前库存：" + stock);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {

            for (; ; ) {

                try {
                    lock.lock();
                    while (stock == 0) {
                        System.out.println(Thread.currentThread().getName() + "-当前库存为0，消费者进入等待");
                        notEmpty.wait();
                        System.out.println(Thread.currentThread().getName() + "-当前库存：" + stock + "，消费者重新获取锁并唤醒");
                    }

                    stock--;

                    notFull.signalAll();

                    System.out.println(Thread.currentThread().getName() + "-当前库存：" + stock);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }

        }
    }
}

