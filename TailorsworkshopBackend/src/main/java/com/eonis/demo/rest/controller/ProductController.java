package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.Product;
import com.eonis.demo.core.model.ProductDetails;
import com.eonis.demo.core.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetails> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getWithDetail(id));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable Long categoryId) {
        List<Product> products = service.getForCategory(categoryId);
        return ResponseEntity.ok(products);
    }
}
