package com.example.cbd.apiGateway.service;

import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.StorageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
public class AppService implements AppServiceMethods {


    private final StorageService storageService;
    //todo add different services + add them to constructor



    @Autowired
    public AppService(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    public Product getProductById(@NotNull UUID id) {
        return this.storageService.getProductById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return this.storageService.getAllProducts();
    }

    @Override
    public void createProduct(@NotNull Product product) {
        this.storageService.createProduct(product);
    }

    @Override
    public void deleteProduct(@NotNull UUID id) {
        this.storageService.deleteProduct(id);
    }

    @Override
    public void deleteAllProducts() {
        this.storageService.deleteAllProducts();
    }

    @Override
    public void updateProduct(@NotNull UUID id) {
        this.storageService.updateProduct(id);
    }







}
