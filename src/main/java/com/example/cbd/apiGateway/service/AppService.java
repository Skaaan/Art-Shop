package com.example.cbd.apiGateway.service;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.externalApi.producer.ImagePexelsProducer;
import com.example.cbd.externalApi.service.ImagePexelsServiceMethods;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.producer.ProductProducer;
import com.example.cbd.storageApi.service.ProductServiceMethods;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

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

}
