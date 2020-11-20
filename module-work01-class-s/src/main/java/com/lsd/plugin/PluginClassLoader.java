package com.lsd.plugin;

import com.lsd.loader.XClassUtil;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-11-20 22:16
 * @Modified By：
 */
public class PluginClassLoader extends ClassLoader{


    //TODO 改为扫描整个文件夹
    private static final String JAR_PATH = "/lib/my-plugin.jar";

    private Map<String, byte[]> map = new HashMap<>();

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = map.get(name);

        if (bytes == null || bytes.length == 0) {

            decompress(JAR_PATH);

            bytes = map.get(name);

            if (bytes == null || bytes.length == 0) {
                throw new ClassNotFoundException();
            }

            return defineClass(name, bytes, 0, bytes.length);

        }else {

            return defineClass(name, bytes, 0, bytes.length);
        }
    }


    private void decompress(String path)  {

        try {

            JarFile jarFile = new JarFile(this.getClass().getResource(path).getFile());

            Enumeration entries  = jarFile.entries();

            while (entries.hasMoreElements()) {

                JarEntry jarEntry = (JarEntry)entries.nextElement();

                if ( jarEntry.getName().endsWith(".class") ) { //是否为一个文件夹

                    byte[] bytes = new byte[0];
                    InputStream inputStream = jarFile.getInputStream(jarEntry);
                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);

                    String name = jarEntry.getName();

                    String fullClassName = name.replace(".class", "").replace("/", ".");

                    map.putIfAbsent(fullClassName, bytes);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
