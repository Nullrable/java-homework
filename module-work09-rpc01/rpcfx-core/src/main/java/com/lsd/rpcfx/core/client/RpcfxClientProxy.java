package com.lsd.rpcfx.core.client;

import com.alibaba.fastjson.JSON;
import com.lsd.rpcfx.core.api.RpcfxRequest;
import com.lsd.rpcfx.core.api.RpcfxResponse;
import com.lsd.rpcfx.core.exception.RpcfxException;
import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 11:38 AM
 * @Modified By：
 */
public class RpcfxClientProxy {



    public static  <T> T create(Class<T> kClass, String url){

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(kClass);
        enhancer.setCallbacks(new Callback[]{new RpcfxInvocationHandler(url, kClass.getName())});
        return (T)enhancer.create();

    }

}

class RpcfxInvocationHandler implements MethodInterceptor {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    private String serverUrl;
    private String serviceClass;

    public RpcfxInvocationHandler(String serverUrl, String serviceClass) {
        this.serverUrl = serverUrl;
        this.serviceClass = serviceClass;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        RpcfxRequest rpcfxrequest = new RpcfxRequest();
        rpcfxrequest.setMethod(method.getName());
        rpcfxrequest.setParamters(args);
        rpcfxrequest.setServiceClass(serviceClass);

        String reqJson = JSON.toJSONString(rpcfxrequest);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(serverUrl)
                .post(RequestBody.create(JSONTYPE, reqJson))
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
