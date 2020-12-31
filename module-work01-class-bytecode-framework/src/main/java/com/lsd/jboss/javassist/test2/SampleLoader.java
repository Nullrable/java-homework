package com.lsd.jboss.javassist.test2;

import java.io.IOException;
import javassist.*;

/**
 * @Author: nhsoft.lsd
 */
public class SampleLoader extends ClassLoader{

    /* Call MyApp.main().
     */
    public static void main(String[] args) throws Throwable {
        SampleLoader s = new SampleLoader();
        Class c = s.findClass("com.lsd.jboss.javassist.test2.MyApp");

        c.getDeclaredMethod("main", new Class[] { String[].class })
                .invoke(null, new Object[] { args });
    }

    private ClassPool pool;

    public SampleLoader() throws NotFoundException {
        pool = new ClassPool();
        pool.insertClassPath(new ClassClassPath(SampleLoader.class)); // MyApp.class must be there.
    }

    /* Finds a specified class.
     * The bytecode for that class can be modified.
     */
    protected Class findClass(String name) throws ClassNotFoundException {
        try {
            CtClass cc = pool.get(name);
            // modify the CtClass object here
            CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
            f.setModifiers(Modifier.PUBLIC);
            cc.addField(f);

            byte[] b = cc.toBytecode();

            return defineClass(name, b, 0, b.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        } catch (NotFoundException e) {
            throw new ClassNotFoundException();
        } catch (CannotCompileException e) {
            throw new ClassNotFoundException();
        }
    }


}
