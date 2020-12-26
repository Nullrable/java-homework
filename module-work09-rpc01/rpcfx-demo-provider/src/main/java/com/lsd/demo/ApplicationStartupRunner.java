package com.lsd.demo;

import com.lsd.rpcfx.core.server.ZookeeperServiceAutoRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 */
@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ZookeeperServiceAutoRegister serviceRegisterZk = new ZookeeperServiceAutoRegister(applicationContext);
        serviceRegisterZk.register();
    }
}
