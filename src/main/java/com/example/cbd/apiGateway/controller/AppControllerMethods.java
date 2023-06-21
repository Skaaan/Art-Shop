package com.example.cbd.apiGateway.controller;

import com.example.cbd.storageApi.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.UUID;



public interface AppControllerMethods {

    ResponseEntity<?> getProductById(@NotNull final UUID id);

    ResponseEntity<?> getAllProducts();

    ResponseEntity<?> createProduct(@NotNull final Product product);

    ResponseEntity<?> deleteProductById(@NotNull final UUID id);

    ResponseEntity<?> deleteAllProducts();

    ResponseEntity<?> updateProduct(@NotNull final UUID id);

    //getProductsFromUser(@NotNull final String userName);

    //external API
    ResponseEntity<?> getAiImage(@NotNull String prompt);

    ResponseEntity<?> getRandomAiImage();


}
