package com.example.cbd.apiGateway.controller;

import com.example.cbd.apiGateway.service.AppService;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="api/")
@RestController
public class AppController {

    private static final String PRODUCT_URI = "product/";
    private static final String PEXELS_URI = "pexels/";

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }



    @GetMapping(path = PRODUCT_URI + "{id}")
    //add api description
    public ResponseEntity<?> getProductById(@NotNull @PathVariable("id") Long id) {
        log.info("GetProduct by following id \"{}\"", id);
        return status(OK).body(appService.getProductById(id));
    }

    @GetMapping(path = PRODUCT_URI)
    public ResponseEntity<?> getAllProducts() {
        log.info("GetAllProducts");
        return status(OK).body(appService.getAllProducts());
    }

    @PostMapping(path = PRODUCT_URI)
    public ResponseEntity<?> createProduct(@NotNull @RequestBody final Product product) {
        log.info("CreateProduct: {}", product);
        appService.createProduct(product);
        return status(CREATED).build();
    }

    @DeleteMapping(path = PRODUCT_URI + "{id}")
    public ResponseEntity<?> deleteProductById(@NotNull @PathVariable Long id) throws ProductNotPresentException {
        log.info("DeleteProduct by following id \"{}\"", id);
        appService.deleteProduct(id);
        return status(OK).build();
    }

    @DeleteMapping(path = PRODUCT_URI)
    public ResponseEntity<?> deleteAllProducts() {
        log.info("DeleteAllProducts");
        appService.deleteAllProducts();
        return status(OK).build();
    }

    @PutMapping(path = PRODUCT_URI)
    public ResponseEntity<?> updateProduct(@NotNull @RequestBody Product product) throws ProductNotPresentException {
        log.info("UpdateProduct");
        appService.updateProduct(product);
        return status(OK).build();
    }

    @GetMapping(path = PEXELS_URI + "{prompt}")
    public ResponseEntity<String> getExternalImage(@NotNull @PathVariable String prompt) throws ExternalApiException {
        log.info("GetExternalImage by following prompt {}", prompt);
        return status(OK).body(appService.getImageByPrompt(prompt));
    }

    @GetMapping(path = PEXELS_URI)
    public ResponseEntity<String> getRandomExternalImage() throws ExternalApiException {
        log.info("GetRandomExternalImage");
        return status(OK).body(appService.getRandomImage());
    }


    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<?> externalApiException(ExternalApiException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ProductNotPresentException.class)
    public ResponseEntity<?> productNotPresentException(ProductNotPresentException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

}