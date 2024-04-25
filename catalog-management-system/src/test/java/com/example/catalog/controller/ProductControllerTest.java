package com.example.catalog.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.catalog.entity.Product;
import com.example.catalog.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;

    @BeforeEach
    void setUp() {
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
    void getAllProducts_ReturnsListOfProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(testProduct));
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    @Test
    void getProductById_ReturnsProductIfExists() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(testProduct));
        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void getProductById_ReturnsNotFoundIfProductDoesNotExist() throws Exception {
        when(productService.getProductById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/products/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addProduct_ReturnsCreatedWithProduct() throws Exception {
        when(productService.saveProduct(any())).thenReturn(testProduct);
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void updateProduct_ReturnsUpdatedProduct() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(testProduct));
        when(productService.saveProduct(any())).thenReturn(testProduct);
        mockMvc.perform(put("/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void updateProduct_ReturnsNotFoundIfProductDoesNotExist() throws Exception {
        when(productService.getProductById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(put("/products/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(testProduct)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProduct_DeletesProduct() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(testProduct));
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void deleteProduct_ReturnsNotFoundIfProductDoesNotExist() throws Exception {
        when(productService.getProductById(2L)).thenReturn(Optional.empty());
        mockMvc.perform(delete("/products/2"))
                .andExpect(status().isNotFound());
    }
}

