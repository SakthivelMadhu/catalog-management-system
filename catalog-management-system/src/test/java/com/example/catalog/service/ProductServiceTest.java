package com.example.catalog.service;

import com.example.catalog.entity.Product;
import com.example.catalog.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setBrand("Test Brand");
        testProduct.setDescription("Test Description");
        testProduct.setPrice(10.0);
        testProduct.setQuantity(20);
        testProduct.setCategory("Test Category");
    }

    @Test
    void getAllProducts_ReturnsListOfProducts() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(testProduct));
        assertEquals(1, productService.getAllProducts().size());
    }

    @Test
    void getProductById_ReturnsProductIfExists() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(testProduct));
        assertEquals(Optional.of(testProduct), productService.getProductById(1L));
    }

    @Test
    void getProductById_ReturnsEmptyOptionalIfProductDoesNotExist() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), productService.getProductById(2L));
    }

    @Test
    void saveProduct_ReturnsSavedProduct() {
        when(productRepository.save(any())).thenReturn(testProduct);
        assertEquals(testProduct, productService.saveProduct(testProduct));
    }

    @Test
    void deleteProduct_DeletesProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
