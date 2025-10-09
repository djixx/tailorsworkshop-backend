package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.Category;
import com.eonis.demo.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper implements Mapper<CategoryEntity, Category> {

    @Override
    public Category map(CategoryEntity input) {
        Category category = new Category();
        category.setName(input.getName());
        category.setId(input.getId());
        return category;
    }

    @Override
    public List<Category> map(List<CategoryEntity> input) {
        return Mapper.super.map(input);
    }
}
