package br.brothers.mourao.persistence.entity.enums;

import java.util.Date;

public enum Type {

    BOOL(Boolean.class),
    BOOLEAN(Boolean.class),
    CHAR(Character.class),
    CHARACTER(Character.class),
    DATE(Date.class),
    DOUBLE(Double.class),
    FLOAT(Float.class),
    INT(Integer.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    STRING(String.class);

    private Class clazz;

    Type(Class clazz) {
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

}
