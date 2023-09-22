package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {



    public void validateProduct(Product product) {
        if(hasNullValue(product)) throw new NullPointerException("A given value is null!");

    }

    private boolean hasNullValue(Product product) {
        return product.getName() == null || product.getDescription() == null || product.getPrice() == null;
    }







}
