package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.model.Product;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
public class ValidationService {



    public void validateProduct(Product product) {
        if(hasNullValue(product)) throw new NullPointerException("A given value is null.");
        if(imageUrlIsValid(product)) throw new IllegalArgumentException("The given Image has no correct URL format.");

    }

    private boolean hasNullValue(Product product) {
        return product.getName() == null || product.getDescription() == null || product.getPrice() == null;
    }

     private boolean imageUrlIsValid(Product product) {
        try {
            new URI(product.getImage());
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    private boolean nameIsValid(Product product) {
        return true;
    }







}
