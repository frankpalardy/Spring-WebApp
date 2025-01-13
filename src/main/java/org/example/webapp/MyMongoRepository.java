package org.example.webapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyMongoRepository extends MongoRepository<Products, String> {
}