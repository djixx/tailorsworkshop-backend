package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.Product;
import com.eonis.demo.persistence.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper implements Mapper<ProductEntity, Product> {
    @Override
    public Product map(ProductEntity input) {
        return new Product(
                input.getName(),
                input.getPrice(),
                input.getDescription(),
                input.getCategorySubtype().getId(),
                input.getCategorySubtype().getCategory().getId(),
                input.getCategorySubtype().getSubtype().getId()
        );
    }

    @Override
    public List<Product> map(List<ProductEntity> input) {
        return Mapper.super.map(input);
    }
}
