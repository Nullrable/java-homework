package com.lsd.modulework05starter;

import com.lsd.modulework05starter.jdbc.JdbcProperties;
import com.lsd.modulework05starter.jdbc.LsdConnectionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-17 23:03
 * @Modified By：
 */
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcAutoConfiguration {

    @Autowired
    public JdbcProperties jdbcProperties;

    @Bean
    public LsdConnectionPool createLsdConnectionPool()throws Exception{
        LsdConnectionPool pool = new LsdConnectionPool(jdbcProperties);
        return pool;
    }
}
