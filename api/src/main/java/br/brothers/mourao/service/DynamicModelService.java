package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.repository.DynamicModelRepository;
import br.brothers.mourao.utils.DynamicModelGenerator;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DynamicModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicModelService.class);

    private static final String CLASS_PATH = "br.brothers.mourao.generated.";

    @Autowired
    private DynamicModelRepository dynamicModelRepository;

    private Map<String, Class<?>> dynamicClasses = new HashMap<>();

    public void generate(Model model)
        throws CannotGeneratedException {
        Map<String, Class<?>> fields = new HashMap<>();
        model.getAttributes().forEach(att -> fields.put(att.getName(), att.getType().getClazz()));
        try {
            String className = CLASS_PATH + model.getName();
            Class clazz = DynamicModelGenerator.generate(className, fields);
            dynamicClasses.put(className, clazz);
        } catch (ClassNotFoundException | NotFoundException | CannotCompileException e) {
            String message = String.format("Model '%s' can not be created. %s", model.getName(), e.getMessage());
            LOGGER.error(message, e);
            throw new CannotGeneratedException(message);
        }
    }

    public String createCollection(String name) {
        LOGGER.info("Creating dynamic collection by name '{}'", name);
        return dynamicModelRepository.createCollection(name);
    }

    public void dropCollection(String name) {
        LOGGER.info("Deleting collection by name '{}'", name);
        dynamicModelRepository.dropCollection(name);
    }

    public List<Object> findAllRecords(Model model)
        throws CannotGeneratedException {
        return dynamicModelRepository.findAllRecords(getDynamicClass(model), model.getName());
    }

    public Object findRecord(Model model, String id)
        throws CannotGeneratedException {
        return dynamicModelRepository.findRecord(getDynamicClass(model), model.getName(), id);
    }

    public void saveRecord(Model model, Map<String, Object> attributes) {
        dynamicModelRepository.saveRecord(model.getName(), attributes);
    }

    public void deleteRecord(Object record, String modelName) {
        dynamicModelRepository.deleteRecord(record, modelName);
    }

    private Class<?> getDynamicClass(Model model)
        throws CannotGeneratedException {
        String className = CLASS_PATH + model.getName();
        if (!dynamicClasses.containsKey(className)) {
            generate(model);
        }
        return dynamicClasses.get(className);
    }

}
