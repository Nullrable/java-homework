package com.lsd.test.dynmic.source.config.datasource;

import com.lsd.test.dynmic.source.dto.TenantDbConfig;
import java.util.*;

/**
 *
 * 简单轮训实现
 *
 * @Author: nhsoft.lsd
 */
public class RoundRobin {

    private static Integer pos = 0;

    public static TenantDbConfig getDataSource(List<TenantDbConfig> tenantDbConfigs) {

        TenantDbConfig server = null;
        synchronized (pos)
        {
            if (pos > tenantDbConfigs.size())
                pos = 0;
            server = tenantDbConfigs.get(pos);
            pos ++;
        }

        return server;
    }
}
