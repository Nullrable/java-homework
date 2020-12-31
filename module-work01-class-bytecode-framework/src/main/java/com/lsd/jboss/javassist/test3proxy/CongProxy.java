package com.lsd.jboss.javassist.test3proxy;

import javassist.*;

/**
 * @Author: nhsoft.lsd
 */
public class CongProxy {

    public static Object create(String className) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {

        ClassPool classPool = ClassPool.getDefault();

        CtClass newClass = classPool.makeClass(className + "&CongProxy");

        CtClass interfaceCtClass = classPool.get(className);

        newClass.addInterface(interfaceCtClass);

        CtMethod ctMethod = interfaceCtClass.getDeclaredMethod("say");

        CtClass returnType = ctMethod.getReturnType();
        CtClass[] parameters = ctMethod.getParameterTypes();

        CtMethod newMethod = new CtMethod(returnType, "say", parameters, newClass);

        String methodBody = "{ System.out.println (\" i am \"+ $1); return null; }";
        newMethod.setBody(methodBody);

        newClass.addMethod(newMethod);

        Class proxyClass = newClass.toClass();

        return proxyClass.newInstance();
    }
}
