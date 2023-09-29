package com.example.cbd.apiGateway.controller;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.apiGateway.service.AppService;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/")
@RestController
public class AppController {

    private static final String PRODUCT_URI = "product/";
    private static final String PEXELS_URI = "pexels/";

    private final AppService appService;

    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }

    @Operation(summary = "Get a product by id.")
    @GetMapping(path = PRODUCT_URI + "{id}")
    public ResponseEntity<?> getProductById(@Parameter(description = "Id of the product you want to fetch.") @NotNull @PathVariable("id") Long id) throws MessagingErrorException {
        log.info("GetProduct by following id \"{}\"", id);
        return status(OK).body(appService.getProductById(id));
    }

    @Operation(summary = "Get all products available.")
    @GetMapping(path = PRODUCT_URI)
    public ResponseEntity<?> getAllProducts() throws MessagingErrorException {
        log.info("GetAllProducts");
        return status(OK).body(appService.getAllProducts());
    }

    @Operation(summary = "Create a product.")
    @PostMapping(path = PRODUCT_URI)
    public ResponseEntity<?> createProduct(@Parameter(description = "Product object, without id.") @NotNull @RequestBody final Product product) throws MessagingErrorException {
        log.info("CreateProduct: {}", product);
        appService.createProduct(product);
        return status(CREATED).build();
    }

    @Operation(summary = "Delete a product by id.")
    @DeleteMapping(path = PRODUCT_URI + "{id}")
    public ResponseEntity<?> deleteProductById(@Parameter(description = "Id of the product to be deleted.") @NotNull @PathVariable Long id) throws ProductNotPresentException, MessagingErrorException {
        log.info("DeleteProduct by following id \"{}\"", id);
        appService.deleteProduct(id);
        return status(OK).build();
    }

    @Operation(summary = "Delete all products.")
    @DeleteMapping(path = PRODUCT_URI)
    public ResponseEntity<?> deleteAllProducts() throws MessagingErrorException {
        log.info("DeleteAllProducts");
        appService.deleteAllProducts();
        return status(OK).build();
    }

    @Operation(summary = "Update a product.")
    @PutMapping(path = PRODUCT_URI)
    public ResponseEntity<?> updateProduct(@Parameter(description = "Product body, containing the correct id and all the updated values.") @NotNull @RequestBody Product product) throws ProductNotPresentException, MessagingErrorException {
        log.info("UpdateProduct");
        appService.updateProduct(product);
        return status(OK).build();
    }

    @Operation(summary = "Get an image by prompt.")
    @GetMapping(path = PEXELS_URI + "{prompt}")
    public ResponseEntity<String> getExternalImage(@Parameter(description = "String by which the API fetches you an Image.") @NotNull @PathVariable String prompt) throws ExternalApiException, MessagingErrorException {
        log.info("GetExternalImage by following prompt {}", prompt);
        return status(OK).body(appService.getImageByPrompt(prompt));
    }

    @Operation(summary = "Get a random image.")
    @GetMapping(path = PEXELS_URI)
    public ResponseEntity<String> getRandomExternalImage() throws ExternalApiException, MessagingErrorException {
        log.info("GetRandomExternalImage");
        return status(OK).body(appService.getRandomImage());
    }

    @ExceptionHandler(MessagingErrorException.class)
    public ResponseEntity<?> messageErrorException(MessagingErrorException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().build();
    }

}