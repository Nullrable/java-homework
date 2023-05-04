package com.lsd.lambda.Stream;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class ReduceTest {

    public static void main(String args[]) {


        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        /**
         * 求值
         */
        System.out.println(  nums.stream().reduce(0, Integer::sum));


        /**
         * 最大，最小值
         */
        System.out.println(  nums.stream().reduce(Integer::max).get() );

    }
}
