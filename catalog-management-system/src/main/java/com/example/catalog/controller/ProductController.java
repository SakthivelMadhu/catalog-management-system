package com.example.catalog.controller;

import com.example.catalog.entity.Product;
import com.example.catalog.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "category", required = false) String category
    ) {
        List<Product> products;

        if (name != null) {
            products = productService.getProductsByName(name);
        } else if (brand != null) {
            products = productService.getProductsByBrand(brand);
        } else if (category != null) {
            products = productService.getProductsByCategory(category);
        } else {
            products = productService.getAllProducts();
        }

        return ResponseEntity.ok(products);
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    productService.saveProduct(product);
                    return ResponseEntity.ok(product);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> existingProduct = productService.getProductById(id);
        if (existingProduct.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
