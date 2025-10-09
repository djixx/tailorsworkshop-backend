package com.eonis.demo.core.service;

import com.eonis.demo.core.mapper.ProductMapper;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.persistence.entity.ProductEntity;
import com.eonis.demo.persistence.jpa_repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper mapper;
    private final ProductJpaRepository repository;

    public List<Product> getAll() {
        List<ProductEntity> entities = repository.findAll();
        return mapper.map(entities);
    }
}
