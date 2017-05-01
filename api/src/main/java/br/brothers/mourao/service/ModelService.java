package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.ModelAlreadyExistsException;
import br.brothers.mourao.exception.ModelNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.repository.ModelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private DynamicModelService dynamicModelService;

    public List<Model> findAll() {
        LOGGER.info("Searching all models");
        return modelRepository.findAll();
    }

    public Model findById(String id)
        throws ModelNotExistsException {
        LOGGER.info("Searching model by id '{}'", id);
        Model model = modelRepository.findOne(id);
        if (model == null) {
            throw new ModelNotExistsException(String.format("Model with id '%s' not exists on domain", id));
        }
        return model;
    }

    public Model findByName(String name) {
        LOGGER.info("Searching model by name '{}'", name);
        return modelRepository.findByName(name);
    }

    public Model create(Model model)
        throws ModelAlreadyExistsException,
            CannotGeneratedException {

        if (findByName(model.getName()) != null) {
            throw new ModelAlreadyExistsException(String.format("Model '%s' already exists on domain", model.getName()));
        }

        LOGGER.info("Creating new model: {}", model);

        dynamicModelService.generate(model);
        dynamicModelService.createCollection(model.getName());
        return modelRepository.save(model);
    }

    public Model delete(String id)
        throws ModelNotExistsException {
        LOGGER.info("Deleting model by id '{}'", id);
        Model model = findById(id);
        dynamicModelService.dropCollection(model.getName());
        modelRepository.delete(id);
        return model;
    }

}
