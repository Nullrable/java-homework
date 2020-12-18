package com.lsd.rpcfx.core.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lsd.rpcfx.core.api.RpcfxRequest;
import com.lsd.rpcfx.core.api.RpcfxResolver;
import com.lsd.rpcfx.core.api.RpcfxResponse;
import com.lsd.rpcfx.core.exception.RpcfxException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 11:39 AM
 * @Modified By：
 */
public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }

    public RpcfxResponse invoke (RpcfxRequest request) {

        RpcfxResponse response = new RpcfxResponse();

        Object service = resolver.resolve(request.getServiceClass());

        String methodName = request.getMethod();
        Object[] args = request.getParamters();

        Method[] methods = service.getClass().getMethods();

        Method method = getMethod(methodName, methods);

        try {

            Object result = method.invoke(service, args);
            response.setStatus(true);
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));

            return response;
        }catch (Exception e){

            e.printStackTrace();

            response.setStatus(false);
            response.setException(new RpcfxException("server happens an error, see more information on server side"));

            return response;
        }

    }

    private Method getMethod (String methodName, Method[] methods) {

        return Arrays.stream(methods).filter( m -> m.getName().equals(methodName)).findFirst().get();

    }
}
