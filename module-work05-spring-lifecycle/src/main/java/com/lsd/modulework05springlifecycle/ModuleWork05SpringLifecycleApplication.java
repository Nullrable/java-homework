package com.lsd.modulework05springlifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ModuleWork05SpringLifecycleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleWork05SpringLifecycleApplication.class, args);
    }

    @Bean(initMethod = "myInit", destroyMethod = "myDestroy", name = "MyLifeCyc1")
    MyLifeCyc createMyLifeCyc(){
        MyLifeCyc myLifeCyc = new MyLifeCyc();
        myLifeCyc.setMonitor("123");
        return myLifeCyc;
    }
}
