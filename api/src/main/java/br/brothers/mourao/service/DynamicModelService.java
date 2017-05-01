package br.brothers.mourao.service;

import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.persistence.repository.DynamicModelRepository;
import br.brothers.mourao.utils.DynamicModelGenerator;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicModelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicModelService.class);

    @Autowired
    private DynamicModelRepository dynamicModelRepository;

    public Class generate(Model model)
        throws CannotCompileException,
            NotFoundException,
            TypeNotExistsException,
            ClassNotFoundException {
        LOGGER.info("Generating dynamic class by model {}", model);
        return DynamicModelGenerator.generate(model);
    }

    public String createCollection(String name) {
        LOGGER.info("Creating dynamic collection by name '{}'", name);
        return dynamicModelRepository.createCollection(name);
    }

    public void saveRecord(Object obj) {
        LOGGER.info("Saving dynamic '{}' record", obj.getClass().getName());
        dynamicModelRepository.saveRecord(obj);
    }

    public String getGetter(String field) {
        return DynamicModelGenerator.generateGetterName(field);
    }

    public String getSetter(String field) {
        return DynamicModelGenerator.generateSetterName(field);
    }

}
