package com.example.cbd.apiGateway.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.service.ImagePexelsService;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cbd.externalApi.model.PexelsImage;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class AppService implements AppServiceMethods {


    private final ProductService productService;
    private final ImagePexelsService imageGeneratorService;

    @Autowired
    public AppService(ProductService productService, ImagePexelsService imageGeneratorService) {
        this.productService = productService;
        this.imageGeneratorService = imageGeneratorService;
    }

    /*private final ImageGeneratorService imageGeneratorService;
    @Autowired
    public AppService(StorageService storageService, ImageGeneratorService imageGeneratorService) {
        this.storageService = storageService;
        this.imageGeneratorService = imageGeneratorService;
    }*/



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
    public PexelsImage getImageByPrompt(String prompt) throws IOException, ExternalApiException {
        return this.imageGeneratorService.getImageByPrompt(prompt);
    }

    @Override
    public PexelsImage getRandomImage() throws IOException, ExternalApiException {
        return this.imageGeneratorService.getRandomImage();
    }
}
