package com.lsd.rpcfx.core.server;

import com.lsd.rpcfx.core.common.annotation.RpcfxService;
import com.lsd.rpcfx.core.common.annotation.RpcfxServiceScan;
import com.lsd.rpcfx.core.common.serializer.MyZkSerializer;
import com.lsd.rpcfx.core.common.config.ServiceNodeConfig;
import com.lsd.rpcfx.core.common.metadata.ServiceInfo;
import com.lsd.rpcfx.core.server.register.ServiceRegister;
import com.lsd.rpcfx.core.server.register.ZookeeperServiceRegister;
import java.lang.annotation.Annotation;
import java.util.*;
import org.I0Itec.zkclient.ZkClient;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @Author: nhsoft.lsd
 */
public class ZookeeperServiceAutoRegister {

    private ApplicationContext applicationContext;

    public ZookeeperServiceAutoRegister(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void register() {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(RpcfxServiceScan.class);

        List<Object> beans =  new ArrayList<>(beanMap.values());

        if (beans != null && beans.size() > 1) {
            throw new IllegalArgumentException("annotation RpcfxServiceScan set only one");
        }

        Object bean = beans.get(0);

        Annotation annotation = AnnotationUtils.findAnnotation(bean.getClass(), RpcfxServiceScan.class);

        String[] basePackages = ((RpcfxServiceScan) annotation).value();

        ServiceNodeConfig serviceNodeConfig =  applicationContext.getBean(ServiceNodeConfig.class);
        List<ServiceInfo> serviceInfoList = new ArrayList<>();
        try {
            for (String basePackage : basePackages) {

                Reflections reflections = new Reflections(basePackage);

                try {

                    Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(RpcfxService.class);

                    if (allClasses == null || allClasses.size() == 0) {
                        return;
                    }

                    allClasses.forEach( clazz -> {
                        try {
                            Class[] interfaceClazzs =  clazz.getInterfaces();

                            if (interfaceClazzs.length != 0) {

                                String serviceClazz = interfaceClazzs[0].getName();

                                ServiceInfo serviceInfo = new ServiceInfo();
                                serviceInfo.setServiceClass(serviceClazz);
                                serviceInfo.setUrl(serviceNodeConfig.getServiceUrl());
                                serviceInfoList.add(serviceInfo);
                            }


                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                }catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        ZkClient zkClient = new ZkClient(serviceNodeConfig.getServerUrl());
        zkClient.setZkSerializer(new MyZkSerializer());
        ServiceRegister serviceRegister = new ZookeeperServiceRegister(zkClient);
        serviceRegister.register(serviceInfoList);

    }

}
