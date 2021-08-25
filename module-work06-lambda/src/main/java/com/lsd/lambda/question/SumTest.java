package com.lsd.lambda.question;

import java.util.stream.Stream;

/**
 * @author nhsoft.lsd
 */
public class SumTest {

    public static void main(String[] args) {


        Integer result = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

        System.out.println(result);

    }
}
