package com.lsd.lock;

/**
 * @author nhsoft.lsd
 */
public class ThreadDeamonTest {

    private static Object o = new Object();

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println( Thread.currentThread().isAlive());
                }

            }
        });
        t.setDaemon(true);
        t.start();
    }
}
