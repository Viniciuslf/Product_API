package com.vinicius.productscatalog.repository;

import com.vinicius.productscatalog.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}