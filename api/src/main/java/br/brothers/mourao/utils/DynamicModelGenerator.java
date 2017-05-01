package br.brothers.mourao.utils;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

import java.io.Serializable;
import java.util.Map;

public class DynamicModelGenerator {

    private static final ClassPool pool = ClassPool.getDefault();

    private static final String CLASS_PATH = "br.brothers.mourao.generated.";
    private static final String ID_ATTRIBUTE_NAME = "id";
    private static final String ID_ATTRIBUTE_TYPE = "String";
    private static final String ID_ANNOTATION_NAME = "org.springframework.data.annotation.Id";
    private static final String DOCUMENT_ANNOTATION_NAME = "org.springframework.data.mongodb.core.mapping.Document";

    public static Class generate(Model model)
        throws NotFoundException,
            CannotCompileException,
            TypeNotExistsException,
            ClassNotFoundException {

        model.getAttributes().put(ID_ATTRIBUTE_NAME, ID_ATTRIBUTE_TYPE);

        String className = CLASS_PATH + model.getName();
        CtClass cc = pool.getOrNull(className);

        if (cc == null) {

            cc = pool.makeClass(CLASS_PATH + model.getName());

            addAnnotation(cc, DOCUMENT_ANNOTATION_NAME);
            cc.addInterface(resolveCtClass(Serializable.class));

            for (Map.Entry<String, String> entry : model.getAttributes().entrySet()) {
                Class fieldClass = TypeFactory.getType(entry.getValue());
                cc.addField(new CtField(resolveCtClass(fieldClass), entry.getKey(), cc));
                cc.addMethod(generateGetter(cc, entry.getKey(), fieldClass));
                cc.addMethod(generateSetter(cc, entry.getKey(), fieldClass));
            }

            addAnnotation(cc, ID_ATTRIBUTE_NAME, ID_ANNOTATION_NAME);

            return cc.toClass();
        } else {
            return Class.forName(className, false, pool.getClassLoader());
        }
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

    private static void addAnnotation(CtClass cc, String annotationName) {

        ClassFile classFile = cc.getClassFile();
        ConstPool cp = classFile.getConstPool();

        AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation(annotationName, cp);
        attr.addAnnotation(annotation);
        classFile.addAttribute(attr);
    }

    private static void addAnnotation(CtClass cc, String field, String annotationName)
            throws NotFoundException {

        ClassFile classFile = cc.getClassFile();
        ConstPool cp = classFile.getConstPool();
        CtField cf  = cc.getField(field);

        AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
        Annotation annotation = new Annotation(annotationName, cp);
        attr.addAnnotation(annotation);
        cf.getFieldInfo().addAttribute(attr);
    }

    public static String generateGetterName(String fieldName) {
        return generateMethodNameWithPrefix("get", fieldName);
    }

    public static String generateSetterName(String fieldName) {
        return generateMethodNameWithPrefix("set", fieldName);
    }

    private static String generateMethodNameWithPrefix(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    private DynamicModelGenerator() {
        // private constructor prevent instantiation
    }

}
