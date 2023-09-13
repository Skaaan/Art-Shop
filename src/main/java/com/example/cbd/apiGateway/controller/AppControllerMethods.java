package com.example.cbd.apiGateway.controller;

import com.example.cbd.externalApi.model.PexelsImage;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;


public interface AppControllerMethods {

    ResponseEntity<?> getProductById(@NotNull final Long id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> createProduct(@NotNull final Product product);

    ResponseEntity<?> deleteProductById(@NotNull final Long id) throws ProductNotPresentException;

    ResponseEntity<?> deleteAllProducts();

    ResponseEntity<?> updateProduct(@NotNull final Product product) throws ProductNotPresentException;

    //getProductsFromUser(@NotNull final String userName);

    //external API
    ResponseEntity<PexelsImage> getAiImage(@NotNull String prompt);

    ResponseEntity<PexelsImage> getRandomAiImage();

}
