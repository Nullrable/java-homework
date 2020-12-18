package com.lsd.demo;

import com.lsd.rpcfx.core.RpcfxServiceScan;
import com.lsd.rpcfx.core.api.RpcfxResolver;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 4:37 PM
 * @Modified By：
 */
public class ReflectRpcfxResolver implements RpcfxResolver {

    private ConcurrentHashMap map = new ConcurrentHashMap();

    @Override
    public Object resolve(String serviceClass) {

        Annotation annotation = AnnotationUtils.findAnnotation(Bootstrap.class, RpcfxServiceScan.class);

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
