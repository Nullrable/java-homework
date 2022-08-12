package org.lsd.extend;

/**
 * @author nhsoft.lsd
 */
public class Test {

    private static Object o = new Object();

    public static void main(String[] args) {

        int x = 0;

        test(x);

        System.out.println(x);
    }

    public static void test(int x) {

        x = x + 1;

    }
}
