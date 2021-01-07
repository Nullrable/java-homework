package com.lsd.cong.core.config;

import java.lang.annotation.*;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: nhsoft.lsd
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Inherited
public @interface CongReference {

    String[] group() default {};
}
