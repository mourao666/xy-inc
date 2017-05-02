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
        attributes.put("foo", TypeFactory.getType("int").getClazz());
        attributes.put("bar", TypeFactory.getType("String").getClazz());
    }

    @Test
    public void generateSuccessTest()
        throws CannotCompileException,
            NotFoundException,
            ClassNotFoundException {
        Class clazz = DynamicModelGenerator.generate("Foo", attributes);
        Assert.assertTrue("Foo".equals(clazz.getName()));
        Assert.assertEquals(3, clazz.getDeclaredFields().length);
    }

}
