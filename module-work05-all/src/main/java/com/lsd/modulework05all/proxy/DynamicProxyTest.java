package com.lsd.modulework05all.proxy;

import com.lsd.modulework05all.proxy.service.UserService;
import com.lsd.modulework05all.proxy.service.impl.UserServiceImpl;
import org.springframework.cglib.core.DebuggingClassWriter;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-13 14:30
 * @Modified By：
 */
public class DynamicProxyTest {

    public static void main(String args[]) {


//        UserService userService = DynamicProxyByJava.create(UserServiceImpl.class);
//        userService.print();
//
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "~/Desktop/proxy/");

        UserService userService = DynamicProxyByCglib.create(UserServiceImpl.class);
        userService.show();

//        // 保存JDK动态代理生成的代理类，类名保存为 UserServiceProxy
        JDKDynamicProxyUtils.generateClassFile(userService.getClass(), "UserServiceProxy2");

    }
}
