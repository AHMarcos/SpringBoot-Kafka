package com.josequispe.cropy.repository;

import com.josequispe.cropy.model.Cajero;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface CajeroRepository extends MongoRepository<Cajero, String> {

}