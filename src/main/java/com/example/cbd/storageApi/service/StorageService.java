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


    //todo ID verändern?
    @Override
    public Product getProductById(@NotNull Long id) {
        //todo, sus?
        return productRepository.getProductById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(@NotNull Product product) {

    }

    @Override
    public void deleteProduct(@NotNull Long id) {

    }

    @Override
    public void deleteAllProducts() {

    }

    @Override
    public void updateProduct(@NotNull Long id) {

    }
}
