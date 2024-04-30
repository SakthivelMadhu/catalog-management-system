package com.example.catalog.repository;



import com.example.catalog.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameIgnoreCaseContaining(String name);

    List<Product> findByBrandIgnoreCaseContaining(String brand);

    List<Product> findByCategoryIgnoreCaseContaining(String category);
}


