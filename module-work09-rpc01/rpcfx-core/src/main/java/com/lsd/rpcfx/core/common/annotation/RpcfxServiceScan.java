package com.lsd.rpcfx.core.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 12/18/20 4:43 PM
 * @Modified By：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface RpcfxServiceScan {

    String[] value();
}
