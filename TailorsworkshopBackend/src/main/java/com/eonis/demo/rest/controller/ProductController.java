package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.NewProduct;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.core.model.ProductDetails;
import com.eonis.demo.core.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<?> create(@RequestPart("newProduct") String newProductJson,
                                    @RequestPart(value = "image", required = false)
                                    MultipartFile imageFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            NewProduct newProduct = objectMapper.readValue(newProductJson, NewProduct.class);
            Product product = service.save(newProduct, imageFile);

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

}
