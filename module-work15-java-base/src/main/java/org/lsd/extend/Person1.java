package org.lsd.extend;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author nhsoft.lsd
 */
public class Person1 {

    private String name;

    private int age;

    final void fly() {
        System.out.println("i am flying");
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public static void main(String args[]) {

       Queue printObjectQueue = new LinkedList();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Object[] newPrintObjectArray = printObjectQueue.toArray();
                    if (newPrintObjectArray != null && newPrintObjectArray.length > 0) {

                        for (Object obj : newPrintObjectArray) {
                            String printContent = (String)obj;
                            try {
                                System.out.println(printContent);
                            } catch (Exception e) {
                                continue;
                            }
                            printObjectQueue.remove();
                        }
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                    }
                }
            }
        });
        t.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                BigInteger i = new BigInteger("0");
                while (true) {
                    i = i.add(new BigInteger("1"));
                    printObjectQueue.add( i + "");
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                    }
                }
            }
        });
        t2.start();


    }
}
