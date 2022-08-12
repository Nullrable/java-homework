package com.lsd.lock;

/**
 * @author nhsoft.lsd
 */
public class ThreadTest {

    private static Object o = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    for (int i = 0; i < 50; i++)  {
                        System.out.println(Thread.currentThread().getName() + "=========> " + i);
                    }
                    try {
                        o.wait();
                    } catch (InterruptedException e) {

                    }
                    for (int i = 50; i < 100; i++)  {
                        System.out.println(Thread.currentThread().getName() + "=========> " + i);
                    }
                }
            }
        });
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        test();
    }

    public static void test() {

        synchronized (o) {
            for (int i = 100; i < 200; i++)  {
                System.out.println(Thread.currentThread().getName() + "=========> " + i);
            }
            o.notify();
        }

    }
}
