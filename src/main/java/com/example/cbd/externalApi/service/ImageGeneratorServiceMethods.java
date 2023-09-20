package com.example.cbd.externalApi.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.model.PexelsImage;

import java.io.IOException;

public interface ImageGeneratorServiceMethods {

    PexelsImage getImageByPrompt(String prompt) throws IOException, ExternalApiException;

    PexelsImage getRandomImage() throws IOException, ExternalApiException;

}
