package com.lsd.demo.service.impl;

import com.lsd.demo.service.User;
import com.lsd.demo.service.UserService;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 2:23 PM
 * @Modified By：
 */
public class UserServiceImpl implements UserService {

    @Override
    public User findById(String id) {
        User user = new User();
        user.setName("hello " + id + " !" );
        int i = 1/0;
        return user;
    }
}
