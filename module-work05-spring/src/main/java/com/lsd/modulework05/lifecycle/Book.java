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
public class Book implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean,  DisposableBean {

    private String bookName;
    public Book(){
        System.out.println("Book Initializing ");
    }

    public void setBeanName(String name) {
        System.out.println("Book.setBeanName invoke");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Book.setBeanFactory invoke");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Book.setApplicationContext invoke");
    }


    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("Book: @PostConstruct");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("Book.afterPropertiesSet invoke");
    }

    public void destroy() throws Exception {
        System.out.println("Book.destory invoke");
    }


    public String getBookName() {
        System.out.println("getBookName: Book name has get.");
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
        System.out.println("setBookName: Book name has set.");
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
