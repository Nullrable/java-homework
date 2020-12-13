package com.lsd.modulework05all.proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.CallbackFilter;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-12-13 14:48
 * @Modified By：
 */
public class ServiceFilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        if ("print".equals(method.getName())) {
            return 0;   // Callback 列表第1个拦截器
        }
        return 1;   // Callback 列表第2个拦截器，return 2 则为第3个，以此类推
    }
}
