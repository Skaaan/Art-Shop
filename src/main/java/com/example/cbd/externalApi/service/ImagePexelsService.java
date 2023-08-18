package com.example.cbd.externalApi.service;

import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.model.PhotoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImagePexelsService implements ImageGeneratorServiceMethods{


    private final String BASE_URL = "https://api.pexels.com/v1/";
    private final String API_TOKEN;
    private final String RANDOM_PROMPT = "Katze";

    private final WebClient client;

    private static final Logger log = LoggerFactory.getLogger(ImagePexelsService.class);


    public ImagePexelsService() {
        API_TOKEN = System.getenv("PEXEL_TOKEN");
        client = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, API_TOKEN)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private PhotoResult apiRequest(String prompt) {
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

        return client.get()
                .uri(api_url)
                .retrieve()
                .bodyToMono(PhotoResult.class)
                .block();

    }

    private PhotoResult getImageUrl(String prompt) throws ExternalApiException {
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
    public PhotoResult getImageByPrompt(String prompt) throws IOException, ExternalApiException {
        PhotoResult res = getImageUrl(prompt);
        //processImage(url);
        //log.info("Image Url: " + res + ", Prompt: " + prompt);
        return res;
    }

    @Override
    public PhotoResult getRandomImage() throws IOException, ExternalApiException {
        PhotoResult res = getImageUrl(RANDOM_PROMPT);
        //processImage(url);
        //System.out.println(res);
        return res;
    }
}
