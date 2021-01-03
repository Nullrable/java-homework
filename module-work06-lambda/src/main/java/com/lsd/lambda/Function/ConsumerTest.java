package com.lsd.lambda.Function;

import java.util.function.Consumer;

/**
 * @Author: nhsoft.lsd
 */
public class ConsumerTest {

    public static void main(String args[]) {

        test("123", (String s) -> {
            System.out.println(s + ", 456");
        });


    }

    private static void test(String s, Consumer<String> c) {
        c.accept(s);

        //这里输出的还是s，
        System.out.println(s);
    }
}
