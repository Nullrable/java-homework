package com.lsd.lock;

/**
 * @author nhsoft.lsd
 */
public class ThreadJoinTest {

    private static Object o = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++)  {
                    System.out.println(Thread.currentThread().getName() + "=========> " + i);
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {

        }
        test();
    }

    public static void test() {

        for (int i = 100; i < 200; i++)  {
            System.out.println(Thread.currentThread().getName() + "=========> " + i);
        }
    }
}
