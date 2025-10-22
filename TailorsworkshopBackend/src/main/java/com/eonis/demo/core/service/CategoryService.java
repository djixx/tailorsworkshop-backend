package com.eonis.demo.core.service;

import com.eonis.demo.core.model.Category;
import com.eonis.demo.persistence.entity.ProductCategoryEntity;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    ProductCategoryEntity get(Long categoryId);
}
