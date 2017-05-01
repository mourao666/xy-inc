package br.brothers.mourao.persistence.repository;

import br.brothers.mourao.persistence.entity.Model;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelRepository extends MongoRepository<Model, String> {

    Model findByName(String name);

}

