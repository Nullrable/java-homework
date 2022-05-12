package com.lsd.lock.Volatile;

/**
 * @author nhsoft.lsd
 */
public class VolatileDemo {

    private static volatile boolean isOver = false;

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver){
//                    如果加了这个同步块，就可以跳出循环，可能存在IO操作，工作内存会存在读取到主内存最新的数据，具体原因不是很清楚
//                    synchronized (this){
//
//                    }
                }


            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isOver = true;

    }
}
