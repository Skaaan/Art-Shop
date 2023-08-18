package com.example.cbd.externalApi.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.model.PhotoResult;

import java.io.IOException;

public interface ImageGeneratorServiceMethods {

    PhotoResult getImageByPrompt(String prompt) throws IOException, ExternalApiException;

    PhotoResult getRandomImage() throws IOException, ExternalApiException;

}
