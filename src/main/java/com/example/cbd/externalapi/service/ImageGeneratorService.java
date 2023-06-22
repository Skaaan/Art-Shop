package com.example.cbd.externalapi.service;

import com.example.cbd.externalapi.exceptions.ExternalApiException;

import java.io.IOException;

public interface ImageGeneratorService {

    String getImageByPrompt(String prompt) throws IOException, ExternalApiException;

    String getRandomImage() throws IOException, ExternalApiException;

}
