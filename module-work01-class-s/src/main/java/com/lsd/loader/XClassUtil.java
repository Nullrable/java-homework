package com.lsd.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-20 15:29
 */
public class XClassUtil {

    public static byte[] decode(byte[] source) {

        for (int i = 0; i < source.length; i++) {
            source[i] = intToByte(255 - byteToInt(source[i]));
        }

        return source;
    }

    //将文件转换成Byte数组
    public static  byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            byte[] data = bos.toByteArray();

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if(bos != null){
                    bos.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if(fis != null){
                    fis.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return null;
    }


    /**
     * byte转int类型
     * 如果byte是负数，则转出的int型是正数
     *
     * @param b
     * @return
     */
    public static int byteToInt(byte b) {
        int x = b & 0xff;
        return x;
    }

    /**
     * int 类型转换为byte 类型
     * 截取int类型的最后8位,与 0xff
     *
     * @param x
     * @return
     */
    public static byte intToByte(int x) {
        byte b = (byte) (x & 0xff);
        return b;

    }
}
