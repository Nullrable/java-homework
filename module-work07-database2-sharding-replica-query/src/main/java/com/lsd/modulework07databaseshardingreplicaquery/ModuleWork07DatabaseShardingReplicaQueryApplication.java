package com.lsd.modulework07databaseshardingreplicaquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ModuleWork07DatabaseShardingReplicaQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleWork07DatabaseShardingReplicaQueryApplication.class, args);
    }

}
