package com.example.catalog.service;

import com.example.catalog.entity.Product;
import com.example.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrandIgnoreCaseContaining(brand);
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryIgnoreCaseContaining(category);
    }
}
