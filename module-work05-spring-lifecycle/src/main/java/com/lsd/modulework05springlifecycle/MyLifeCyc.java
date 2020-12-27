package com.lsd.modulework05springlifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: nhsoft.lsd
 */
public class MyLifeCyc implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {


    private String monitor;


    public MyLifeCyc() {

        System.out.println(this.getClass().getSimpleName() + "实例化");
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        System.out.println(this.getClass().getSimpleName() + "调用setMonitor方法" + monitor);
        this.monitor = monitor;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(this.getClass().getSimpleName() + "调用BeanNameAware接口的setBeanName方法" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

        MyLifeCyc myLifeCyc = (MyLifeCyc)beanFactory.getBean("MyLifeCyc1");

        System.out.println(this.getClass().getSimpleName() + "调用BeanFactoryAware接口的setBeanFactory方法" + myLifeCyc.getClass().getSimpleName());
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println(this.getClass().getSimpleName() + "调用自定义postConstruct方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(this.getClass().getSimpleName() + "调用ApplicationContextAware接口的setApplicationContext方法");
    }

    public void myInit() {
        System.out.println(this.getClass().getSimpleName() + "调用自定义myInit方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getSimpleName() + "调用InitializingBean接口的afterPropertiesSet方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(this.getClass().getSimpleName() + "调用DisposableBean接口的DisposableBean方法");
    }

    public void myDestroy() throws Exception {
        System.out.println(this.getClass().getSimpleName() + "调用自定义的myDestroy方法");
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        System.out.println(this.getClass().getSimpleName() + "调用自定义的preDestroy方法");
    }


}
