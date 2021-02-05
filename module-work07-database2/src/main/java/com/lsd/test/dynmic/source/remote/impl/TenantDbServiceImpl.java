package com.lsd.test.dynmic.source.remote.impl;

import com.lsd.test.dynmic.source.dto.TenantDbConfig;
import com.lsd.test.dynmic.source.remote.TenantDbService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TenantDbServiceImpl implements TenantDbService {

    List<TenantDbConfig> list = new ArrayList<>();

    {
        TenantDbConfig tenantDbConfig = new TenantDbConfig();
        tenantDbConfig.setTenantId("4020");
        tenantDbConfig.setDbUrl("jdbc:mysql://localhost:3306/lsd_test1");
        tenantDbConfig.setDbDriver("com.mysql.jdbc.Driver");
        tenantDbConfig.setDbUser("root");
        tenantDbConfig.setDbPassword("nhsoft123");
        list.add(tenantDbConfig);


        tenantDbConfig = new TenantDbConfig();
        tenantDbConfig.setTenantId("3030");
        tenantDbConfig.setDbUrl("jdbc:mysql://localhost:3306/lsd_geekbang");
        tenantDbConfig.setDbDriver("com.mysql.jdbc.Driver");
        tenantDbConfig.setDbUser("root");
        tenantDbConfig.setDbPassword("nhsoft123");
        list.add(tenantDbConfig);

    }


    @Override
    public List<TenantDbConfig> listAll() {
        return list;
    }

    @Override
    public Boolean exist(String tenantId) {
        return true;// TODO (lusudong, 2019/2/22)
    }

    @Override
    public List<TenantDbConfig> listByTenantId(String tenantId, Boolean readOnly) {
        return null;// TODO (lusudong, 2020-12-02)
    }
}
