package com.lsd.lambda.Stream;

import com.lsd.lambda.common.Dish;
import com.lsd.lambda.common.Utils;
import java.nio.channels.Selector;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author: nhsoft.lsd
 */
public class GroupByTest {

    public enum CaloricLevel { DIET, NORMAL, FAT }

    public static void main(String args[]) {

//        testOneLevel();
//
//        testMultiLevel();
//
//        testSubLevelCount();

        testMergeCollector();
    }

    private static void testMergeCollector(){

       Map<Dish.Type, List<String>> resultList = Utils.getDishs().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));

        resultList.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }

    private static void testSubLevelMaxby() {
        Map<Dish.Type, Dish> mostCaloricByType =
                Utils.getDishs().stream()
                        .collect(Collectors.groupingBy(Dish::getType,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                                        Optional::get)));
    }

    /**
     * 分组收集
     */
    private static void testSubLevelCount() {

       Map<Dish.Type, Long> map = Utils.getDishs().stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        System.out.println(map);
    }

    /**
     *  多级分组
     */
    private static void testMultiLevel() {


        Map<Dish.Type, Map<Object, List<Dish>>> map = Utils.getDishs().stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.groupingBy(
                                dish -> {
                                    if (dish.getCalories() <= 400)
                                        return CaloricLevel.DIET;
                                    else if (dish.getCalories() <= 700)
                                        return CaloricLevel.NORMAL;
                                    else
                                        return CaloricLevel.FAT;
                                }

                        )));

        System.out.println(map);

    }

    /**
     * 一级分组
     */
    private static void testOneLevel() {


        Map<CaloricLevel, List<Dish>> map = Utils.getDishs().stream().collect(Collectors.groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700)
                        return CaloricLevel.NORMAL;
                    else
                        return CaloricLevel.FAT;
                }
        ));

        System.out.println(map);

    }
}
