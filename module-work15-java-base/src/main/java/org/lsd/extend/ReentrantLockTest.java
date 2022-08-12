package org.lsd.extend;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author nhsoft.lsd
 */
public class ReentrantLockTest {

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition empty = lock.newCondition();

    private static int stock = 0;

    public static void main(String[] args) {
        Customer c = new Customer();
        Producer p = new Producer();
        c.start();
        p.start();
    }

    static class Customer extends Thread {

        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(5000);
                    lock.lock();
                    if (stock <= 0) {
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                    for (int i = 0; i < 20; i++) {
                        stock--;
                        System.out.println("消费者消耗了：" + stock);
                    }
                    System.out.println(1111);
                    empty.signalAll();
                } catch (Exception e) {

                }
                finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Producer extends Thread {

        @Override
        public void run() {

            while (true) {

                try {
                    Thread.sleep(3000);
                    lock.lock();
                    if (stock >= 20) {
                        try {
                            empty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    for (int i = 0; i < 20; i++) {
                        stock++;
                        System.out.println("生产这生产了：" + stock);
                    }
                    System.out.println(22222);
                    notEmpty.signalAll();
                } catch (Exception e) {

                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
