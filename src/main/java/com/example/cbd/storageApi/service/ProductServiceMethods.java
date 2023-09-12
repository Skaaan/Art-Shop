package com.example.cbd.storageApi.service;

import org.jetbrains.annotations.NotNull;
import com.example.cbd.externalApi.model.Test;

import java.math.BigDecimal;


public interface ProductServiceMethods<T> {

    T getProductById(@NotNull Long id);

    Iterable<T> getAllProducts();

    void createProduct(@NotNull final T product);

    void deleteProduct(@NotNull final Long id);

    void deleteAllProducts();

    void updateProduct(@NotNull final Long id, String name, String description, BigDecimal price, Test image);

}