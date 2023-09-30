package com.example.cbd.apiGateway.controller;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.apiGateway.service.AppService;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AppControllerTest {

    @Mock
    private AppService appService;

    @InjectMocks
    private AppController appController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProductById() throws MessagingErrorException {
        Long productId = 1L;
        Product product =  new Product("Product1", "Description1", BigDecimal.valueOf(10.0), "image1.jpg");
        when(appService.getProductById(productId)).thenReturn(product);

        ResponseEntity<?> responseEntity = appController.getProductById(productId);

        verify(appService).getProductById(productId);
    }

    @Test
    void testGetAllProducts() throws MessagingErrorException {
        List<Product> productList = List.of(
                new Product("Product1", "Description1", BigDecimal.valueOf(10.0), "image1.jpg"),
                new Product("Product2", "Description2", BigDecimal.valueOf(20.0), "image2.jpg")
        );

        when(appService.getAllProducts()).thenReturn(productList);

        ResponseEntity<?> responseEntity = appController.getAllProducts();

        verify(appService).getAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertNotNull(responseEntity.getBody());

        List<Product> responseProducts = (List<Product>) responseEntity.getBody();
        assertEquals(productList.size(), responseProducts.size());

        assertEquals("Product1", responseProducts.get(0).getName());
        assertEquals("Description1", responseProducts.get(0).getDescription());
        assertEquals(BigDecimal.valueOf(10.0), responseProducts.get(0).getPrice());
        assertEquals("image1.jpg", responseProducts.get(0).getImage());
    }

    @Test
    void testCreateProduct() throws MessagingErrorException {
        Product product = new Product("Product1", "Description", BigDecimal.valueOf(10.0), "image.jpg");

        doNothing().when(appService).createProduct(any(Product.class));

        ResponseEntity<?> responseEntity = appController.createProduct(product);

        verify(appService).createProduct(eq(product));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testDeleteProductById() throws ProductNotPresentException, MessagingErrorException {
        Long productId = 1L;

        doNothing().when(appService).deleteProduct(productId);

        ResponseEntity<?> responseEntity = appController.deleteProductById(productId);

        verify(appService).deleteProduct(eq(productId));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testDeleteAllProducts() throws MessagingErrorException {
        doNothing().when(appService).deleteAllProducts();

        ResponseEntity<?> responseEntity = appController.deleteAllProducts();

        verify(appService).deleteAllProducts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testUpdateProduct() throws ProductNotPresentException, MessagingErrorException {
        Product updatedProduct = new Product("Updated Product", "Updated Description", BigDecimal.valueOf(15.0), "updated.jpg");

        doNothing().when(appService).updateProduct(updatedProduct);

        ResponseEntity<?> responseEntity = appController.updateProduct(updatedProduct);

        verify(appService).updateProduct(eq(updatedProduct));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    void testGetExternalImage() throws ExternalApiException, MessagingErrorException {
        String prompt = "sample_prompt";
        String sampleImageUrl = "http://test.com/image.jpg";
        when(appService.getImageByPrompt(prompt)).thenReturn(sampleImageUrl);

        ResponseEntity<String> responseEntity = appController.getExternalImage(prompt);

        verify(appService).getImageByPrompt(eq(prompt));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sampleImageUrl, responseEntity.getBody());
    }

    @Test
    void testGetRandomExternalImage() throws ExternalApiException, MessagingErrorException {
        String sampleImageUrl = "http://test.com/sample.jpg";
        when(appService.getRandomImage()).thenReturn(sampleImageUrl);

        ResponseEntity<String> responseEntity = appController.getRandomExternalImage();

        verify(appService).getRandomImage();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(sampleImageUrl, responseEntity.getBody());
    }


    @Test
    void testMessageErrorException() {
        MessagingErrorException exception = new MessagingErrorException("Test error message");
        ResponseEntity<?> responseEntity = appController.messageErrorException(exception);

        assertEquals(400, responseEntity.getStatusCodeValue());
    }



}