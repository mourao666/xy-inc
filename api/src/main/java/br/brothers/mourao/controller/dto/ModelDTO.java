package br.brothers.mourao.controller.dto;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Attribute;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.entity.builder.AttributeBuilder;
import br.brothers.mourao.persistence.entity.builder.ModelBuilder;
import br.brothers.mourao.persistence.entity.enums.TypeFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelDTO implements Serializable {

    private String id;

    private String name;

    private Map<String, String> attributes;

    public ModelDTO() {

    }

    public ModelDTO(Model model) {
        id = model.getId();
        name = model.getName();
        attributes = new HashMap<>();
        model.getAttributes().forEach(att -> attributes.put(att.getName(), att.getTypeName()));
    }

    public Model unwrap()
        throws TypeNotExistsException {
        List<Attribute> attList = new ArrayList<>();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attList.add(new AttributeBuilder()
                .name(entry.getKey()).type(TypeFactory.getType(entry.getValue())).typeName(entry.getValue()).build());
        }
        return new ModelBuilder().id(id).name(name).attributes(attList).build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

}
