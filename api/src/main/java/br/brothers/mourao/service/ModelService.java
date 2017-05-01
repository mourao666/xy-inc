package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.ModelAlreadyExistsException;
import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.repository.ModelRepository;
import br.brothers.mourao.utils.Constants;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelService.class);

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private DynamicModelService dynamicModelService;

    public Model create(Model model)
        throws ModelAlreadyExistsException,
            TypeNotExistsException,
            CannotGeneratedException {

        model.setCollectionName(model.getName().toLowerCase());

        if (findByCollectionName(model.getCollectionName()) != null) {
            String message = String.format("Model '%s' already exists on domain", model.getName());
            throw new ModelAlreadyExistsException(message);
        }

        LOGGER.info("Creating new model: {}", model);

        try {
            dynamicModelService.generate(model);
        } catch (CannotCompileException | NotFoundException e) {
            String message = String.format("Model '%s' can not be created. %s", model.getName(), e.getMessage());
            LOGGER.error(message, e);
            throw new CannotGeneratedException(message);
        }

        String modelName = dynamicModelService.createCollection(model.getCollectionName());
        model.setRestResources(Arrays.asList(
            String.format("GET %s/%s : Lists all elements of model '%s'", Constants.DYNAMIC_URL, modelName, model.getName()),
            String.format("GET %s/%s/{id} : Search for a '%s' model record by id", Constants.DYNAMIC_URL, modelName, model.getName()),
            String.format("POST %s/%s : Creates a new '%s' model record", Constants.DYNAMIC_URL, modelName, model.getName()),
            String.format("PUT %s/%s/{id} : Edit a '%s' model record", Constants.DYNAMIC_URL, modelName, model.getName()),
            String.format("DELETE %s/%s/{id} : Delete a template '%s' record", Constants.DYNAMIC_URL, modelName, model.getName())
        ));

        return modelRepository.save(model);
    }

    public Model findByCollectionName(String collectionName) {
        LOGGER.info("Searching model by collection name '{}'", collectionName);
        return modelRepository.findByCollectionName(collectionName);
    }

}
