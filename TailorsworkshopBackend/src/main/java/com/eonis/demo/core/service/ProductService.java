package com.eonis.demo.core.service;

import com.eonis.demo.core.model.Product;
import com.eonis.demo.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAll() {
        return repository.getAll();
    }
}
