package com.lsd.modulework05springlifecycle;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 */
@Component
public class MyApplicationListener implements ApplicationListener {

    public MyApplicationListener() {
        System.out.println("ApplicationListener实例化");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("执行ApplicationListener.onApplicationEvent：" + event.getClass().getSimpleName()) ;
    }
}
