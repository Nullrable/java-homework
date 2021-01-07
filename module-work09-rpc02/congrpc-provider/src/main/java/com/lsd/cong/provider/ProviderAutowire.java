package com.lsd.cong.provider;

import com.lsd.cong.core.config.CongProvider;
import com.lsd.cong.core.consumer.URL;
import com.lsd.cong.core.registry.ServiceRegister;
import com.lsd.cong.provider.registry.ServiceRegistryConfiguration;
import java.util.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: nhsoft.lsd
 */
public class ProviderAutowire implements  ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Autowired
    private ServiceRegistryConfiguration serviceRegistryConfiguration;

    @Autowired
    private ServiceRegister serviceRegister;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet()  {
        Map<String, Object> providerMap = applicationContext.getBeansWithAnnotation(CongProvider.class);

        String protocol = serviceRegistryConfiguration.getProtocol();
        String host = serviceRegistryConfiguration.getHost();
        String path = serviceRegistryConfiguration.getPath();
        int port = serviceRegistryConfiguration.getPort();

        List<URL> urls = new ArrayList<>();
        providerMap.values().forEach(provider -> {

            String serviceName = provider.getClass().getInterfaces()[0].getName();

            CongProvider congProvider = provider.getClass().getAnnotation(CongProvider.class);

            List<String> groups = Arrays.asList(congProvider.group()) ;

            Map<String, String> parameters = new HashMap<>();

            URL serviceInfo = new URL(
                    protocol,
                    host,
                    port,
                    path,
                    serviceName,
                    groups,
                    parameters);
            urls.add(serviceInfo);

        });

        serviceRegister.register(urls);
    }
}
