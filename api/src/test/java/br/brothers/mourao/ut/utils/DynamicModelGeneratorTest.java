package br.brothers.mourao.ut.utils;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.entity.builder.ModelBuilder;
import br.brothers.mourao.ut.UnitTest;
import br.brothers.mourao.utils.DynamicModelGenerator;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DynamicModelGeneratorTest extends UnitTest {

    private static Model model;

    @BeforeClass
    public static void init() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("foo", "Integer");
        attributes.put("bar", "String");
        model = new ModelBuilder().name("Test").attributes(attributes).build();
    }

    @Test
    public void generateSuccessTest()
        throws CannotCompileException,
            NotFoundException,
            TypeNotExistsException,
            NoSuchFieldException,
            NoSuchMethodException {
        Class clazz = DynamicModelGenerator.generate(model);
        Assert.assertTrue("Test".equals(clazz.getName()));
        Assert.assertEquals(3, clazz.getDeclaredFields().length);
        clazz.getMethod("getId");
        clazz.getMethod("getFoo");
        clazz.getMethod("getBar");
        clazz.getMethod("setId", String.class);
        clazz.getMethod("setFoo", Integer.class);
        clazz.getMethod("setBar", String.class);
        Assert.assertEquals(1, clazz.getDeclaredField("id").getAnnotations().length);
        Assert.assertEquals(1, clazz.getAnnotations().length);
        //System.out.println(clazz.newInstance());
    }

    // TODO - Test NotFoundException
    // TODO - Test CannotCompileException
    // TODO - Test TypeNotExistsException
    // TODO - Test IllegalAccessException
    // TODO - Test InstantiationException

}
