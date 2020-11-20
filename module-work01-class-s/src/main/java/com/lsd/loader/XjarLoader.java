package com.lsd.loader;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;

/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-20 15:21
 */
public class XjarLoader extends ClassLoader {

    private static final String XJAR_PATH = "/lib/Hello.xar";

    private Map<String, byte[]> map = new HashMap<>();

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] bytes = map.get(name);

        if (bytes == null || bytes.length == 0) {

            decompress(this.getClass().getResource(XJAR_PATH).getFile() );

            bytes = map.get(name);

            if (bytes == null || bytes.length == 0) {
                throw new ClassNotFoundException();
            }

            byte[] decodeByte = XClassUtil.decode(bytes);

            return defineClass(name, decodeByte, 0, decodeByte.length);

        }else {
            byte[] decodeByte = XClassUtil.decode(bytes);

            return defineClass(name, decodeByte, 0, decodeByte.length);
        }
    }


    private void decompress(String path)  {

        try {
            XjarFile jarFile = new XjarFile(path);

            Enumeration entries  = jarFile.entries();

            while (entries.hasMoreElements()) {

                ZipEntry jarEntry = (ZipEntry)entries.nextElement();

                if ( !jarEntry.isDirectory() ) { //是否为一个文件夹

                    byte[] bytes = new byte[0];
                    InputStream inputStream = jarFile.getInputStream(jarEntry);
                    bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);

                    String name = jarEntry.getName();

                    map.putIfAbsent(name.replace(".xlass", ""), bytes);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(String name) {
        map.remove(name);
    }

}
