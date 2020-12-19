package com.lsd.rpcfx.core.client;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/19/20 3:16 PM
 * @Modified By：
 */
public interface RpcfxClient {

    Object invoke(InvokerMetadata metadata)throws Throwable ;
}
