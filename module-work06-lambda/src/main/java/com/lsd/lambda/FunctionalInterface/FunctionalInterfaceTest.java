package com.lsd.lambda.FunctionalInterface;

/**
 * @Author: nhsoft.lsd
 */
public class FunctionalInterfaceTest {

    public static void main(String args[]) {

        GreetingService greetingService = msg -> System.out.println(msg);

        greetingService.greeting("123");

        greetingService = msg -> System.out.println("new: " + msg);

        greetingService.greeting("123");
    }
}
