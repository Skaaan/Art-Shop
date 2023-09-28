package com.example.cbd.apiGateway.producer;


import com.example.cbd.apiGateway.exceptions.MessagingErrorException;
import com.example.cbd.apiGateway.model.MessageType;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.ProductServiceMethods;
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

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.example.cbd.apiGateway.model.MessageType.*;

@RequiredArgsConstructor
@Slf4j
public class ProductProducer implements ProductServiceMethods<Product> {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;
    @Value("${keys.product-service}")
    private String PRODUCT_SERVICE_KEY;


    @Override
    public Product getProductById(@NotNull Long id) throws MessagingErrorException {
        var message = new Message(new Gson().toJson(id).getBytes());
        var received = processMessage(message, GET_PRODUCT, "GetProduct", "Couldn't fetch a product");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), Product.class);
    }

    @Override
    public List<Product> getAllProducts() throws MessagingErrorException {
        var message = new Message("".getBytes());
        var received = processMessage(message, GET_ALL_PRODUCTS, "GetAllProducts", "Couldn't fetch products");
        return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), new TypeToken<List<Product>>(){}.getType());
    }

    @Override
    public void createProduct(@NotNull Product product) throws MessagingErrorException {
        var message = new Message(new Gson().toJson(product).getBytes());
        var received = processMessage(message, CREATE_PRODUCT, "UpdateProduct", "Couldn't create product");
        //return new Gson().fromJson(new String(received.getBody(), StandardCharsets.UTF_8), new TypeToken<List<Product>>(){}.getType());
    }

    @Override
    public void deleteProduct(@NotNull Long id) throws MessagingErrorException {
        var message = new Message(new Gson().toJson(id).getBytes());
        var received = processMessage(message, DELETE_PRODUCT, "DeleteProduct", "Couldn't delete product");
        //?
    }

    @Override
    public void deleteAllProducts() throws MessagingErrorException {
        var message = new Message("".getBytes());
        var received = processMessage(message, DELETE_ALL_PRODUCTS, "DeleteAllProducts", "Couldn't delete all products");
    }

    @Override
    public void updateProduct(@NotNull Product product) throws MessagingErrorException {
        var message = new Message(new Gson().toJson(product).getBytes());
        var received = processMessage(message, UPDATE_PRODUCT, "UpdateProduct", "Couldn't update product");

    }

    private Message processMessage(Message message, MessageType messageType, String errorTag, String errorMessage) throws MessagingErrorException {
        var received = rabbitTemplate.sendAndReceive(directExchange.getName(), PRODUCT_SERVICE_KEY, message);
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
