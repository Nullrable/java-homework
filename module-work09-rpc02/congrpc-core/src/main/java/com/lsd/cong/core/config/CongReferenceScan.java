package com.lsd.cong.core.config;

import java.lang.annotation.*;

/**
 * @Author: nhsoft.lsd
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface CongReferenceScan {

    String[] basePackages() default {};
}
