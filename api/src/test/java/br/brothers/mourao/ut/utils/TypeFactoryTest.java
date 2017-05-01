package br.brothers.mourao.ut.utils;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.ut.UnitTest;
import br.brothers.mourao.utils.TypeFactory;
import org.junit.Assert;
import org.junit.Test;

public class TypeFactoryTest extends UnitTest {

    @Test
    public void boolTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("bool").getName()));
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("Bool").getName()));
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("BOOL").getName()));
    }

    @Test
    public void booleanTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("boolean").getName()));
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("Boolean").getName()));
        Assert.assertTrue("java.lang.Boolean".equals(TypeFactory.getType("BOOLEAN").getName()));
    }

    @Test
    public void charTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("char").getName()));
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("Char").getName()));
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("CHAR").getName()));
    }

    @Test
    public void characterTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("character").getName()));
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("Character").getName()));
        Assert.assertTrue("java.lang.Character".equals(TypeFactory.getType("CHARACTER").getName()));
    }

    @Test
    public void dateTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.util.Date".equals(TypeFactory.getType("date").getName()));
        Assert.assertTrue("java.util.Date".equals(TypeFactory.getType("Date").getName()));
        Assert.assertTrue("java.util.Date".equals(TypeFactory.getType("DATE").getName()));
    }

    @Test
    public void doubleTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Double".equals(TypeFactory.getType("double").getName()));
        Assert.assertTrue("java.lang.Double".equals(TypeFactory.getType("Double").getName()));
        Assert.assertTrue("java.lang.Double".equals(TypeFactory.getType("DOUBLE").getName()));
    }

    @Test
    public void floatTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Float".equals(TypeFactory.getType("float").getName()));
        Assert.assertTrue("java.lang.Float".equals(TypeFactory.getType("Float").getName()));
        Assert.assertTrue("java.lang.Float".equals(TypeFactory.getType("FLOAT").getName()));
    }

    @Test
    public void intTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("int").getName()));
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("Int").getName()));
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("INT").getName()));
    }

    @Test
    public void integerTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("integer").getName()));
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("Integer").getName()));
        Assert.assertTrue("java.lang.Integer".equals(TypeFactory.getType("INTEGER").getName()));
    }

    @Test
    public void longTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.Long".equals(TypeFactory.getType("long").getName()));
        Assert.assertTrue("java.lang.Long".equals(TypeFactory.getType("Long").getName()));
        Assert.assertTrue("java.lang.Long".equals(TypeFactory.getType("LONG").getName()));
    }

    @Test
    public void stringTest()
        throws TypeNotExistsException {
        Assert.assertTrue("java.lang.String".equals(TypeFactory.getType("string").getName()));
        Assert.assertTrue("java.lang.String".equals(TypeFactory.getType("String").getName()));
        Assert.assertTrue("java.lang.String".equals(TypeFactory.getType("STRING").getName()));
    }

    @Test(expected = TypeNotExistsException.class)
    public void nonexistentTypeTest()
        throws TypeNotExistsException {
        TypeFactory.getType("nonexistent");
    }

}
