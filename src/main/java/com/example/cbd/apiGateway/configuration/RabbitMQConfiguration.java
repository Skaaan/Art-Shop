package com.example.cbd.apiGateway.configuration;


import com.example.cbd.externalApi.listener.ImagePexelsListener;
import com.example.cbd.externalApi.producer.ImagePexelsProducer;
import com.example.cbd.storageApi.listener.ProductListener;
import com.example.cbd.storageApi.producer.ProductProducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    /*@Value("${xchange.name}")
    public static String DIRECT_EXCHANGE;
    @Value("${queue-names.product-service}")
    public static String PRODUCT_QUEUE;
    @Value("${queue-names.image-service}")
    public static String IMAGE_QUEUE;
    @Value("${routing-keys.product-service}")
    public static String PRODUCT_KEY;
    @Value("${routing-keys.image-service}")
    public static String IMAGE_KEY;*/

    @Value("xchange_name")
    private String DIRECT_EXCHANGE;
    @Value("product_queue")
    private String PRODUCT_QUEUE;
    @Value("image_queue")
    private String IMAGE_QUEUE;
    @Value("product_key")
    private String PRODUCT_KEY;
    @Value("image_key")
    private String IMAGE_KEY;


    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Queue productQueue() {
        return new Queue(PRODUCT_QUEUE);
    }

    @Bean
    public Queue imageQueue() {
        return new Queue(IMAGE_QUEUE);
    }

    @Bean
    public Binding productBinding(Queue productQueue, DirectExchange exchange) {
        return BindingBuilder.bind(productQueue).to(exchange).with(PRODUCT_KEY);
    }

    @Bean
    public Binding imageBinding(Queue imageQueue, DirectExchange exchange) {
        return BindingBuilder.bind(imageQueue).to(exchange).with(IMAGE_KEY);
    }
/*
    @Bean
    public ProductListener productListener() {
        return new ProductListener();
    }

    @Bean
    public ImagePexelsListener imagePexelsListener() {
        return new ImagePexelsListener();
    }

    @Bean
    public ProductProducer productProducer() {
        return new ProductProducer();
    }

    @Bean
    public ImagePexelsProducer imagePexelsProducer() {
        return new ImagePexelsProducer();
    }*/

}
