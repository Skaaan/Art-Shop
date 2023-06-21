package com.example.cbd.apiGateway.controller;


import com.example.cbd.apiGateway.service.AppService;
import com.example.cbd.storageApi.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(path="api/v1/")
public class AppController implements AppControllerMethods {


    private static final String PRODUCT_TAG = "product";
    private static final String AI_TAG = "ai";

    private final AppService appService;


    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }



    //@PreAuthorize("hasRole('user')")
    @GetMapping("/" + PRODUCT_TAG + "{id}")
    @ResponseStatus(OK)
    @Override
    //add api description
    public ResponseEntity<?> getProductById(@NotNull @PathVariable UUID id) {
        //add logging
        return status(OK).body(this.appService.getProductById(id));
    }


    @GetMapping("/" + PRODUCT_TAG)
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<?> getAllProducts() {
        return status(OK).body(this.appService.getAllProducts());
    }

    @PostMapping("/" + PRODUCT_TAG)
    @Override
    @ResponseStatus(CREATED)
    public ResponseEntity<?> createProduct(@NotNull @RequestBody final Product product) {
        this.appService.createProduct(product);
        return status(CREATED).build();
    }

    @DeleteMapping("/" + PRODUCT_TAG + "{id}")
    @Override
    @ResponseStatus(OK)
    public ResponseEntity<?> deleteProductById(@NotNull @PathVariable UUID id) {
        this.appService.deleteProduct(id);
        return status(OK).build();
    }

    @Override
    @ResponseStatus(OK)
    public ResponseEntity<?> deleteAllProducts() {
        this.appService.deleteAllProducts();
        return status(OK).build();
    }

    @PutMapping(path = "/" + AI_TAG + "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<?> updateProduct(@NotNull @PathVariable UUID id) {
        this.appService.updateProduct(id);
        return status(OK).build();
    }






    @PostMapping("/" + AI_TAG)
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<?> getAiImage(@NotNull String prompt) {
        return null;
    }

    @Override
    @ResponseStatus(OK)
    public ResponseEntity<?> getRandomAiImage() {
        return null;
    }
}