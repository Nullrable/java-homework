package com.lsd.modulework05.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-18 22:41
 * @Modified By：
 */
@Component
public class Libiray implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean,  DisposableBean {

    private String libirayName;
    public Libiray(){
        System.out.println("Libiray Initializing ");
    }

    public void setBeanName(String name) {
        System.out.println("Libiray.setBeanName invoke");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Libiray.setBeanFactory invoke");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Libiray.setApplicationContext invoke");
    }


    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("Libiray: @PostConstruct");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Libiray.afterPropertiesSet invoke");
    }

    public void destroy() throws Exception {
        System.out.println("Libiray.destory invoke");
    }


    public String getLibirayName() {
        System.out.println("getLibirayName: Libiray name has get.");
        return libirayName;
    }

    public void setLibirayName(String libirayName) {
        this.libirayName = libirayName;
        System.out.println("setLibirayName: Libiray name has set.");
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPreDestory(){
        System.out.println("@PreDestory");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("------inside finalize-----");
    }
}
