package com.example.cbd.apiGateway.configuration;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    @Value("exchange_name")
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
    public Binding productBinding() {
        return BindingBuilder.bind(productQueue()).to(directExchange()).with(PRODUCT_KEY);
    }

    @Bean
    public Binding imageBinding() {
        return BindingBuilder.bind(imageQueue()).to(directExchange()).with(IMAGE_KEY);
    }


}
