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


@Slf4j
@Service
public class ImagePexelsService implements ImagePexelsServiceMethods {


    private final String BASE_URL = "https://api.pexels.com/v1/";
    private final String API_TOKEN;
    private final String RANDOM_PROMPT = "Random";

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
        //String api_url = "/search?query=" + prompt;

        /**
         * Weitere Attribute können sein: "orientation", "size", "color", "locale", "page", "per_page"
         * size: large (24MP), medium (12mp), small (4mp)
         * page: requested page number (default=1)
         * per_page: number of results per page (default=15, max=80)
         */
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("search")
                .queryParam("query", prompt)
                .queryParam("page", 1)
                .queryParam("per_page", 1);

                //.queryParam("size", 2);

        String api_url = uriBuilder.toUriString();

        String jsonString = client.get().uri(api_url).retrieve().bodyToMono(String.class).block();


        try {
            return convertToObject(jsonString);
        } catch (JsonProcessingException e) {
            throw new ExternalApiException();
        }

    }

    private String convertToObject(String json) throws JsonProcessingException {

        JsonNode srcNode = objectMapper.readTree(json);

        JsonNode sizeNode = srcNode
                .path("photos")
                .get(0)
                .path("src");

        String image = sizeNode.path("landscape").asText();

        return image;
    }


    private String getImageUrl(String prompt) throws ExternalApiException {
        try {
            var response = apiRequest(prompt);
            if (response != null) {
                return response;
                //return new URL(response.getPhotos().get(0).getSrc().getMedium());
            }
            throw new ExternalApiException("The API was not able to create an Image out of the prompt");
        } catch (Exception e) {
            throw new ExternalApiException("There was a problem generating the image URL. " + e.getMessage());
        }
    }

    @Override
    public String getImageByPrompt(String prompt) throws ExternalApiException {
        String res = getImageUrl(prompt);

        //log.info("Image Url: " + res + ", Prompt: " + prompt);
        return res;
    }

    @Override
    public String getRandomImage() throws ExternalApiException {
        String res = getImageUrl(RANDOM_PROMPT);

        return res;
    }
}
