package com.lsd.cong.comsumer;

import com.alibaba.fastjson.JSON;
import com.lsd.cong.core.consumer.CongResponse;
import com.lsd.cong.core.consumer.Invocation;
import com.lsd.cong.core.consumer.Invoker;
import com.lsd.cong.core.consumer.URL;
import com.lsd.cong.core.exception.CongRpcException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Author: nhsoft.lsd
 */
public class OkHttpInvoker implements Invoker {

    private URL url;


    public void setUrl(URL url) {
        this.url = url;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public Object invoke(Invocation invocation) throws Throwable {
        String reqJson = JSON.toJSONString(invocation);

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(invocation.getServiceUrl())
                .post(RequestBody.create(MediaType.get(invocation.getMediaType()), reqJson))
                .build();

        String respJson = client.newCall(request).execute().body().string();

        System.out.println("resp json: "+respJson);

        CongResponse response = JSON.parseObject(respJson, CongResponse.class);

        if (response.getStatus() != null && !response.getStatus() ) {

            CongRpcException exception = response.getException();

            throw exception.getCause() == null ? exception : exception.getCause();
        }

        return JSON.parse(response.getResult().toString());
    }
}
