package br.brothers.mourao.persistence.entity.builder;

import br.brothers.mourao.persistence.entity.Model;

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
}
