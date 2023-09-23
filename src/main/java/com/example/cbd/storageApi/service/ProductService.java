package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Slf4j
@Service
@CacheConfig
public class ProductService implements ProductServiceMethods<Product> {

    private final ProductRepository productRepository;

    private final String CACHE_TAG = "product";

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = CACHE_TAG, key="#id")
    @Override
    public Product getProductById(@NotNull Long id) {
        return productRepository.getProductById(id);
    }

    @CacheEvict(value=CACHE_TAG, allEntries = true)
    @Override
    public void createProduct(@NotNull Product product) {
        //todo validationService
        productRepository.save(product);
    }

    @Cacheable(value = CACHE_TAG)
    @Override
    public Iterable<Product> getAllProducts() {
        log.info(productRepository.findAll().toString());
        return productRepository.findAll();
    }

    @Override
    @CacheEvict(value = CACHE_TAG, key="#id", allEntries = true)
    public void deleteProduct(@NotNull Long id) throws ProductNotPresentException {
        if (!productIsPresent(id)) {
            throw new ProductNotPresentException("");
        }
        productRepository.deleteById(id);
    }

    @CacheEvict(value = CACHE_TAG, allEntries = true)
    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @CachePut(value = CACHE_TAG, key="#id")
    @Transactional
    @Override
    public void updateProduct(Product product) throws ProductNotPresentException {

        if (!productIsPresent(product.getId())) {
            throw new ProductNotPresentException("Student with id: " + product.getId() + " does not exist");
        }
        //todo include validationservice
        productRepository.save(product);
    }

    private boolean productIsPresent(Long id) {
        return productRepository.findById(id).isPresent();
    }
}
