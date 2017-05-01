package br.brothers.mourao.ut.utils;

import br.brothers.mourao.ut.UnitTest;

public abstract class DynamicModelGeneratorTest extends UnitTest {

//    private static Model model;

//    @BeforeClass
//    public static void init() {
//        Map<String, String> attributes = new HashMap<>();
//        attributes.put("foo", "Integer");
//        attributes.put("bar", "String");
//        model = new ModelBuilder().name("Test").attributes(attributes).build();
//    }

//    @Test
//    public void generateSuccessTest()
//        throws CannotCompileException,
//            NotFoundException,
//            TypeNotExistsException,
//            NoSuchFieldException,
//            NoSuchMethodException,
//            ClassNotFoundException {
//        Class clazz = DynamicModelGenerator.generate(model);
//        Assert.assertTrue("br.brothers.mourao.generated.Test".equals(clazz.getName()));
//        Assert.assertEquals(3, clazz.getDeclaredFields().length);
//        clazz.getMethod("getId");
//        clazz.getMethod("getFoo");
//        clazz.getMethod("getBar");
//        clazz.getMethod("setId", String.class);
//        clazz.getMethod("setFoo", Integer.class);
//        clazz.getMethod("setBar", String.class);
//        Assert.assertEquals(1, clazz.getDeclaredField("id").getAnnotations().length);
//        Assert.assertEquals(1, clazz.getAnnotations().length);
//        //System.out.println(clazz.newInstance());
//    }

//    @Test(expected = TypeNotExistsException.class)
//    public void generateTypeNotExistsExceptionTest()
//        throws TypeNotExistsException,
//            CannotCompileException,
//            NotFoundException,
//            ClassNotFoundException {
//        model.setName("TestTypeNotExists");
//        model.getAttributes().put("notExist", "nonexistent");
//        DynamicModelGenerator.generate(model);
//        model.setName("Test");
//        model.getAttributes().remove("notExist");
//    }

    // TODO - Test NotFoundException
    // TODO - Test CannotCompileException
    // TODO - Test IllegalAccessException
    // TODO - Test InstantiationException

}
