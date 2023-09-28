package com.example.cbd.storageApi.service;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import org.jetbrains.annotations.NotNull;


public interface ProductServiceMethods<T> {

    T getProductById(@NotNull Long id) throws MessagingErrorException;

    Iterable<T> getAllProducts() throws MessagingErrorException;

    void createProduct(@NotNull final T product) throws MessagingErrorException;

    void deleteProduct(@NotNull final Long id) throws ProductNotPresentException, MessagingErrorException;

    void deleteAllProducts() throws MessagingErrorException;

    void updateProduct(@NotNull final T product) throws ProductNotPresentException, MessagingErrorException;

}