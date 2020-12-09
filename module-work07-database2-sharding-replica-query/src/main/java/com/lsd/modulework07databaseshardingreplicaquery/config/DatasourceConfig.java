package com.lsd.modulework07databaseshardingreplicaquery.config;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.shardingsphere.driver.api.yaml.YamlShardingSphereDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-08 21:16
 * @Modified By：
 */
@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    public DataSource createDataSource(){

        // 创建 ShardingSphereDataSource
        DataSource dataSource = null;
        try {
            dataSource = YamlShardingSphereDataSourceFactory.createDataSource(getFile("/sharding-replica-query.yml"));
        }catch (SQLException | IOException e){
            e.printStackTrace();
            System.out.println("application-sharding-jdbc.yaml loading error");
        }
        return dataSource;
    }

    private static File getFile(final String configFile) {
        return new File(DatasourceConfig.class.getResource(configFile).getFile());
    }
}
