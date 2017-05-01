package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.exception.ModelNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RecordModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordModelService.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private DynamicModelService dynamicModelService;

    public List<Object> findAll(String modelName)
        throws CannotGeneratedException,
            ModelNotExistsException {
        Model model = modelService.findByName(modelName);
        if (model == null) {
            throw new ModelNotExistsException(String.format("Model '%s' not exists on domain", model.getName()));
        }
        LOGGER.info("Searching all records for model {}", model);
        return dynamicModelService.findAllRecords(model);
    }

    public Object findById(String modelName, String id)
        throws CannotGeneratedException,
            ModelNotExistsException {
        Model model = modelService.findByName(modelName);
        if (model == null) {
            throw new ModelNotExistsException(String.format("Model '%s' not exists on domain", model.getName()));
        }
        LOGGER.info("Searching records for model {} with id '{}'", model, id);
        return dynamicModelService.findRecord(model, id);
    }

    public Map<String, Object> create(String modelName, Map<String, Object> attributes) {
        validateAttributes(attributes);
        attributes.put("_id", UUID.randomUUID().toString());
        Model model = modelService.findByName(modelName);
        LOGGER.info("Creating new record for model {} with attributes {}", model, attributes);
        dynamicModelService.saveRecord(model, attributes);
        return attributes;
    }

    public Map<String, Object> update(String modelName, String id, Map<String, Object> attributes) {
        validateAttributes(attributes);
        attributes.put("_id", id);
        Model model = modelService.findByName(modelName);
        LOGGER.info("Update record id '{}' for model {} with attributes {}", id, model, attributes);
        dynamicModelService.saveRecord(model, attributes);
        return attributes;
    }

    public Object deleteById(String modelName, String id)
        throws CannotGeneratedException,
            ModelNotExistsException {
        Model model = modelService.findByName(modelName);
        Object record = findById(model.getName(), id);
        LOGGER.info("Deleting record id '{}' for model {}", id, model);
        dynamicModelService.deleteRecord(record, model.getName());
        return record;
    }

    private void validateAttributes(Map<String, Object> attributes) {

    }

}
