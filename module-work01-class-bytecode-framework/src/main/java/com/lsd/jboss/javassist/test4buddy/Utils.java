package com.lsd.jboss.javassist.test4buddy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: nhsoft.lsd
 */
public class Utils {

    public static void outputClazz(byte[] bytes) {
        FileOutputStream out = null;
        try {
            String pathName = ByteBuddyTest1.class.getResource("/").getPath() + "ByteBuddyHelloWorld.class";
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
