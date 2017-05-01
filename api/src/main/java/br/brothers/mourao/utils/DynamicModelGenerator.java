package br.brothers.mourao.utils;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.Serializable;
import java.util.Map;

public class DynamicModelGenerator {

    private static final ClassPool pool = ClassPool.getDefault();

    public static Class generate(String modelName, Map<String, Class<?>> attributes)
        throws ClassNotFoundException,
            NotFoundException,
            CannotCompileException {

        CtClass cc = pool.getOrNull(modelName);

        if (cc != null) {
            return Class.forName(modelName, false, pool.getClassLoader());
        }

        cc = pool.makeClass(modelName);
        cc.addInterface(resolveCtClass(Serializable.class));

        for (Map.Entry<String, Class<?>> entry : attributes.entrySet()) {
            cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));
            cc.addMethod(generateGetter(cc, entry.getKey(), entry.getValue()));
            cc.addMethod(generateSetter(cc, entry.getKey(), entry.getValue()));
        }

        cc.addField(new CtField(resolveCtClass(String.class), "_id", cc));

        return cc.toClass();
    }

    private static CtClass resolveCtClass(Class clazz)
        throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

    private static CtMethod generateGetter(CtClass declaringClass, String fieldName, Class fieldClass)
        throws CannotCompileException {

        String getterName = generateGetterName(fieldName);

        StringBuffer sb = new StringBuffer();
        sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
            .append("return this.").append(fieldName).append(";")
            .append("}");

        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static CtMethod generateSetter(CtClass declaringClass, String fieldName, Class fieldClass)
        throws CannotCompileException {

        String setterName = generateSetterName(fieldName);

        StringBuffer sb = new StringBuffer();
        sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ").append(fieldName).append(")").append("{")
            .append("this.").append(fieldName).append("=").append(fieldName).append(";")
            .append("}");

        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static String generateGetterName(String fieldName) {
        return generateMethodNameWithPrefix("get", fieldName);
    }

    private static String generateSetterName(String fieldName) {
        return generateMethodNameWithPrefix("set", fieldName);
    }

    private static String generateMethodNameWithPrefix(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private DynamicModelGenerator() {
        // private constructor prevent instantiation
    }

}
