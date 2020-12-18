package com.lsd.demo;

import com.lsd.demo.service.UserService;
import com.lsd.demo.service.impl.UserServiceImpl;
import com.lsd.rpcfx.core.api.RpcfxRequest;
import com.lsd.rpcfx.core.api.RpcfxResolver;
import com.lsd.rpcfx.core.api.RpcfxResponse;
import com.lsd.rpcfx.core.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:26 PM
 * @Modified By：
 */
@SpringBootApplication
@RestController
public class Bootstrap {

    public static void main (String args[]) {

        SpringApplication.run(Bootstrap.class, args);

    }

    @Autowired
    RpcfxInvoker invoker;

    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }


    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver){
        return new RpcfxInvoker(resolver);
    }

    @Bean
    public RpcfxResolver createResolver(){
        return new DefaultResolver();
    }



    @Bean(name = "com.lsd.demo.service.UserService")
    public UserService createUserService(){
        return new UserServiceImpl();
    }
}
