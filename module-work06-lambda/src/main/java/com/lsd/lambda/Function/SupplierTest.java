package com.lsd.lambda.Function;

import java.util.function.Supplier;

/**
 * @Author: nhsoft.lsd
 */
public class SupplierTest {

    public static void main(String args[]) {

        test("test", () -> "hello word");
    }


    private static void test(String level, Supplier<String> supplier) {

        if (level.equals("test")) {
            String res = supplier.get();
            System.out.println(res);
        }
    }
}
