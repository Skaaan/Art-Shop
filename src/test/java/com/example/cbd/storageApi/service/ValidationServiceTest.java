package com.example.cbd.storageApi.service;

import com.example.cbd.storageApi.ValidationService;
import com.example.cbd.storageApi.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService();
    }

    @Test
    void testValidateProduct_NullValue() {
        Product productWithNullValue = new Product(null, "Product Description", BigDecimal.valueOf(10.0), "https://example.com/image.jpg");
        assertThrows(NullPointerException.class, () -> validationService.validateProduct(productWithNullValue));
    }





}