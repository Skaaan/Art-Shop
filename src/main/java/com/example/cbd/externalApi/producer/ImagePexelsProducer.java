package com.example.cbd.apiGateway.producer;

import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.apiGateway.model.MessageType;
import com.example.cbd.externalApi.exceptions.ExternalApiException;
import com.example.cbd.externalApi.service.ImagePexelsServiceMethods;
import com.example.cbd.storageApi.model.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.example.cbd.apiGateway.model.MessageType.*;

@RequiredArgsConstructor
@Slf4j
public class ImagePexelsProducer implements ImagePexelsServiceMethods {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;
    @Value("${keys.image-service}")
    private String IMAGE_SERVICE_KEY;

    @Override
    public String getImageByPrompt(String prompt) throws MessagingErrorException {
        var message = new Message(prompt.getBytes());
        var received = processMessage(message, GET_IMAGE, "GetImage", "Couldn't fetch an image");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), String.class);
    }

    @Override
    public String getRandomImage() throws MessagingErrorException {
        var message = new Message("".getBytes());
        var received = processMessage(message, GET_RANDOM_IMAGE, "GetRandomImage", "Couldn't fetch a random image");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), String.class);
    }

    private Message processMessage(Message message, MessageType messageType, String errorTag, String errorMessage) throws MessagingErrorException {
        var received = rabbitTemplate.sendAndReceive(directExchange.getName(), IMAGE_SERVICE_KEY, message);
        message.getMessageProperties().setType(messageType.name());
        if(receivedMessageHasError(received)) {
            log.error("Error occured in {}. Received Message is null.", errorTag);
            throw new MessagingErrorException(errorMessage);
        }
        return received;
    }

    private boolean receivedMessageHasError(Message receivedMessage) {
        return receivedMessage == null ||
                receivedMessage.getBody() == null ||
                new String(receivedMessage.getBody(), StandardCharsets.UTF_8).equals("error");
    }



}
