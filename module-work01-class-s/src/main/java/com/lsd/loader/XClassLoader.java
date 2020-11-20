package com.lsd.loader;


/**
 * @Author: nhsoft.lsd
 * @Description:
 * @Date:Create：in 2020-10-17 16:35
 * @Modified By：
 */
public class XClassLoader extends ClassLoader {

    private static final String XLASS_PATH = "D:\\git-work\\JAVA000\\java-homework\\module-work01-class-s\\src\\main\\resources\\file\\";



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {


        String xlassFile =  XLASS_PATH + name + ".xlass";

        byte[] encodeByte = XClassUtil.getBytesByFile(xlassFile);

        byte[] decodeByte = XClassUtil.decode(encodeByte);

        return defineClass(name, decodeByte, 0, decodeByte.length);

    }




}
