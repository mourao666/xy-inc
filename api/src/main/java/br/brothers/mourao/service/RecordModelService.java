package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.InvalidTypeAttribute;
import br.brothers.mourao.persistence.entity.Attribute;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.exception.ModelNotExistsException;
import br.brothers.mourao.persistence.entity.enums.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Model model = getModelOrThrow(modelName);
        LOGGER.info("Searching all records for model {}", model);
        return dynamicModelService.findAllRecords(model);
    }

    public Object findById(String modelName, String id)
        throws CannotGeneratedException,
            ModelNotExistsException {
        Model model = getModelOrThrow(modelName);
        LOGGER.info("Searching records for model {} with id '{}'", model, id);
        return dynamicModelService.findRecord(model, id);
    }

    public Map<String, Object> create(String modelName, Map<String, Object> attributes)
        throws ModelNotExistsException,
            InvalidTypeAttribute {
        attributes.put("_id", UUID.randomUUID().toString());
        return save(modelName, attributes);
    }

    public Map<String, Object> update(String modelName, String id, Map<String, Object> attributes)
        throws ModelNotExistsException,
            InvalidTypeAttribute {
        attributes.put("_id", id);
        return save(modelName, attributes);
    }

    private Map<String, Object> save(String modelName, Map<String, Object> attributes)
        throws ModelNotExistsException,
            InvalidTypeAttribute {
        Model model = getModelOrThrow(modelName);
        validateAttributes(model, attributes);
        LOGGER.info("Saving new record for model {} with attributes {}", model, attributes);
        dynamicModelService.saveRecord(model, attributes);
        return attributes;
    }

    public Object deleteById(String modelName, String id)
        throws CannotGeneratedException,
            ModelNotExistsException {
        Model model = getModelOrThrow(modelName);
        Object record = findById(model.getName(), id);
        LOGGER.info("Deleting record id '{}' for model {}", id, model);
        dynamicModelService.deleteRecord(record, model.getName());
        return record;
    }

    private Model getModelOrThrow(String modelName)
        throws ModelNotExistsException {
        Model model = modelService.findByName(modelName);
        if (model == null) {
            throw new ModelNotExistsException(String.format("Model '%s' not exists on domain", modelName));
        }
        return model;
    }

    private void validateAttributes(Model model, Map<String, Object> attributes)
        throws InvalidTypeAttribute {
        StringBuilder sb = new StringBuilder();
        for (Attribute attribute : model.getAttributes()) {
            if (attribute.getType().equals(Type.DATE)) {
                try {
                    new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(attributes.get(attribute.getName()).toString());
                } catch (ParseException e) {
                    sb.append(String.format(" %s.", attribute.getName()));
                }
            } else {
                Class clazz1 = attribute.getType().getClazz();
                Class clazz2 = attributes.get(attribute.getName()).getClass();
                if (!clazz1.isAssignableFrom(clazz2)) {
                    sb.append(String.format(" %s.", attribute.getName()));
                }
            }
        }
        if (!sb.toString().isEmpty()) {
            throw new InvalidTypeAttribute(String.format("Invalid attribute type(s): %s", sb.toString()));
        }
    }

}
