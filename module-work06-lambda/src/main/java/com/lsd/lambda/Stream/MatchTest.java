package com.lsd.lambda.Stream;

import com.lsd.lambda.common.Dish;
import com.lsd.lambda.common.Utils;
import java.util.Optional;

/**
 * @Author: nhsoft.lsd
 */
public class MatchTest {

    /**
     * allMatch、anyMatch、noneMatch、findFirst和findAny方
     *
     * @param args
     */
    public static void main(String args[]) {
        Optional<Dish> dish =
                Utils.getDishs().stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
    }
}
