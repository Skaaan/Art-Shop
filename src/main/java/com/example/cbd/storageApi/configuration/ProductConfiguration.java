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
            Product one = new Product( "Cat Image", "An image of a cat.", new BigDecimal("20"), "https://images.pexels.com/photos/3687770/pexels-photo-3687770.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            Product two = new Product( "Dog Image", "An image of a dog.", new BigDecimal("10"), "https://images.pexels.com/photos/1170986/pexels-photo-1170986.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            Product three = new Product( "Dog Image", "An image of a tuktuk in a beautiful landscape.", new BigDecimal("15"), "https://images.pexels.com/photos/18365722/pexels-photo-18365722.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            Product four = new Product( "Dog Image", "Picture of fire.", new BigDecimal("12.5"), "https://images.pexels.com/photos/18372173/pexels-photo-18372173.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            Product five = new Product( "Face behind paper", "", new BigDecimal("50"), "https://images.pexels.com/photos/18363064/pexels-photo-18363064.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            Product six = new Product("Family", "An image picturing a man with his baby.", new BigDecimal("30"), "https://images.pexels.com/photos/18379357/pexels-photo-18379357.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800");
            productRepository.saveAll(List.of(one, two, three, four, five, six));
            log.info("Products got presaved into database");
        };
    }
}
