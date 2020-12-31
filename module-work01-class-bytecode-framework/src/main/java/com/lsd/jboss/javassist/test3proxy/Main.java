package com.lsd.jboss.javassist.test3proxy;

import javassist.CannotCompileException;
import javassist.NotFoundException;

/**
 * @Author: nhsoft.lsd
 */
public class Main {

    public static void main (String args[]) throws CannotCompileException, InstantiationException, NotFoundException, IllegalAccessException {

        IUserService userService =  (IUserService)CongProxy.create(IUserService.class.getName());
        userService.say("lsd");
    }
}
