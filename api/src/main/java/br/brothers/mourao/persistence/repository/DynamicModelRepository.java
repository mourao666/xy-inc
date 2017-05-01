package br.brothers.mourao.persistence.repository;

public interface DynamicModelRepository {

    String createCollection(String name);

    void saveRecord(Object obj);

}
