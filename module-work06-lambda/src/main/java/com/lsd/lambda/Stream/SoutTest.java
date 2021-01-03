package com.lsd.lambda.Stream;

import com.lsd.lambda.common.Utils;

/**
 * @Author: nhsoft.lsd
 */
public class SoutTest {

    public static void main(String args[]) {


        Utils.getDishs().forEach(System.out::println);

    }

}
