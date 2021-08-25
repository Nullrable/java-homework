package com.lsd.lambda.common;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public class Utils {

    public static List<Dish> getDishs() {
        return  Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
    }

    public static List<Artist> getArtists() {
        return  Arrays.asList(
                new Artist("梵高", "法国"),
                new Artist("肖邦", "波兰"),
                new Artist("莫扎特", "奥地利"),
                new Artist("鲁班", "中国"),
                new Artist("达芬奇", "意大利"),
                new Artist("毕加索", "西班牙"));
    }
}
