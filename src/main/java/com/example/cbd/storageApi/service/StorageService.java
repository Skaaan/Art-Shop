package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class StorageService implements StorageServiceMethods<Product> {



    private final ProductRepository productRepository;



    @Autowired
    public StorageService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(@NotNull UUID id) {
        //todo, sus?
        return productRepository.getReferenceById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return null;
    }

    @Override
    public void createProduct(@NotNull Product product) {

    }

    @Override
    public void deleteProduct(@NotNull UUID id) {

    }

    @Override
    public void deleteAllProducts() {

    }

    @Override
    public void updateProduct(@NotNull UUID id) {

    }
}
