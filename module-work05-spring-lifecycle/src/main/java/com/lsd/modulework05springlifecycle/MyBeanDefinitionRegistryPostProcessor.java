package com.lsd.modulework05springlifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @Author: nhsoft.lsd
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        System.out.println("MyBeanDefinitionRegistryPostProcessor...添加了MyLifeCyc之前的bean数="+ registry.getBeanDefinitionCount());
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(MyLifeCyc.class);
        registry.registerBeanDefinition("MyLifeCyc", rootBeanDefinition);

        System.out.println("执行BeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        System.out.println("MyBeanDefinitionRegistryPostProcessor...postProcessBeanFactory...的bean数="+beanDefinitionCount);
        System.out.println("执行BeanDefinitionRegistryPostProcessor.configurableListableBeanFactory");
    }
}
