package com.lsd.modulework05.work03.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-18 17:56
 * @Modified By：
 */
@Configuration
public class DatasourceConfig {

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Bean
    public HikariDataSource createHikariDataSource(){

        HikariConfig config = new HikariConfig();
        config.setPassword(password);
        config.setUsername(userName);
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);

        return new HikariDataSource(config);
    }
}
