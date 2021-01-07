package com.lsd.demo.server;

import com.lsd.cong.core.config.CongProvider;
import com.lsd.demo.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: nhsoft.lsd
 */
@Service
@CongProvider
public class UserServiceImpl implements UserService {

    @Override
    public String read(String name) {
        return "server: " + name;
    }
}
