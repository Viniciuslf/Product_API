package com.vinicius.productscatalog.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import com.vinicius.productscatalog.Product;
import com.vinicius.productscatalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.productscatalog.dto.ProductUpdateDTO;
import com.vinicius.productscatalog.exception.ProductNotFound;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(String id) {
        final Optional<Product> product = repository.findById(id);

        if (product.isPresent()) {
            return product.get();
        }
        
        throw new ProductNotFound("Product not found with id: " + id);
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(OffsetDateTime.now().toString());
        product.setUpdatedAt(OffsetDateTime.now().toString());
        return repository.save(product);
    }

    public Product updateProduct(String id, ProductUpdateDTO updateDTO) {
        if (!updateDTO.hasAtLeastOneField()) {
            throw new IllegalArgumentException("At least one field must be provided.");
        }

        Product product = getProductById(id);

        if (updateDTO.getName() != null) {
            product.setName(updateDTO.getName());
        }
        if (updateDTO.getDescription() != null) {
            product.setDescription(updateDTO.getDescription());
        }
        if (updateDTO.getPrice() != null) {
            product.setPrice(updateDTO.getPrice());
        }
        if (updateDTO.getStock() != null) {
            product.setStock(updateDTO.getStock());
        }

        if (!product.isValid()) {
            throw new IllegalArgumentException("Updated product is not valid.");
        }

        product.setUpdatedAt(OffsetDateTime.now().toString());
        return repository.save(product);
    }

    public void deleteProduct(String id) {
        Product product = getProductById(id);
        repository.delete(product);
    }
}