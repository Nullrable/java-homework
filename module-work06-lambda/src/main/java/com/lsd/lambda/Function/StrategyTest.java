package com.lsd.lambda.Function;

/**
 * @Author: nhsoft.lsd
 */
public class StrategyTest {

    public static void main (String[] args) {

        Validator numericValidator =
                new Validator((String s) -> s.matches("[a-z]+"));
        System.out.println(numericValidator.validate("aaaa"));

    }
}
