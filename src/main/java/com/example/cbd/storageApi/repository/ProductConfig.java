package com.example.cbd.storageApi.repository;

import com.example.cbd.storageApi.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product one = new Product(UUID.randomUUID(), "Moin");
            Product two = new Product(UUID.fromString("hey"), "Second");
        };
    }
}
