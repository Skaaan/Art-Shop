package com.example.cbd.storageApi.service;

import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import org.jetbrains.annotations.NotNull;


public interface ProductServiceMethods<T> {

    T getProductById(@NotNull Long id);

    Iterable<T> getAllProducts();

    void createProduct(@NotNull final T product);

    void deleteProduct(@NotNull final Long id) throws ProductNotPresentException;

    void deleteAllProducts();

    void updateProduct(@NotNull final T product) throws ProductNotPresentException;

}