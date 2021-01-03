package com.lsd.lambda.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: nhsoft.lsd
 */
public class MapTest {

    public static void main(String args[]) {

        testFlatMap();

        testSquare();

        testDouble();

    }

    /**
     * flat map 扁平化映射
     */
    private static void testFlatMap() {
        String[] wordAttr = {"Hello", "World"};
        List<String> words = Arrays.asList(wordAttr);

        List<String> result = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        result.forEach(System.out::println);
    }

    /**
     *  给߿一个数ߙѴ表，如何返回一个由每个数的ࣰ方构成的Ѵ表呢？例如，给߿[1, 2, 3, 4,
     * 5]，应该返回[1, 4, 9, 16, 25]。
     */
    private static void testSquare() {

        Integer[] numArray = { 1, 2, 3, 4, 5 };
        List<Integer> nums = Arrays.asList(numArray);

        List<Integer> result = nums.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        result.forEach(System.out::println);

    }

    /**
     *  给߿两个数ߙѴ表，如何返回所有的数对呢？例如，给߿Ѵ表[1, 2, 3]和Ѵ表[3, 4]，应
     * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起᜸，你可以用有两个元素的数组来代
     * 表数对。
     */
    private static void testDouble() {

        List<Integer> nums1 = Arrays.asList(1, 2, 3);
        List<Integer> nums2 = Arrays.asList(3, 4);

        List<Integer[]> result = nums1.stream()
                .flatMap(num1 ->
                        nums2.stream().map(num2 -> new Integer[]{num1, num2}))
                .collect(Collectors.toList());

        result.forEach( array -> System.out.println(array[0] + "," + array[1]) );
    }
}
