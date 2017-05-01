package br.brothers.mourao.persistence.entity;

import br.brothers.mourao.persistence.entity.enums.Type;

import java.io.Serializable;

public class Attribute implements Serializable {

    private static final long serialVersionUID = 9188034874654025093L;

    private String name;

    private Type type;

    private String typeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute attribute = (Attribute) o;

        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
        if (type != attribute.type) return false;
        return typeName != null ? typeName.equals(attribute.typeName) : attribute.typeName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                '}';
    }

}
