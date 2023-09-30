package com.example.cbd.storageApi;


import com.example.cbd.storageApi.model.Product;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ValidationService {

    private static final int MAX_NAME_LENGTH = 30;
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_DESC_LENGTH = 100;

    public void validateProduct(Product product) throws IllegalArgumentException, NullPointerException {
        if (hasNullValue(product))
            throw new NullPointerException("A given value is null.");
        if (!imageUrlIsValid(product))
            throw new IllegalArgumentException("The given Image has no correct URL format.");
        if (!nameIsValid(product))
            throw new IllegalArgumentException("The name needs to be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters");
        if (!descriptionIsValid(product))
            throw new IllegalArgumentException("Description is character limited to " + MAX_DESC_LENGTH + " chars");
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
        int chars = product.getName().length();
        return MIN_NAME_LENGTH <= chars && chars <= MAX_NAME_LENGTH;
    }

    private boolean descriptionIsValid(Product product) {
        int chars = product.getDescription().length();
        return chars <= MAX_DESC_LENGTH;
    }


}
