package br.brothers.mourao.persistence.entity.builder;

import br.brothers.mourao.persistence.entity.Attribute;
import br.brothers.mourao.persistence.entity.enums.Type;

public class AttributeBuilder {

    private Attribute attribute;

    public AttributeBuilder() {
        attribute = new Attribute();
    }

    public Attribute build() {
        return attribute;
    }

    public AttributeBuilder name(String name) {
        attribute.setName(name);
        return this;
    }

    public AttributeBuilder type(Type type) {
        attribute.setType(type);
        return this;
    }

    public AttributeBuilder typeName(String typeName) {
        attribute.setTypeName(typeName);
        return this;
    }

}
