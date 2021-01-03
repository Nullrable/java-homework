package com.lsd.lambda.Stream;

import com.lsd.lambda.common.Dish;
import com.lsd.lambda.common.Utils;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

/**
 * @Author: nhsoft.lsd
 */
public class SummingTest {

    public static void main(String args[]) {

        System.out.println(Utils.getDishs().stream().collect(Collectors.summingInt(Dish::getCalories)));

        IntSummaryStatistics menuStatistics =
                Utils.getDishs().stream().collect(Collectors.summarizingInt(Dish::getCalories));

        System.out.println(menuStatistics);

    }
}
