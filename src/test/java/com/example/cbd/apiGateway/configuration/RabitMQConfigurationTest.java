package com.example.cbd.apiGateway.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Import(RabbitMQConfiguration.class)
@TestPropertySource(properties = {
        "exchange_name=my_direct_exchange",
        "product_queue=my_product_queue",
        "image_queue=my_image_queue",
        "product_key=my_product_key",
        "image_key=my_image_key"
})
 class RabitMQConfigurationTest {

    @Autowired
    private DirectExchange directExchange;

    @Autowired
    private Queue productQueue;

    @Autowired
    private Queue imageQueue;

    @Autowired
    private Binding productBinding;

    @Autowired
    private Binding imageBinding;

    @Test
    void testRabbitMQBeans() {
        assertNotNull(directExchange);
        assertNotNull(productQueue);
        assertNotNull(imageQueue);
        assertNotNull(productBinding);
        assertNotNull(imageBinding);
    }



}