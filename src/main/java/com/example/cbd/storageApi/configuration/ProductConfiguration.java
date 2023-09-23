package com.example.cbd.storageApi.configuration;

import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
@Configuration
public class ProductConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product one = new Product(1L, "Cat Image", "An image of a cat.", new BigDecimal("20"), "https://images.pexels.com/photos/3687770/pexels-photo-3687770.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200");
            Product two = new Product( 2L, "Dog Image", "An image of a dog.", new BigDecimal("10"), "https://images.pexels.com/photos/3687770/pexels-photo-3687770.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");

            productRepository.saveAll(List.of(one, two));
            log.info("Products got presaved into database");
        };
    }
}
