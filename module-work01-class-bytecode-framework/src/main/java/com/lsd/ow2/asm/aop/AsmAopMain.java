package com.lsd.ow2.asm.aop;

import com.lsd.ow2.asm.MyClassLoader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.objectweb.asm.*;

/**
 * @Author: nhsoft.lsd
 */
public class AsmAopMain extends ClassLoader{

    public static class Monitor {

        static long start = 0;

        public static void start(){
            start = System.currentTimeMillis();
        }
        public static void end(){
            long end = System.currentTimeMillis();
            System.out.println("execute method use time: " + (end - start) + " millis");
        }

    }

    public static class Foo {

        public static void execute() {
            System.out.println("test changed method name");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException, IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, InvocationTargetException, ClassNotFoundException {


//        Foo1.execute();

        ClassReader cr = new ClassReader(Foo.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MethodChangeClassAdapter(cw);
        cr.accept(cv, Opcodes.ASM4);

        // gets the bytecode of the Example class, and loads it dynamically
        byte[] code = cw.toByteArray();

//        FileOutputStream fos = new FileOutputStream("module-work01-class-bytecode-framework/src/main/java/com/lsd/asm/aop/FooProxy.class");
//        fos.write(code);
//        fos.close();
//        Class<?> exampleClass = AsmAopMain.class.getClassLoader().loadClass("com.lsd.asm.aop.FooProxy");

        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> exampleClass = myClassLoader.defineClass(Foo.class.getName(), code);

        for(Method method:  exampleClass.getMethods()){
            System.out.println(method);
        }

        exampleClass.getMethods()[0].invoke(null, null);  //調用execute，修改方法內容

    }



    public static class MethodChangeClassAdapter extends ClassVisitor implements Opcodes {

        public MethodChangeClassAdapter(final ClassVisitor cv) {
            super(Opcodes.ASM4, cv);
        }

//        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//            //更改类名，并使新类继承原有的类。
//            super.visit(version, access, name + "Proxy", signature, name, interfaces);
//
//        }

        @Override
        public MethodVisitor visitMethod(
                int access,
                String name,
                String desc,
                String signature,
                String[] exceptions) {
            if ("execute".equals(name))  //此处的execute即为需要修改的方法  ，修改方法內容
            {
                MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);//先得到原始的方法
                MethodVisitor newMethod = null;
                newMethod = new AsmMethodVisit(mv); //访问需要修改的方法
                return newMethod;
            }

            return super.visitMethod(access, name, desc, signature, exceptions);

        }


    }

    public static class AsmMethodVisit extends MethodVisitor {

        public AsmMethodVisit(MethodVisitor mv) {
            super(Opcodes.ASM4, mv);
        }

        @Override
        public void visitCode() {
            //此方法在访问方法的头部时被访问到，仅被访问一次
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/lsd/ow2/asm/aop/AsmAopMain$Monitor",  "start", "()V", false);
            super.visitCode();
        }

        @Override
        public void visitInsn(int opcode) {
            //此方法可以获取方法中每一条指令的操作类型，被访问多次
            //如应在方法结尾处添加新指令，则应判断：
        if(opcode == Opcodes.RETURN)
        {
            visitMethodInsn(Opcodes.INVOKESTATIC, "com/lsd/ow2/asm/aop/AsmAopMain$Monitor",  "end", "()V", false);
        }
            super.visitInsn(opcode);
        }

    }
}
