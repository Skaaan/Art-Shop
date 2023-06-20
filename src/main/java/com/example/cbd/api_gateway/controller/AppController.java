package com.example.cbd.api_gateway.controller;


import com.example.cbd.api_gateway.service.AppService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(path="api/v1/application")
public class AppController implements AppControllerMethods {


    private static final String PRODUCT_TAG = "product";
    private static final String AI_TAG = "ai";

    private final AppService appService;


    @Autowired
    public AppController(AppService appService) {
        this.appService = appService;
    }


    @GetMapping("/" + PRODUCT_TAG + "{id}")
    @Override
    public ResponseEntity<?> findProductById(@NotNull @PathVariable UUID id) {
        return status(OK).body(this.appService.findProductById(id));
    }


    @PostMapping("/" + AI_TAG)
    @Override
    public ResponseEntity<?> getAiImage(@NotNull String prompt) {
        return null;
    }

    @Override
    public ResponseEntity<?> getRandomAiImage() {
        return null;
    }
}