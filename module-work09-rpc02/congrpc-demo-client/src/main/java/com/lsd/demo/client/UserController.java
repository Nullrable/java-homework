package com.lsd.demo.client;

import com.lsd.cong.core.config.CongReference;
import com.lsd.demo.service.UserService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: nhsoft.lsd
 */
@RestController
public class UserController {

    @CongReference
    @Resource
    private UserService userService;

    @GetMapping("/test")
    private String test() {
        String name = userService.read("流苏");
        return name;
    }
}
