package com.example.cbd.externalapi.Service;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class ImageGenerator implements ImageGeneratorService {

    private String BASE_URL = "https://api.openai.com/v1";
    private String ENDPOINT_URL = "/images/generations";
    private String API_TOKEN;

    //256x256, 512x512, 1024x1024
    private String IMAGE_SIZE = "1024x1024";

    private WebClient client;

    public ImageGenerator() {
        this.API_TOKEN = System.getenv("TOKEN");

        this.client = WebClient.builder()
                .baseUrl(BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_TOKEN)
                .build();

    }

    @Override
    public String getImage(String prompt) throws IOException {
        URL url = getUrl(prompt);

        BufferedImage image = ImageIO.read(url);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "jpg", byteArrayOutputStream);


        byteArrayOutputStream.close();


        return "";
    }

    public URL getUrl(String prompt) {
        try {
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("prompt", prompt);
            requestBody.put("n", 1);
            requestBody.put("size", IMAGE_SIZE);
            var response = client.post()
                    .uri(ENDPOINT_URL)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            if (response != null) {
                return new URL(response);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }




    @Override
    public String getRandomImage() {
        return null;
    }
}
