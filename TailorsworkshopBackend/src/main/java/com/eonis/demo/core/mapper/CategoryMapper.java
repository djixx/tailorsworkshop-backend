package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.Category;
import com.eonis.demo.persistence.entity.ProductCategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements Mapper<ProductCategoryEntity, Category> {

    @Override
    public Category map(ProductCategoryEntity input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setId(input.getId());
        return category;
    }

    @Override
    public List<Category> map(List<ProductCategoryEntity> input) {
        return Mapper.super.map(input);
    }
}
