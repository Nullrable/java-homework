package com.lsd.plugin.impl;

import com.lsd.plugin.MyPlugin;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-20 21:40
 * @Modified By：
 */
public class MyPluginImpl implements MyPlugin {

    @Override
    public String say() {
        return "Hello MyPluginImpl";
    }
}
