package com.lsd.modulework08database3shardingxa.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-08 21:16
 * @Modified By：
 */
@Configuration
@EnableTransactionManagement
public class DatasourceConfig {

    public DataSource createDataSource(){

        DataSource dataSource = null;
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(getFile("/application-sharding-jdbc.yaml"));
        }catch (SQLException | IOException e){
            System.out.println("application-sharding-jdbc.yaml loading error");
        }
        return dataSource;
    }


    private static File getFile(final String configFile) {
        return new File(DatasourceConfig.class.getResource(configFile).getFile());
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(createDataSource());
    }

    @Bean
    public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
