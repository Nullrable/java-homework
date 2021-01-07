package com.lsd.cong.comsumer;

import com.lsd.cong.core.config.CongReference;
import com.lsd.cong.comsumer.proxy.CglibProxyFactory;
import com.lsd.cong.core.config.CongReferenceScan;
import java.lang.annotation.ElementType;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.*;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

/**
 * @Author: nhsoft.lsd
 */
public class ReferenceAutowire implements BeanDefinitionRegistryPostProcessor {



    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        registryCongReferenceToSpring(registry);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException{

        System.out.println("执行BeanDefinitionRegistryPostProcessor.configurableListableBeanFactory");

    }

    private void registryCongReferenceToSpring(BeanDefinitionRegistry registry){

        try {

            //TODO 加载太慢啦
            Reflections reflections = new Reflections(new SubTypesScanner(false), new TypeAnnotationsScanner());
            Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(CongReferenceScan.class);

            classSet.forEach(klass ->{
                CongReferenceScan congReferenceScan = klass.getAnnotation(CongReferenceScan.class);

                String[] basePackages = congReferenceScan.basePackages();

                for (String basePackage : basePackages) {

                    Set<Field> fields = new Reflections(basePackage, new FieldAnnotationsScanner())
                            .getFieldsAnnotatedWith(CongReference.class);

                    for (Field field : fields) {

                        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(CglibProxyFactory.class);
                        MutablePropertyValues values = new MutablePropertyValues();
                        values.add("serviceInterface", field.getType().getName());
                        rootBeanDefinition.setPropertyValues(values);
                        registry.registerBeanDefinition(StringUtils.uncapitalize(field.getType().getSimpleName()), rootBeanDefinition);
                        System.out.println("CglibProxyFactory 注入成功" + field.getDeclaringClass().getName());
                    }

                }

            });

        }catch (Exception e) {

        }


    }
}
