package com.lsd.modulework05all.proxy.service.impl;

import com.lsd.modulework05all.proxy.service.UserService;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-13 14:25
 * @Modified By：
 */
public class UserServiceImpl implements UserService {

    @Override
    public void print() {
        System.out.println("hello print");
    }

    @Override
    public void show() {
        System.out.println("hello show");
    }
}
