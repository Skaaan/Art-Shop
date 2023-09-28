package com.example.cbd.storageApi.service;


import com.example.cbd.apiGateway.model.MessageType;
import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class ProductListener {

    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${queue}")
    public String handle(Message message) {


        try {
            final MessageType messageType = MessageType.valueOf(message.getMessageProperties().getType());

            switch (messageType) {
                case GET_PRODUCT: {
                    log.info("GET PRODUCT Message received: {}", message);
                    return getProduct(convertToId(message));
                }
                case GET_ALL_PRODUCTS: {
                    log.info("GET ALL PRODUCTS Message received: {}", message);
                    return getAllProducts();
                }
                case DELETE_PRODUCT: {
                    log.info("DELETE PRODUCT Message received: {}", message);
                    return deleteProduct(convertToId(message));
                }
                default: {
                    return error();
                }
            }
        } catch (IllegalArgumentException e) {

        } catch (ProductNotPresentException ex) {

        }
        return error();
    }

    private String error() {
        return "error";
    }

    private String getProduct(Long id) {
        return new Gson().toJson(productService.getProductById(id));
    }

    private String getAllProducts() {
        return new Gson().toJson(productService.getAllProducts());
    }

    private String createProduct(Product product) {
        productService.createProduct(product);
        return "product_created";
    }

    private String deleteProduct(Long id) throws ProductNotPresentException {
        productService.deleteProduct(id);
        return "product_deleted";
    }


    private long convertToId(Message message) {
        return Long.parseLong(new String(message.getBody(), StandardCharsets.UTF_8));
    }

    private Product convertToProduct(Message message) {
        return new Gson().fromJson(new String(message.getBody(), StandardCharsets.UTF_8), Product.class);
    }



}
