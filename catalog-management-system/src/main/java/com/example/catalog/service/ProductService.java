package com.example.catalog.service;

import com.example.catalog.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    List<Product> getAllProducts();
    
    Optional<Product> getProductById(Long id);
    
    Product saveProduct(Product product);
    
    void deleteProduct(Long id);
    
 //  methods for search and filtering
    List<Product> getProductsByName(String name);

    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByCategory(String category);
}
