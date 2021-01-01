package com.lsd.net.bytebuddy.simple;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: nhsoft.lsd
 */
public class Utils {

    public static void outputClazz(byte[] bytes) {

        outputClazz(bytes, "ByteBuddyHelloWorld.class");
    }

    public static void outputClazz(byte[] bytes, String className) {
        FileOutputStream out = null;
        try {
            String pathName = ByteBuddyTest1.class.getResource("/").getPath() + className;
            out = new FileOutputStream(new File(pathName));
            System.out.println("类输出路径：" + pathName);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
