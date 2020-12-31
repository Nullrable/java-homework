package com.lsd.jboss.javassist.test2;

/**
 * @Author: nhsoft.lsd
 */
public class MyApp {

    public static void main(String[] args) throws Exception {
        System.out.println(MyApp.class.getField("hiddenValue").getName());

        /**
         *
         * getField
         * 获取一个类的 ==public成员变量，包括基类== 。
         *
         * getDeclaredField
         * 获取一个类的 ==所有成员变量，不包括基类== 。
         *
         * Field.setAccessible
         * 成员变量为private，必须进行此操作。
         *
         *
         */
    }

}
