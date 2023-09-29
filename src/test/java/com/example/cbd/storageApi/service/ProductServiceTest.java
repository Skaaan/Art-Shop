package com.example.cbd.storageApi.service;

import com.example.cbd.storageApi.exceptions.ProductNotPresentException;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Captor
    ArgumentCaptor<Product> productCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        Product expectedProduct = new Product("p1", "p1 description", new BigDecimal("44.5"), "image");
        expectedProduct.setId(productId);

        when(productRepository.getProductById(productId)).thenReturn(expectedProduct); //configure behaviour test for the mock

        Product actualProduct = productService.getProductById(productId);

        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testCreateProduct() {
        Product expectedProduct = new Product("p1", "p1 description", new BigDecimal("44.5"), "image");

        productService.createProduct(expectedProduct);

        verify(productRepository).save(productCaptor.capture());
        Product actualProduct = productCaptor.getValue();
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testCreateMultipleProducts() {
        // Arrange
        Product product1 = new Product("p1", "p1 description", new BigDecimal("44.5"), "image1");
        Product product2 = new Product("p2", "p2 description", new BigDecimal("34.5"), "image2");
        Product product3 = new Product("p3", "p3 description", new BigDecimal("24.5"), "image3");
        Product product4 = new Product("p4", "p4 description", new BigDecimal("14.5"), "image4");
        Product product5 = new Product("p5", "p5 description", new BigDecimal("4.5"), "image5");

        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);

        products.forEach(productService::createProduct);

        verify(productRepository, VerificationModeFactory.times(5)).save(any(Product.class));

        when(productRepository.findAll()).thenReturn(products);
        List<Product> allProducts = (List<Product>) productService.getAllProducts();
        assertEquals(5, allProducts.size());
    }

    @Test
    void testGetAllProducts_One_Product() {
        Product expectedProduct = new Product("p1", "p1 description", new BigDecimal("44.5"), "image");
        when(productRepository.findAll()).thenReturn(Collections.singletonList(expectedProduct));

        Iterable<Product> actualProducts = productService.getAllProducts();

        assertEquals(Collections.singletonList(expectedProduct), actualProducts);
    }

    @Test
    void testGetAllProducts_Three_Products() {
        Product product1 = new Product("p1", "p1 description", new BigDecimal("44.5"), "image1");
        Product product2 = new Product("p2", "p2 description", new BigDecimal("54.5"), "image2");
        Product product3 = new Product("p3", "p3 description", new BigDecimal("64.5"), "image3");

        when(productRepository.findAll()).thenReturn(List.of(product1, product2, product3));

        List<Product> actualProducts = (List<Product>) productService.getAllProducts();

        assertEquals(3, actualProducts.size(), "The size of the product list should be 3");
        assertEquals(List.of(product1, product2, product3), actualProducts, "The actual products should match the expected products");
    }

    @Test
    void testGetAllProducts_No_Products() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());

        Iterable<Product> actualProducts = productService.getAllProducts();
        
        assertThat(actualProducts).isEmpty();
        assertTrue(!actualProducts.iterator().hasNext());
    }

    @Test
    void testDeleteProduct_ProductIsPresent() {
        Long existingProductId = 1L;
        Product existingProduct = new Product("p1", "p1 description", new BigDecimal("44.5"), "image1");

        List<Product> productList = new ArrayList<>();
        productList.add(existingProduct);

        when(productRepository.findById(existingProductId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.findAll()).thenReturn(productList);

        assertEquals(1, ((List<Product>) productService.getAllProducts()).size(), "Initially, there should be one product");

        doAnswer(invocation -> {
            productList.remove(existingProduct);
            return null;
        }).when(productRepository).deleteById(existingProductId);

        assertDoesNotThrow(() -> productService.deleteProduct(existingProductId), "Exception should not be thrown for existing product");

        verify(productRepository, times(1)).deleteById(existingProductId);
        assertEquals(0, ((List<Product>) productService.getAllProducts()).size(), "Finally, there should be no product");
    }

    @Test
    void testDeleteProduct_WhenProductIsNotPresent() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotPresentException.class, () -> productService.deleteProduct(productId));
    }

    @Test
    void testUpdateProduct_WhenProductIsPresent() throws ProductNotPresentException {
        Long productId = 1L;
        Product expectedProduct = new Product("p1", "p1 description", new BigDecimal("44.5"), "image");
        expectedProduct.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        productService.updateProduct(expectedProduct);

        verify(productRepository).save(productCaptor.capture());
        Product actualProduct = productCaptor.getValue();
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void testUpdateProduct_WhenProductIsNotPresent() {
        Long productId = 1L;
        Product product = new Product("name", "description", BigDecimal.ONE, "image");
        product.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotPresentException.class, () -> productService.updateProduct(product));
    }


}