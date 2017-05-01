package br.brothers.mourao.persistence.entity.enums;

import br.brothers.mourao.exception.TypeNotExistsException;

public class TypeFactory {

    public static Type getType(String type)
        throws TypeNotExistsException {

        switch (type.toLowerCase()) {
            case "bool":
                return Type.BOOLEAN;
            case "boolean":
                return Type.BOOLEAN;
            case "char":
                return Type.CHARACTER;
            case "character":
                return Type.CHARACTER;
            case "date":
                return Type.DATE;
            case "double":
                return Type.DOUBLE;
            case "float":
                return Type.FLOAT;
            case "int":
                return Type.INTEGER;
            case "integer":
                return Type.INTEGER;
            case "long":
                return Type.LONG;
            case "string":
                return Type.STRING;
            default:
                throw new TypeNotExistsException(String.format("The type '%s' not exists", type));
        }
    }

    private TypeFactory() {
        // private constructor prevent instantiation
    }

}
