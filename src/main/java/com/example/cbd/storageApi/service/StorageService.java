package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService implements StorageServiceMethods<Product> {


    private final ProductRepository productRepository;

    @Autowired
    public StorageService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //todo ID verändern?
    @Override
    public Product getProductById(@NotNull Long id) {
        //todo, sus?
        return productRepository.getProductById(id);
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void createProduct(@NotNull Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getUuid());

        if (productOptional.isPresent()) {
            //todo later check values which cant be taken from user perspective (only unique names or sth)
            // also let this somehow throw a 400 error (if thats actually better) -> rethrow exception and handle that in controller?
            throw new IllegalStateException("Id already taken.");
        }
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(@NotNull Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Transactional
    @Override
    public void updateProduct(@NotNull Long id, String name) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id " + id + " does not exist."));

        if (name != null && !Objects.equals(product.getName(), name)) {
            product.setName(name);
        }

    }
}
