package com.lsd.asm;

import java.io.IOException;
import org.objectweb.asm.ClassReader;

/**
 * @Author: nhsoft.lsd
 */
public class ClassPrinterMain {

    public static void main (String[] args) throws IOException {

        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);

    }
}
