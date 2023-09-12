package com.example.cbd.apiGateway.controller;

import com.example.cbd.externalApi.model.PhotoResult;
import com.example.cbd.storageApi.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import com.example.cbd.externalApi.model.Test;
import java.math.BigDecimal;
import java.util.UUID;



public interface AppControllerMethods {

    ResponseEntity<?> getProductById(@NotNull final Long id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> createProduct(@NotNull final Product product);

    ResponseEntity<?> deleteProductById(@NotNull final Long id);

    ResponseEntity<?> deleteAllProducts();

    ResponseEntity<?> updateProduct(@NotNull final Long id, String name, String description, BigDecimal price, Test test);

    //getProductsFromUser(@NotNull final String userName);

    //external API
    ResponseEntity<Test> getAiImage(@NotNull String prompt);

    ResponseEntity<Test> getRandomAiImage();

}
