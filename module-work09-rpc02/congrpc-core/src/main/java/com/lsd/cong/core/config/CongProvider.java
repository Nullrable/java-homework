package com.lsd.cong.core.config;

import java.lang.annotation.*;

/**
 * @Author: nhsoft.lsd
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CongProvider {

    String[] group() default {};

    String parameters() default "";
}
