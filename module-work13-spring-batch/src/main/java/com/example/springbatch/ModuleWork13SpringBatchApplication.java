package com.example.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ModuleWork13SpringBatchApplication {

    public static void main(String[] args) {

        try {
            test();
        } catch (Exception e)  {
            System.out.println(e.getMessage());
        }


    }

    private static void test() {
        try {
            throw new RuntimeException("12321");
        } finally {
            System.out.println("oooo");
        }
    }
}
