package com.example.cbd.api_gateway.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;

import java.util.UUID;



public interface AppControllerMethods {

    ResponseEntity<?> findProductById(@NotNull final UUID id);


    //external API
    ResponseEntity<?> getAiImage(@NotNull String prompt);

    ResponseEntity<?> getRandomAiImage();


}
