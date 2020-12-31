package com.lsd.ow2.asm;

/**
 * @Author: nhsoft.lsd
 */
public class MyClassLoader extends ClassLoader {
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}
