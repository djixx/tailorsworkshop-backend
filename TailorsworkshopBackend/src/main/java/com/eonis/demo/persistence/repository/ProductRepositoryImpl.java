package com.eonis.demo.persistence.repository;

import com.eonis.demo.core.mapper.ProductMapper;
import com.eonis.demo.core.model.Product;
import com.eonis.demo.persistence.entity.ProductEntity;
import com.eonis.demo.persistence.jpaRepository.ProductJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductMapper mapper;
    private final ProductJpaRepository repository;

    @Override
    public List<Product> getAll() {
        List<ProductEntity> entities = repository.findAll();
        return mapper.map(entities);
    }
}
