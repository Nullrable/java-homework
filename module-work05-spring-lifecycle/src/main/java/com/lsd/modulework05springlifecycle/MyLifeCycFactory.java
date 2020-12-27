package com.lsd.modulework05springlifecycle;

import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 */
@Component("MyLifeCycFactory")
public class MyLifeCycFactory {

    public MyLifeCyc getMyLifeCyc(){

        MyLifeCyc instance = new MyLifeCyc();
        instance.setMonitor("4444");
        return instance;
    }
}
