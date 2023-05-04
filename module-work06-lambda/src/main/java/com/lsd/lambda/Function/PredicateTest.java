package com.lsd.lambda.Function;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * @Author: nhsoft.lsd
 */
public class PredicateTest {

    public static void main(String args[]) {


        int  i = 0;
        while (true) {

            if (i != 10) {

                i = i + 1;
                System.out.println(i);
                continue;
            }
            System.out.println(i);
            break;
        }

    }

    private static void test(String s, Predicate<String> p) {
        if (p.test(s)) {
            System.out.println("hello world~");
        }else {
            System.out.println("no world~");
        }
    }
}
