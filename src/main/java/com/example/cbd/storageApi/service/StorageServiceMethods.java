package com.example.cbd.storageApi.service;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface StorageServiceMethods<T> {

    T getProductById(@NotNull UUID id);

    Iterable<T> getAllProducts();

    void createProduct(@NotNull final T product);

    void deleteProduct(@NotNull final UUID id);

    void deleteAllProducts();

    void updateProduct(@NotNull final UUID id);

}