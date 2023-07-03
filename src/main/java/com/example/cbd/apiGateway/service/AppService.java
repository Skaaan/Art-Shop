package com.example.cbd.apiGateway.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.service.ImageGeneratorService;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.StorageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AppService implements AppServiceMethods {


    private final StorageService storageService;
    private final ImageGeneratorService imageGeneratorService;


    @Autowired
    public AppService(StorageService storageService, ImageGeneratorService imageGeneratorService) {
        this.storageService = storageService;
        this.imageGeneratorService = imageGeneratorService;
    }

    @Override
    public Product getProductById(@NotNull Long id) {
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
    public void deleteProduct(@NotNull Long id) {
        this.storageService.deleteProduct(id);
    }

    @Override
    public void deleteAllProducts() {
        this.storageService.deleteAllProducts();
    }

    @Override
    public void updateProduct(@NotNull Long id, String name) {
        this.storageService.updateProduct(id, name);
    }


    @Override
    public String getImageByPrompt(String prompt) throws IOException, ExternalApiException {
        return this.imageGeneratorService.getImageByPrompt(prompt);
    }

    @Override
    public String getRandomImage() throws IOException, ExternalApiException {
        return this.imageGeneratorService.getRandomImage();
    }
}
