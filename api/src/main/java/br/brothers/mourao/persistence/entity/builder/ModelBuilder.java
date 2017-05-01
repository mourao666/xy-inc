package br.brothers.mourao.persistence.entity.builder;

import br.brothers.mourao.persistence.entity.Model;

import java.util.List;
import java.util.Map;

public class ModelBuilder {

    private Model model;

    public ModelBuilder() {
        model = new Model();
    }

    public Model build() {
        return model;
    }

    public ModelBuilder id(String id) {
        model.setId(id);
        return this;
    }

    public ModelBuilder name(String name) {
        model.setName(name);
        return this;
    }

    public ModelBuilder collectionName(String collectionName) {
        model.setCollectionName(collectionName);
        return this;
    }

    public ModelBuilder attributes(Map<String, String> attributes) {
        model.setAttributes(attributes);
        return this;
    }

    public ModelBuilder restResources(List<String> restResources) {
        model.setRestResources(restResources);
        return this;
    }

}
