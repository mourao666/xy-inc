package br.brothers.mourao.utils;

import br.brothers.mourao.exception.TypeNotExistsException;

import java.util.Date;

public class TypeFactory {

    public static Class getType(String type)
        throws TypeNotExistsException {

        switch (type.toLowerCase()) {
            case "bool":
                return Boolean.class;
            case "boolean":
                return Boolean.class;
            case "char":
                return Character.class;
            case "character":
                return Character.class;
            case "date":
                return Date.class;
            case "double":
                return Double.class;
            case "float":
                return Float.class;
            case "int":
                return Integer.class;
            case "integer":
                return Integer.class;
            case "long":
                return Long.class;
            case "string":
                return String.class;
            default:
                throw new TypeNotExistsException(String.format("The type '%s' not exists", type));
        }
    }

    private TypeFactory() {
        // private constructor prevent instantiation
    }

}
