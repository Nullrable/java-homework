package com.lsd.cong.comsumer;

import com.lsd.cong.core.config.SPI;
import com.lsd.cong.core.consumer.URL;
import java.util.List;

/**
 * @Author: nhsoft.lsd
 */
public interface ServiceDiscovery extends SPI {

    List<URL> getURL(String serviceName);
}
