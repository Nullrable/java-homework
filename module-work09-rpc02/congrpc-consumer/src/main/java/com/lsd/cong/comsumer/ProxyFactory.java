package com.lsd.cong.comsumer;

import com.lsd.cong.core.config.SPI;
import com.lsd.cong.core.exception.CongRpcException;

/**
 * @Author: nhsoft.lsd
 */
public interface ProxyFactory extends SPI {

    <T> T getProxy() throws CongRpcException;
}
