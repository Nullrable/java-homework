package com.lsd.loader;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-20 16:35
 */
public class XClassLoaderCache {

    private XClassLoader xClassLoader;
    private ConcurrentHashMap<String, Class> registry;


    public XClassLoaderCache() {
        this.xClassLoader = new XClassLoader();
        registry = new ConcurrentHashMap<>();
    }

    public Class loadClass(String name)throws Exception {

        try {

            Class aClass = registry.get(name);

            if ( aClass == null) {

                aClass = xClassLoader.findClass(name);

                registry.putIfAbsent(name, aClass);
            }

            return aClass;

        }catch (Exception e) {
            throw e;
        }

    }


    public void unload () {

        registry.clear();

        xClassLoader = null;

        //在另外的线程中执行是因为，方法是虚拟机执行的最小单元，调用unload方法时会生成一个栈帧放到栈顶。unload方法的局部变量就会存在与栈帧中的局部变量表中，这里就有xClassLoader类加载器及Hello类实例的引用，还包括一些动态链接，所以在GC时，由于栈帧中的内容是作为GC ROOT的，所以肯定不会被回收，故而不会进行类的卸载。
        //注意在实际开发中一定要保证类的实例, 该类的ClassLoader都被回收，并且没该类不可以被反射调用，才可以类卸载。
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.gc();
                }catch (Exception e) {

                }

            }
        });
        thread.start();

    }
}
