package br.brothers.mourao.service;

import br.brothers.mourao.exception.CannotAssignException;
import br.brothers.mourao.exception.CannotGeneratedException;
import br.brothers.mourao.exception.CannotInstantiateException;
import br.brothers.mourao.exception.ModelNotExistsException;
import br.brothers.mourao.exception.TypeNotExistsException;
import br.brothers.mourao.persistence.entity.Model;
import br.brothers.mourao.utils.TypeFactory;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@Service
public class DynamicRestApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicRestApiService.class);

    @Autowired
    private ModelService modelService;

    @Autowired
    private DynamicModelService dynamicModelService;

    public void createRecord(String modelName, Map<String, Object> attributes)
        throws ModelNotExistsException,
            TypeNotExistsException,
            CannotGeneratedException,
            CannotInstantiateException,
            CannotAssignException {

        LOGGER.info("Creating new record in model '{}' by attributes: {}", modelName, attributes);

        Model model = modelService.findByCollectionName(modelName);

        if (model == null) {
            throw new ModelNotExistsException(String.format("Model '%s' not exists on domain", modelName));
        }

        try {

            Class clazz = dynamicModelService.generate(model);
            Object instance = clazz.newInstance();

            for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
                Method method = clazz.getMethod(
                    dynamicModelService.getSetter(attribute.getKey()),
                    TypeFactory.getType(model.getAttributes().get(attribute.getKey())));
                method.invoke(instance, attribute.getValue());
            }

            dynamicModelService.saveRecord(instance);

        } catch (CannotCompileException | NotFoundException | ClassNotFoundException e) {
            String message = String.format("Model '%s' can not be created. %s", model.getName(), e.getMessage());
            LOGGER.error(message, e);
            throw new CannotGeneratedException(message);
        } catch (IllegalAccessException | InstantiationException e) {
            String message = String.format("Model '%s' can not be instantiate. %s", model.getName(), e.getMessage());
            LOGGER.error(message, e);
            throw new CannotInstantiateException(message);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            String message = String.format("Values can not be assign to  model '%s'. %s", model.getName(), e.getMessage());
            LOGGER.error(message, e);
            throw new CannotAssignException(message);
        }
    }

}
