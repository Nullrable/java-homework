package com.lsd.demo.client;

import com.alibaba.fastjson.JSON;
import com.lsd.demo.service.User;
import com.lsd.demo.service.UserService;
import com.lsd.rpcfx.core.client.RpcfxClient;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:37 PM
 * @Modified By：
 */
public class Main {

    public static void main (String args[]) {

        System.setProperty("fastjson.parser.autoTypeSupport", "true");

        UserService userService = RpcfxClient.create(UserService.class, "http://localhost:8080");
        User user = userService.findById("1");
        System.out.println(JSON.toJSONString(user));

    }
}
