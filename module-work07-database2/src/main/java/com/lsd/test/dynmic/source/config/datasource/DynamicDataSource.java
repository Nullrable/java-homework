package com.lsd.test.dynmic.source.config.datasource;

import com.lsd.test.dynmic.source.config.AppContextHolder;
import com.lsd.test.dynmic.source.dto.TenantDbConfig;
import com.lsd.test.dynmic.source.remote.TenantDbService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Lazy
    @Autowired
    private DynamicDataSourceSummoner summoner;

    @Lazy
    @Autowired
    private TenantDbService tenantDbService;

    private static boolean loaded = false;

    @PostConstruct
    public void init(){
        // 防止重复执行
        if (!loaded) {
            loaded = true;
            try {
                summoner.registerDynamicDataSources();
            } catch (Exception e) {

                log.error("数据源初始化失败, Exception:", e);
            }
        }
    }

    @Override
    protected String determineCurrentLookupKey() {

        TenantDbConfig tenantDbConfig = getTenantDbConfig();

        String beanKey = DataSourceUtil.getDataSourceBeanId(tenantDbConfig.getName());

        return DataSourceUtil.getDataSourceBeanId(beanKey);
    }

    @Override
    protected DataSource determineTargetDataSource() {



        TenantDbConfig tenantDbConfig = getTenantDbConfig();

        String tenantId = tenantDbConfig.getTenantId();

        String beanKey = DataSourceUtil.getDataSourceBeanId(tenantDbConfig.getName());

        if (!StringUtils.hasText(tenantId) || applicationContext.containsBean(beanKey)) {
            return super.determineTargetDataSource();
        }
        if (tenantDbService.exist(tenantId)) {
            summoner.registerDynamicDataSources();
        }
        return super.determineTargetDataSource();
    }

    private TenantDbConfig getTenantDbConfig() {
        String tenantId = AppContextHolder.getSourceKey();
        Boolean readOnly = AppContextHolder.getReadOnly();

        List<TenantDbConfig> tenantDbConfigs = tenantDbService.listByTenantId(tenantId, readOnly);

        Assert.notEmpty(tenantDbConfigs, "TenantDbConfig not empty");

        TenantDbConfig tenantDbConfig = null;

        if (readOnly) {
            //简易负载均衡 - 轮询实现
            tenantDbConfig =  RoundRobin.getDataSource(tenantDbConfigs);
        }else {

            if (tenantDbConfigs.size() > 1){

                throw new IllegalStateException("master database only create one");
            }

            //master 只会有一个
            tenantDbConfig = tenantDbConfigs.get(0);
        }

        return tenantDbConfig;
    }
}
