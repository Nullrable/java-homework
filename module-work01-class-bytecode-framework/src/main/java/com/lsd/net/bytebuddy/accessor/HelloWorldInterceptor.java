package com.lsd.net.bytebuddy.accessor;

/**
 * @Author: nhsoft.lsd
 */
public class  HelloWorldInterceptor implements Interceptor {
    @Override
    public String doSomethingElse() {
        return "Hello World!";
    }
}
