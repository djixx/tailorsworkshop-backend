package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CategoryMapper;
import com.eonis.demo.core.model.Category;
import com.eonis.demo.core.service.CategoryService;
import com.eonis.demo.persistence.entity.ProductCategoryEntity;
import com.eonis.demo.persistence.jpa_repository.ProductCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<Category> getAll() {
        List<ProductCategoryEntity> all = productCategoryRepository.findAll();
        return categoryMapper.map(all);
    }
}
