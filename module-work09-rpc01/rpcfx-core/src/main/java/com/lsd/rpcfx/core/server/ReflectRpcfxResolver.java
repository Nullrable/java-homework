package com.lsd.rpcfx.core.server;

import com.lsd.rpcfx.core.common.annotation.RpcfxServiceScan;
import com.lsd.rpcfx.core.api.RpcfxResolver;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 4:37 PM
 * @Modified By：
 */
public class ReflectRpcfxResolver implements RpcfxResolver, ApplicationContextAware {

    private ConcurrentHashMap map = new ConcurrentHashMap();

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {


        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RpcfxServiceScan.class);

        List<Object> beans =  new ArrayList<>(beanMap.values());

        if(beans == null){
            return null;
        }
        if (beans != null && beans.size() > 1) {
            throw new IllegalArgumentException("annotation RpcfxServiceScan set only one");
        }

        Object bean = beans.get(0);

        Annotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), RpcfxServiceScan.class);

        String[] basePackages = ((RpcfxServiceScan) annotation).value();

        Object service = map.get(serviceClass);

        if (service == null) {
            try {
                service = get(basePackages, serviceClass).newInstance();
            }catch (IllegalAccessException | InstantiationException e) {
                System.out.println(serviceClass + " initialization error");
                return null;
            }
        }

        map.putIfAbsent(serviceClass, service);

        return service;
    }

    private Class get(String[] basePackages, String serviceClass){

        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());


        for (String basePackage : basePackages) {

            Reflections reflections = new Reflections(basePackage);

            try {

                Class clazz = Class.forName(serviceClass);
                Set<Class> allClasses = reflections.getSubTypesOf(clazz);

                if (allClasses == null || allClasses.size() == 0) {
                    throw new IllegalStateException(serviceClass + "has no implements");
                }

                if (allClasses.size() > 1) {
                    throw new IllegalStateException(serviceClass + "more than one");
                }


                Iterator iterator = allClasses.iterator();

                return (Class) iterator.next();

            }catch (ClassNotFoundException e) {

                System.out.println(serviceClass + " not found");

                return null;
            }


        }

        return null;

    }
}
