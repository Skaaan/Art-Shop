package com.example.cbd.storageApi.repository;

import com.example.cbd.storageApi.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

public class ProductConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product one = new Product(1L, "Moin");
            Product two = new Product(2L, "Second");
            productRepository.saveAll(List.of(one, two));
        };
    }
}
