package com.example.catalog.repository;


import com.example.catalog.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveProduct_ReturnsSavedProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setBrand("Test Brand");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setQuantity(20);
        product.setCategory("Test Category");
        Product savedProduct = productRepository.save(product);
        assertThat(savedProduct.getId()).isNotNull();
    }

    @Test
    void findById_ReturnsProductIfExists() {
        Product product = new Product();
        product.setName("Test Product");
        product.setBrand("Test Brand");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setQuantity(20);
        product.setCategory("Test Category");
        Product savedProduct = productRepository.save(product);
        assertThat(productRepository.findById(savedProduct.getId())).isNotEmpty();
    }

    @Test
    void findById_ReturnsEmptyOptionalIfProductDoesNotExist() {
        assertThat(productRepository.findById(100L)).isEmpty();
    }

    @Test
    void deleteProduct_DeletesProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setBrand("Test Brand");
        product.setDescription("Test Description");
        product.setPrice(10.0);
        product.setQuantity(20);
        product.setCategory("Test Category");
        Product savedProduct = productRepository.save(product);
        productRepository.deleteById(savedProduct.getId());
        assertThat(productRepository.findById(savedProduct.getId())).isEmpty();
    }
}
