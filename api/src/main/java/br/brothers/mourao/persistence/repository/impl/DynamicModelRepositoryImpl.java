package br.brothers.mourao.persistence.repository.impl;

import br.brothers.mourao.persistence.repository.DynamicModelRepository;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DynamicModelRepositoryImpl implements DynamicModelRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String createCollection(String name) {
        DBCollection collection = mongoTemplate.createCollection(name);
        return collection.getName();
    }

    @Override
    public void dropCollection(String name) {
        mongoTemplate.dropCollection(name);
    }

    @Override
    public List<Object> findAllRecords(Class clazz, String modelName) {
        return mongoTemplate.findAll(clazz, modelName);
    }

    @Override
    public <T> T findRecord(Class<T> clazz, String modelName, String id) {
        return mongoTemplate.findById(id, clazz, modelName);
    }

    @Override
    public void saveRecord(String modelName, Map<String, Object> attributes) {
        mongoTemplate.save(attributes, modelName);
    }

    @Override
    public void deleteRecord(Object record, String modelName) {
        mongoTemplate.remove(record, modelName);
    }

}
