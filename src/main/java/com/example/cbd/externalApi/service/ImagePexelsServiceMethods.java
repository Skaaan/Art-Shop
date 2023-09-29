package com.example.cbd.externalApi.service;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.externalApi.exceptions.ExternalApiException;

import java.io.IOException;

public interface ImagePexelsServiceMethods {

    String getImageByPrompt(String prompt) throws IOException, ExternalApiException, MessagingErrorException;

    String getRandomImage() throws IOException, ExternalApiException, MessagingErrorException;

}
