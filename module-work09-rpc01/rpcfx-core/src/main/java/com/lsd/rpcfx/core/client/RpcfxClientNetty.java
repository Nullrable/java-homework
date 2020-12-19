package com.lsd.rpcfx.core.client;

import com.alibaba.fastjson.JSON;
import com.lsd.rpcfx.core.api.RpcfxRequest;
import com.lsd.rpcfx.core.api.RpcfxResponse;
import com.lsd.rpcfx.core.exception.RpcfxException;


/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/19/20 10:16 AM
 * @Modified By：
 */
public class RpcfxClientNetty {

    public Object invoke(InvokerMetadata metadata)throws Throwable {
        RpcfxRequest rpcfxrequest = new RpcfxRequest();
        rpcfxrequest.setMethod(metadata.getMethodName());
        rpcfxrequest.setParamters(metadata.getArgs());
        rpcfxrequest.setServiceClass(metadata.getServiceClass());

        String reqJson = JSON.toJSONString(rpcfxrequest);

        String[] array = metadata.getServerUrl().split(":");
        String host = array[0];
        int port = Integer.parseInt(array[1]);

        NettyClient nettyClient = new NettyClient(host, port);

        System.out.println("req json: " + reqJson);

        String respJson = (String)nettyClient.doExecuteRequest(reqJson, metadata.getMediaType());

        System.out.println("resp json: "+respJson);

        RpcfxResponse response = JSON.parseObject(respJson, RpcfxResponse.class);

        if (response.getStatus() != null && !response.getStatus() ) {

            RpcfxException exception = response.getException();

            throw exception.getCause() == null ? exception : exception.getCause();
        }

        return JSON.parse(response.getResult().toString());

    }
}