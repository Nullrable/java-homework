package com.lsd.modulework08database3shardingxa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class ModuleWork08Database3ShardingXaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleWork08Database3ShardingXaApplication.class, args);
	}

}
