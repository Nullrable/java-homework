package com.lsd.demo.client;

import com.lsd.cong.core.config.CongReferenceScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: nhsoft.lsd
 */
@SpringBootApplication
@CongReferenceScan(basePackages = {"com.lsd.demo.client"})
public class ClientApplication {

    public static void main (String args[]){
        SpringApplication.run(ClientApplication.class, args);
    }



}
