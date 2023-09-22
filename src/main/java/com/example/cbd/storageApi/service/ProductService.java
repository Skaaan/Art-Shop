package com.example.cbd.storageApi.service;


import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@CacheConfig
public class ProductService implements ProductServiceMethods<Product> {

    private final ProductRepository productRepository;

    private final String CACHE_TAG = "product";

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //todo ID verändern?
    @Cacheable(value = CACHE_TAG, key="#id")
    @Override
    public Product getProductById(@NotNull Long id) {
        return productRepository.getProductById(id);
    }

    @Cacheable(value = CACHE_TAG)
    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @CacheEvict(value=CACHE_TAG, allEntries = true)
    public void createProduct(@NotNull Product product) {
        Optional<Product> productOptional = productRepository.findById(product.getId());

        //todo check product for any non permitted values etc.
        // could do with extra class "ValidatorService.java" -> throws Exception when e.g. a name has wrong values
        // check if any value is null e.g.;
        if (productOptional.isPresent()) {
            //todo later check values which cant be taken from user perspective (only unique names or sth)
            // also let this somehow throw a 400 error (if thats actually better) -> rethrow exception and handle that in controller?
            throw new IllegalStateException("Id already taken.");
        }
        productRepository.save(product);
    }

    @Override
    @CacheEvict(value = CACHE_TAG, key="#id")
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

    @CacheEvict(value = CACHE_TAG, key="#id")
    @Transactional
    @Override
    public void updateProduct(Product product) throws ProductNotPresentException {

        if (!productIsPresent(product.getId())) {
            throw new ProductNotPresentException("Student with id: " + product.getId() + " does not exist");
        }
        productRepository.save(product);
    }

    private boolean productIsPresent(Long id) {
        return productRepository.findById(id).isPresent();
    }
}
