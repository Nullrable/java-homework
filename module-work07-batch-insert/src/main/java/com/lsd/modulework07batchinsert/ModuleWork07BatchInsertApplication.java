package com.lsd.modulework07batchinsert;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ModuleWork07BatchInsertApplication {


    public static void main(String[] args) {
        SpringApplication.run(ModuleWork07BatchInsertApplication.class, args);


    }

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DruidDataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }


}
