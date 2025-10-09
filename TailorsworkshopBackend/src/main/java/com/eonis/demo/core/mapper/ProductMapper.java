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
                input.getId(),
                input.getName(),
                input.getPrice(),
                input.getDescription(),
                input.getProductCategory().getId(),
                input.getProductCategory().getName()
        );
    }

    @Override
    public List<Product> map(List<ProductEntity> input) {
        return Mapper.super.map(input);
    }

}
