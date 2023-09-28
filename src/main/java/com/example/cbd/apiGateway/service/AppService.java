package com.example.cbd.apiGateway.service;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.producer.ImagePexelsProducer;
import com.example.cbd.externalApi.service.ImagePexelsService;
import com.example.cbd.externalApi.service.ImagePexelsServiceMethods;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.producer.ProductProducer;
import com.example.cbd.storageApi.service.ProductService;
import com.example.cbd.storageApi.service.ProductServiceMethods;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class AppService implements ProductServiceMethods<Product>, ImagePexelsServiceMethods {


    private final ProductProducer productProducer;
    private final ImagePexelsProducer imagePexelsProducer;


    @Override
    public Product getProductById(@NotNull Long id) throws MessagingErrorException {
        return productProducer.getProductById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() throws MessagingErrorException {
        return productProducer.getAllProducts();
    }

    @Override
    public void createProduct(@NotNull Product product) throws MessagingErrorException {
        productProducer.createProduct(product);
    }

    @Override
    public void deleteProduct(@NotNull Long id) throws MessagingErrorException {
        productProducer.deleteProduct(id);

    }

    @Override
    public void deleteAllProducts() throws MessagingErrorException {
        productProducer.deleteAllProducts();

    }

    @Override
    public void updateProduct(@NotNull Product product) throws MessagingErrorException {
        productProducer.updateProduct(product);

    }
    @Override
    public String getImageByPrompt(String prompt) throws MessagingErrorException {
        return imagePexelsProducer.getImageByPrompt(prompt);
    }

    @Override
    public String getRandomImage() throws MessagingErrorException {
        return imagePexelsProducer.getRandomImage();
    }

    /*
    private final ProductService productService;
    private final ImagePexelsService imageGeneratorService;

    @Autowired
    public AppService(ProductService productService, ImagePexelsService imageGeneratorService) {
        this.productService = productService;
        this.imageGeneratorService = imageGeneratorService;
    }

    @Override
    public Product getProductById(@NotNull Long id) {
        return this.productService.getProductById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @Override
    public void createProduct(@NotNull Product product) {
        this.productService.createProduct(product);
    }

    @Override
    public void deleteProduct(@NotNull Long id) throws ProductNotPresentException {
        this.productService.deleteProduct(id);
    }

    @Override
    public void deleteAllProducts() {
        this.productService.deleteAllProducts();
    }

    @Override
    public void updateProduct(@NotNull Product product) throws ProductNotPresentException {
        this.productService.updateProduct(product);
    }


    @Override
    public String getImageByPrompt(String prompt) throws ExternalApiException {
        return this.imageGeneratorService.getImageByPrompt(prompt);
    }

    @Override
    public String getRandomImage() throws ExternalApiException {
        return this.imageGeneratorService.getRandomImage();
    }*/
}
