package com.lsd.modulework05springlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("MyLifeCyc");
        beanDefinition.setFactoryBeanName("MyLifeCycFactory");
        beanDefinition.setFactoryMethodName("getMyLifeCyc");
        System.out.println("执行BeanFactoryPostProcessor.configurableListableBeanFactory");
    }
}
