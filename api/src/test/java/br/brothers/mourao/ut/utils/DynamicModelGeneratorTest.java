package br.brothers.mourao.ut.utils;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.enums.TypeFactory;
import br.brothers.mourao.utils.DynamicModelGenerator;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import java.util.HashMap;
import java.util.Map;

@TestPropertySource(properties = { "spring.data.mongodb.port=27668" })
public class DynamicModelGeneratorTest extends br.brothers.mourao.Test {

    private static Map<String, Class<?>> attributes;

    @BeforeClass
    public static void init()
        throws TypeNotExistsException {
        attributes = new HashMap<>();
        attributes.put("foo01", TypeFactory.getType("bool").getClazz());
        attributes.put("foo02", TypeFactory.getType("Boolean").getClazz());
        attributes.put("foo03", TypeFactory.getType("char").getClazz());
        attributes.put("foo04", TypeFactory.getType("Character").getClazz());
        attributes.put("foo05", TypeFactory.getType("Date").getClazz());
        attributes.put("foo06", TypeFactory.getType("Double").getClazz());
        attributes.put("foo07", TypeFactory.getType("Float").getClazz());
        attributes.put("foo08", TypeFactory.getType("int").getClazz());
        attributes.put("foo09", TypeFactory.getType("Integer").getClazz());
        attributes.put("foo10", TypeFactory.getType("Long").getClazz());
        attributes.put("foo11", TypeFactory.getType("String").getClazz());
    }

    @Test
    public void generateSuccessTest()
        throws CannotCompileException,
            NotFoundException,
            ClassNotFoundException {
        Class clazz = DynamicModelGenerator.generate("Foo", attributes);
        Assert.assertTrue("Foo".equals(clazz.getName()));
        Assert.assertEquals(12, clazz.getDeclaredFields().length);
    }

}
