package com.example.cbd.storageApi.service;

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

    @Test
    void testValidateProduct_InvalidImageUrl() {
        Product productWithInvalidImageUrl = new Product("Product Name", "Product Description", BigDecimal.valueOf(10.0), "invalid-url");
        assertThrows(IllegalArgumentException.class, () -> validationService.validateProduct(productWithInvalidImageUrl));
    }

    @Test
    void testImageUrlIsValid_ValidUrl() {
        Product productWithValidImageUrl = new Product("Product Name", "Product Description", BigDecimal.valueOf(10.0), "https://example.com/image.jpg");
        assertTrue(validationService.imageUrlIsValid(productWithValidImageUrl));
    }


}