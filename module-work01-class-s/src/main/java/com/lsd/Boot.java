package com.lsd;

import com.lsd.loader.XClassLoaderCache;
import com.lsd.loader.XClassLoader;
import com.lsd.loader.XjarLoader;

import com.lsd.plugin.MyPlugin;
import com.lsd.plugin.PluginClassLoader;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-20 15:13
 */
public class Boot {


    public static void main (String args[])throws Exception {

        testPlugin();

        testDynamicLoadAndUnload();

        testXlassLoad();

        testXarLoad();

    }

    private static void testPlugin()throws Exception {

        System.out.println("30-基于自定义Classloader实现模块化机制：需要设计模块化机制。");

        PluginClassLoader pluginClassLoader = new PluginClassLoader();

        Class clazz = pluginClassLoader.loadClass("com.lsd.plugin.impl.MyPluginImpl");

        MyPlugin myPlugin = (MyPlugin)clazz.newInstance();

        System.out.println(myPlugin.say());

    }


    //-Xlog:class+unload=info -Xlog:class+load=info -verbose:class 使用上述参数打印加载卸载日志
    private static void testDynamicLoadAndUnload () {

        try {
            System.out.println("30-基于自定义Classloader实现类的动态加载和卸载：需要设计加载和卸载。");

            XClassLoaderCache xClassLoaderCache = new XClassLoaderCache();

            Class clazz =  xClassLoaderCache.loadClass("Hello");

            Method method = clazz.getMethod("hello");

            Object object = clazz.newInstance();
            method.invoke(object);

            xClassLoaderCache.unload();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private static void testXlassLoad () {

        try {

            System.out.println("10-使用自定义Classloader机制，实现xlass的加载：xlass是作业材料。");

            XClassLoader xClassLoader = new XClassLoader();

            Class clazz = xClassLoader.loadClass("Hello");

            Object object = clazz.newInstance();

            Method method = clazz.getMethod("hello");

            method.invoke(object);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private static void testXarLoad() {
        try {

            System.out.println("20-实现xlass打包的xar（类似class文件打包的jar）的加载：xar里是xlass。");

            XjarLoader xjarLoader = new XjarLoader();

            Class clazz = xjarLoader.loadClass("Hello");

            Object object = clazz.newInstance();

            Method method = clazz.getMethod("hello");

            method.invoke(object);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
