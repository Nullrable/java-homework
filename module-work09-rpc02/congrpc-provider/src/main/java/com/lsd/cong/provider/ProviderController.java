package com.lsd.cong.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lsd.cong.core.consumer.CongResponse;
import com.lsd.cong.core.consumer.Invocation;
import com.lsd.cong.core.exception.CongRpcException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nhsoft.lsd
 */
@RestController
public class ProviderController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/")
    public CongResponse invoke(@RequestBody Invocation request) throws ClassNotFoundException {

        Object service = applicationContext.getBean(Class.forName(request.getServiceName()));

        String methodName = request.getMethod();
        Object[] args = request.getArguments();

        Method[] methods = service.getClass().getMethods();

        Method method = getMethod(methodName, methods);

        CongResponse response = new CongResponse();
        try {

            Object result = method.invoke(service, args);
            response.setStatus(true);
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));

            return response;
        }catch (InvocationTargetException | IllegalAccessException re) {

            Throwable mainException = re.getCause();

            if (mainException instanceof CongRpcException) {
                response.setStatus(false);
                response.setException((CongRpcException)mainException);
            }else {
                response.setStatus(false);
                response.setException(new CongRpcException(mainException));
            }

            return response;

        }
    }

    private Method getMethod (String methodName, Method[] methods) {

        return Arrays.stream(methods).filter(m -> m.getName().equals(methodName)).findFirst().get();

    }
}
