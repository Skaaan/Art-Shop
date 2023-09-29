package com.example.cbd.storageApi.exceptions;

public class ProductNotPresentException extends Exception {

    public ProductNotPresentException() {
        super();
    }
    public ProductNotPresentException(String message) {
        super(message);
    }

}
