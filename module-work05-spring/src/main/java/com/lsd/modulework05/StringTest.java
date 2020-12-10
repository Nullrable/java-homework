package com.lsd.modulework05;


/**
 * @description:
 * @author: nhsoft.lsd
 * @create: 2020-11-26 16:01
 */
public class StringTest {

    public static void main (String args[]) {

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = s3.intern();

        String s5 = "ab" + "c";

        String s6 = new String("ab" + "c");

        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("s1.equals(s3): " + s1.equals(s3));
        System.out.println("s3.equals(s4): " + s3.equals(s4));
        System.out.println("s1.equals(s5): " + s1.equals(s5));
        System.out.println("s5.equals(s6): " + s5.equals(s6));

    }


}
