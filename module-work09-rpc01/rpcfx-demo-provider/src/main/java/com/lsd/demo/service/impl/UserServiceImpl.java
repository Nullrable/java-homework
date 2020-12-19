package com.lsd.demo.service.impl;

import com.lsd.demo.service.User;
import com.lsd.demo.service.UserService;
import com.lsd.rpcfx.core.exception.RpcfxException;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:23 PM
 * @Modified By：
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findByName(String name) {
        User user = new User(name);

        System.out.println("receive request from client: " + name );
        return user;
    }

    @Override
    public User findById(String id) {
        throw new RpcfxException("method findById not supported");
    }
}
