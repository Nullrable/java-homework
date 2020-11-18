package com.lsd.modulework05.work01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 21:59
 * @Modified By：
 */
@ImportResource("classpath:application-bean.xml")
@Configuration
public class BeanConfig {


    @Bean
    public School newSchool(){
        School school = new School();

        return school;
    }


}
