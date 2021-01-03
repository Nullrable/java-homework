package com.lsd.lambda.Function;

import java.util.function.Consumer;

/**
 * @Author: nhsoft.lsd
 */
public class OnlineBankingTest {

    public static void main (String[] args) {

        new OnlineBankingLambda().processCustomer(1, getMakeCustomerHappyFunction());
    }

    private static Consumer<String> getMakeCustomerHappyFunction() {
        return (String msg) -> {
            System.out.println("Hello12312 " + msg);
        };

    }
}
