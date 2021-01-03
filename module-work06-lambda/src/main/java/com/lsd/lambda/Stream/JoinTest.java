package com.lsd.lambda.Stream;

import com.lsd.lambda.common.Dish;
import com.lsd.lambda.common.Utils;
import java.util.stream.Collectors;

/**
 * @Author: nhsoft.lsd
 */
public class JoinTest {

    public static void main(String[] args) {

       String result1 = Utils.getDishs().stream().map(Dish::getName).collect(Collectors.joining(","));

        System.out.println(result1);
    }
}
