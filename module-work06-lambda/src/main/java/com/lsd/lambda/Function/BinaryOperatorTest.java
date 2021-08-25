package com.lsd.lambda.Function;

import java.util.function.BinaryOperator;

/**
 * @author nhsoft.lsd
 */
public class BinaryOperatorTest {

    public static void main(String[] args) {

        BinaryOperator<Integer> add = Integer::sum;

        System.out.println(add.apply(1, 2));

    }
}
