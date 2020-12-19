package com.lsd.rpcfx.core.client;

import com.alibaba.fastjson.JSON;
import com.lsd.rpcfx.core.api.RpcfxRequest;
import com.lsd.rpcfx.core.api.RpcfxResponse;
import com.lsd.rpcfx.core.exception.RpcfxException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 10:19 PM
 * @Modified By：
 */
public class RpcfxClientOkHttp implements RpcfxClient{

    @Override
    public Object invoke(InvokerMetadata metadata)throws Throwable {
        RpcfxRequest rpcfxrequest = new RpcfxRequest();
        rpcfxrequest.setMethod(metadata.getMethodName());
        rpcfxrequest.setParamters(metadata.getArgs());
        rpcfxrequest.setServiceClass(metadata.getServiceClass());

        String reqJson = JSON.toJSONString(rpcfxrequest);

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(metadata.getServerUrl())
                .post(RequestBody.create(MediaType.get(metadata.getMediaType()), reqJson))
                .build();

        String respJson = client.newCall(request).execute().body().string();

        System.out.println("resp json: "+respJson);

        RpcfxResponse response = JSON.parseObject(respJson, RpcfxResponse.class);

        if (response.getStatus() != null && !response.getStatus() ) {

            RpcfxException exception = response.getException();

            throw exception.getCause() == null ? exception : exception.getCause();
        }

        return JSON.parse(response.getResult().toString());
    }
}
