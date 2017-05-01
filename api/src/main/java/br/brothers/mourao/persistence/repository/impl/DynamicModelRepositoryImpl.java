package br.brothers.mourao.persistence.repository.impl;

import br.brothers.mourao.persistence.repository.DynamicModelRepository;
import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

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
    public void saveRecord(Object obj) {
        mongoTemplate.insert(obj);
    }

}
