package org.example.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MongoRepositoryImpl {

    @Autowired
    private MyMongoRepository myMongoRepository;

    public List<Products> findAll() {
        return myMongoRepository.findAll();
    }

    public Optional<Products> findById(String id) {
        return myMongoRepository.findById(id);
    }

    public Products save(Products product) {
        return myMongoRepository.save(product);
    }

    public void deleteById(String id) {
        myMongoRepository.deleteById(id);
    }
}