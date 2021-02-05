package com.lsd.test.dynmic.source.remote.impl;

import com.lsd.test.dynmic.source.dto.RedisConfig;
import com.lsd.test.dynmic.source.remote.RedisConfigService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RedisConfigServiceImpl implements RedisConfigService {

    List<RedisConfig> configs = new ArrayList<>();
    {
        RedisConfig config = new RedisConfig();
        config.setTenantId("5255");
        config.setHost("localhost");
        config.setPassword("X51x5XnUuZDs0GUQ8shm");
        config.setPort(6379);
        config.setDatabase(1);
        configs.add(config);


        config = new RedisConfig();
        config.setTenantId("5256");
        config.setHost("localhost");
        config.setPassword("X51x5XnUuZDs0GUQ8shm");
        config.setPort(6379);
        config.setDatabase(2);
        configs.add(config);
    }

    @Override
    public RedisConfig read(String tenantId) {

        for(RedisConfig config : configs){

            if(config.getTenantId().equals(tenantId)){
                return config;
            }
        }
        return null;
    }
}
