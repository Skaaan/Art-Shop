package com.example.cbd.apiGateway.controller;


import com.example.cbd.apiGateway.service.AppService;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.model.PhotoResult;
import com.example.cbd.storageApi.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path="api/")
public class AppController implements AppControllerMethods {


    private static final String PRODUCT_URI = "product/";
    private static final String PEXELS_URI = "pexels/";

    private final AppService appService;


    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }



    //@PreAuthorize("hasRole('user')")
    @GetMapping(path = PRODUCT_URI + "{id}")
    @ResponseStatus(OK)
    @Override
    //add api description
    public ResponseEntity<?> getProductById(@NotNull @PathVariable("id") Long id) {
        //add logging
        return status(OK).body(appService.getProductById(id));
    }

    @GetMapping(path = PRODUCT_URI)
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<?> getAllProducts() {
        return status(OK).body(appService.getAllProducts());
    }

    @PostMapping(path = PRODUCT_URI)
    @Override
    @ResponseStatus(CREATED)
    public ResponseEntity<?> createProduct(@NotNull @RequestBody final Product product) {
        appService.createProduct(product);
        return status(CREATED).build();
    }

    @DeleteMapping(path = PRODUCT_URI + "{id}")
    @Override
    @ResponseStatus(OK)
    public ResponseEntity<?> deleteProductById(@NotNull @PathVariable Long id) {
        appService.deleteProduct(id);
        return status(OK).build();
    }

    @DeleteMapping(path = PRODUCT_URI)
    @Override
    @ResponseStatus(OK)
    public ResponseEntity<?> deleteAllProducts() {
        appService.deleteAllProducts();
        return status(OK).build();
    }

    @PutMapping(path = PRODUCT_URI + "{id}")
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<?> updateProduct(@NotNull @PathVariable Long id, @RequestParam(required = false) String name) {
        appService.updateProduct(id, name);
        return status(OK).build();
    }






    @GetMapping(path = PEXELS_URI + "{prompt}")
    @ResponseStatus(OK)
    @Override
    public ResponseEntity<PhotoResult> getAiImage(@NotNull @PathVariable String prompt) {
        try {
            return status(OK).body(appService.getImageByPrompt(prompt));
        } catch (IOException | ExternalApiException e) {
            e.printStackTrace();
        }
        //todo what error code?
        return status(BAD_GATEWAY).build();
    }

    @GetMapping(path = PEXELS_URI)
    @Override
    @ResponseStatus(OK)
    public ResponseEntity<PhotoResult> getRandomAiImage() {
        try {
            return status(OK).body(appService.getRandomImage());
        } catch (IOException | ExternalApiException e) {
            e.printStackTrace();
        }
        return status(BAD_GATEWAY).build();
    }


    //Todo ADD EXCEPTION HANDLER -> throws error codes regarding specific exceptions !!!
}