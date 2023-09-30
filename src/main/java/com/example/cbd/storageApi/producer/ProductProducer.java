package com.example.cbd.storageApi.producer;


import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.storageApi.model.ProductMessageType;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.ProductServiceMethods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.cbd.storageApi.model.ProductMessageType.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductProducer implements ProductServiceMethods<Product> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @Value("product_key")
    private String PRODUCT_SERVICE_KEY;

    @Override
    public Product getProductById(@NotNull Long id) throws MessagingErrorException {
        Message message = new Message(new Gson().toJson(id).getBytes());
        Message received = processMessage(message, GET_PRODUCT, "GetProduct", "Couldn't fetch a product");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), Product.class);
    }

    @Override
    public List<Product> getAllProducts() throws MessagingErrorException {
        Message message = new Message("".getBytes());
        Message received = processMessage(message, GET_ALL_PRODUCTS, "GetAllProducts", "Couldn't fetch products");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), new TypeToken<List<Product>>() {
        }.getType());
    }

    @Override
    public void createProduct(@NotNull Product product) throws MessagingErrorException {
        Message message = new Message(new Gson().toJson(product).getBytes());
        processMessage(message, CREATE_PRODUCT, "UpdateProduct", "Couldn't create product");
    }

    @Override
    public void deleteProduct(@NotNull Long id) throws MessagingErrorException {
        Message message = new Message(new Gson().toJson(id).getBytes());
        processMessage(message, DELETE_PRODUCT, "DeleteProduct", "Couldn't delete product");
    }

    @Override
    public void deleteAllProducts() throws MessagingErrorException {
        Message message = new Message("".getBytes());
        processMessage(message, DELETE_ALL_PRODUCTS, "DeleteAllProducts", "Couldn't delete all products");
    }

    @Override
    public void updateProduct(@NotNull Product product) throws MessagingErrorException {
        Message message = new Message(new Gson().toJson(product).getBytes());
        processMessage(message, UPDATE_PRODUCT, "UpdateProduct", "Couldn't update product");

    }

    private Message processMessage(Message message, ProductMessageType messageType, String errorTag, String errorMessage) throws MessagingErrorException {
        message.getMessageProperties().setType(messageType.name());
        Message received = rabbitTemplate.sendAndReceive(directExchange.getName(), PRODUCT_SERVICE_KEY, message);
        log.info(message.getMessageProperties().getType());
        if (receivedMessageHasError(received)) {
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
