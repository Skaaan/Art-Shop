package com.example.cbd.apiGateway.service;


import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.service.ImagePexelsService;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppServiceTest {
/*
    private AppService appService;

    @Mock
    private ProductService productService;

    @Mock
    private ImagePexelsService imageGeneratorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        appService = new AppService(productService, imageGeneratorService);
    }

    @Test
    public void testGetProductById() {
        Product sampleProduct = new Product();
        sampleProduct.setId(1L);
        sampleProduct.setName("Test Product");

        Mockito.when(productService.getProductById(1L)).thenReturn(sampleProduct);

        Product result = appService.getProductById(1L);

        assertEquals(sampleProduct, result);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        Mockito.when(productService.getAllProducts()).thenReturn(Collections.singletonList(product1));

        Iterable<Product> result = appService.getAllProducts();

        assertEquals(Collections.singletonList(product1), result);
    }

    @Test
    public void testCreateProduct() {
        Product newProduct = new Product();
        newProduct.setId(2L);
        newProduct.setName("New Product");

        appService.createProduct(newProduct);

        Mockito.verify(productService, Mockito.times(1)).createProduct(newProduct);
    }

    @Test
    public void testDeleteProductById() throws ProductNotPresentException {
        Mockito.doNothing().when(productService).deleteProduct(1L);

        appService.deleteProduct(1L);

        Mockito.verify(productService, Mockito.times(1)).deleteProduct(1L);
    }

    @Test
    public void testDeleteAllProducts() {
        Mockito.doNothing().when(productService).deleteAllProducts();

        appService.deleteAllProducts();

        Mockito.verify(productService, Mockito.times(1)).deleteAllProducts();
    }

    @Test
    public void testUpdateProduct() throws ProductNotPresentException {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");

        Mockito.doNothing().when(productService).updateProduct(updatedProduct);

        appService.updateProduct(updatedProduct);

        Mockito.verify(productService, Mockito.times(1)).updateProduct(updatedProduct);
    }

    @Test
    public void testGetImageByPrompt() throws ExternalApiException {
        Mockito.when(imageGeneratorService.getImageByPrompt("TestPrompt")).thenReturn("Test Image URL");

        String result = appService.getImageByPrompt("TestPrompt");

        assertEquals("Test Image URL", result);
    }

    @Test
    public void testGetRandomImage() throws ExternalApiException {
        Mockito.when(imageGeneratorService.getRandomImage()).thenReturn("Random Image URL");

        String result = appService.getRandomImage();

        assertEquals("Random Image URL", result);
    }

    @Test
    public void testDeleteProductByIdThrowsException() throws ProductNotPresentException {
        Mockito.doThrow(new ProductNotPresentException("Product not found")).when(productService).deleteProduct(1L);

        assertThrows(ProductNotPresentException.class, () -> appService.deleteProduct(1L));
    }

    @Test
    public void testUpdateProductThrowsException() throws ProductNotPresentException {
        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");

        Mockito.doThrow(new ProductNotPresentException("Product not found")).when(productService).updateProduct(updatedProduct);

        assertThrows(ProductNotPresentException.class, () -> appService.updateProduct(updatedProduct));
    }*/
}