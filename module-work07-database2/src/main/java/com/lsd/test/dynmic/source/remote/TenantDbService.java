package com.lsd.test.dynmic.source.remote;

import com.lsd.test.dynmic.source.dto.TenantDbConfig;
import java.util.List;

public interface TenantDbService {

    List<TenantDbConfig> listAll();

    List<TenantDbConfig> listByTenantId(String tenantId, Boolean readOnly);

    Boolean exist(String tenantId);

}
