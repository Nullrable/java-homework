package com.lsd.test.dynmic.source.remote;

import com.lsd.test.dynmic.source.dto.RedisConfig;

public interface RedisConfigService {

    RedisConfig read(String tenantId);
}
