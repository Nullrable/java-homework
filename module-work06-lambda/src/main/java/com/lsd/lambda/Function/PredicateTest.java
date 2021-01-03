package com.lsd.lambda.Function;

import java.util.function.Predicate;

/**
 * @Author: nhsoft.lsd
 */
public class PredicateTest {

    public static void main(String args[]) {

        test("123", (String str) -> str.equals("123"));

    }

    private static void test(String s, Predicate<String> p) {
        if (p.test(s)) {
            System.out.println("hello world~");
        }else {
            System.out.println("no world~");
        }
    }
}
