package com.example.cbd.externalApi.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;


@Slf4j
@Service
public class ImagePexelsService implements ImagePexelsServiceMethods {


    private final String BASE_URL = "https://api.pexels.com/v1/";
    private final String API_TOKEN;

    private final int AMOUNT_PER_PAGE = 10;

    private final WebClient client;

    ObjectMapper objectMapper;

    public ImagePexelsService() {
        API_TOKEN = System.getenv("PEXEL_TOKEN");
        client = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, API_TOKEN)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
        objectMapper = new ObjectMapper();
    }

    private String apiRequest(String prompt) throws ExternalApiException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("search")
                .queryParam("query", prompt)
                .queryParam("page", 1)
                .queryParam("per_page", 1);

        String api_url = uriBuilder.toUriString();
        String jsonString = client.get().uri(api_url).retrieve().bodyToMono(String.class).block();

        try {
            JsonNode srcNode = objectMapper.readTree(jsonString);

            JsonNode sizeNode = srcNode
                    .path("photos")
                    .get(0)
                    .path("src");

            return sizeNode.path("portrait").toString();
        } catch (JsonProcessingException e) {
            throw new ExternalApiException();
        }
    }

    private String apiRequest() throws ExternalApiException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("curated")
                .queryParam("page", 1)
                .queryParam("per_page", AMOUNT_PER_PAGE);

        String api_url = uriBuilder.toUriString();
        String jsonString = client.get().uri(api_url).retrieve().bodyToMono(String.class).block();
        try {
            JsonNode srcNode = objectMapper.readTree(jsonString);

            JsonNode sizeNode = srcNode
                    .path("photos")
                    .get(new Random().nextInt(AMOUNT_PER_PAGE))
                    .path("src");

            return sizeNode.path("portrait").toString();
        } catch (JsonProcessingException e) {
            throw new ExternalApiException();
        }
    }



    private String getImageUrl(String prompt) throws ExternalApiException {
        try {
            String response;
            if(prompt.equals("")) {
                response = apiRequest();
            } else {
                response = apiRequest(prompt);
            }
            if (response != null) {
                return response;
            }
            throw new ExternalApiException("The API was not able to create an Image out of the prompt");
        } catch (Exception e) {
            throw new ExternalApiException("There was a problem generating the image URL. " + e.getMessage());
        }
    }

    @Override
    public String getImageByPrompt(String prompt) throws ExternalApiException {
        String res = getImageUrl(prompt);
        log.info("URL {} was created", res);
        return res;
    }

    @Override
    public String getRandomImage() throws ExternalApiException {
        String res = getImageUrl("");
        log.info("URL {} was created", res);
        return res;
    }
}
