package br.brothers.mourao.persistence.repository;

import java.util.List;
import java.util.Map;

public interface DynamicModelRepository {

    String createCollection(String name);

    void dropCollection(String name);

    List<Object> findAllRecords(Class clazz, String modelName);

    <T> T  findRecord(Class<T> clazz, String modelName, String id);

    void saveRecord(String modelName, Map<String, Object> attributes);

    void deleteRecord(Object record, String modelName);

}
