package com.example.cbd.storageApi.repository;

import com.example.cbd.storageApi.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@Configuration
public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product one = new Product(1L, "Cat Image", "An image of a cat.", new BigDecimal("20"));
            Product two = new Product(2L, "Dog Image", "An image of a dog.", new BigDecimal("10"));
            productRepository.saveAll(List.of(one, two));
        };
    }
}
