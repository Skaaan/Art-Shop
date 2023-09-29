package com.example.cbd.externalApi.listener;

import com.example.cbd.externalApi.model.ImageMessageType;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.service.ImagePexelsService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class ImagePexelsListener {

    @Autowired
    private ImagePexelsService pexelsService;

    @RabbitListener(queues = "image_queue")
    public String handle(Message message) {

        try {
            final ImageMessageType messageType = ImageMessageType.valueOf(message.getMessageProperties().getType());

            switch (messageType) {
                case GET_IMAGE: {
                    log.info("GET PRODUCT Message received: {}", message);
                    return getImage(convertToPrompt(message));
                }
                case GET_RANDOM_IMAGE: {
                    log.info("GET ALL PRODUCTS Message received: {}", message);
                    return getRandomImage();
                }
                default: {
                    return error();
                }
            }
        } catch (Exception e) {
            return error();
        }
    }

    private String error() {
        return "error";
    }

    private String convertToPrompt(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    private String getImage(String prompt) throws ExternalApiException {
        return new Gson().toJson(pexelsService.getImageByPrompt(prompt));
    }

    private String getRandomImage() throws ExternalApiException {
        return new Gson().toJson(pexelsService.getRandomImage());
    }

}
