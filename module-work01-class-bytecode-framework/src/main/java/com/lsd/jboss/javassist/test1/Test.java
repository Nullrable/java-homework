package com.lsd.jboss.javassist.test1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @Author: nhsoft.lsd
 */
public class Test {

    public static void main(String[] args) throws Exception {

        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.lsd.jboss.javassist.test1.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();

        System.out.println("-------------------");

        Hello hello = new Hello();
        hello.say();

        System.out.println("-------------------");

        Hello hello1 = new Hello();
        hello1.say();

        /**
         *
         * 输出
         *
         * Hello.say():
         * Hello
         * -------------------
         * Hello.say():
         * Hello
         * -------------------
         * Hello.say():
         * Hello
         *
         * 可以看出cc.toClass()，直接改了内存的class内容
         */

    }
}
