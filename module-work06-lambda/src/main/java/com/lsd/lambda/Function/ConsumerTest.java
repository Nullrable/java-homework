package com.lsd.lambda.Function;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Author: nhsoft.lsd
 */
public class ConsumerTest {

    public static void main(String args[]) {

        test("123", (String s) -> {
            System.out.println(s + ", 456");
        });

        test("dd", "bb", (String s1, String s2) -> {
            System.out.println(s1 + "," + s2 + ", 456");
        });


    }

    private static void test(String s1, String s2, BiConsumer<String, String> c) {
        c.accept(s1, s2);

    }

    private static void test(String s, Consumer<String> c) {
        c.accept(s);

        //这里输出的还是s，
        System.out.println(s);
    }
}
