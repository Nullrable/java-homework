package com.lsd.lambda.Function;

import java.util.function.Function;

/**
 * @Author: nhsoft.lsd
 */
public class FunctionTest {

    public static void main(String args[]) {

        test("123", (String t) ->  t + ", 456");
    }

    private static void test(String s, Function<String, String> function) {

        String i = function.apply(s);

        System.out.println(i);

    }
}
