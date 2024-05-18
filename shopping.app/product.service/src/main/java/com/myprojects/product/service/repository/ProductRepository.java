package com.myprojects.product.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myprojects.product.service.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
