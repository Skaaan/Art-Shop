package com.example.cbd.externalApi.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.model.PhotoResult;
import com.example.cbd.externalApi.model.Test;

import java.io.IOException;

public interface ImageGeneratorServiceMethods {

    Test getImageByPrompt(String prompt) throws IOException, ExternalApiException;

    Test getRandomImage() throws IOException, ExternalApiException;

}
