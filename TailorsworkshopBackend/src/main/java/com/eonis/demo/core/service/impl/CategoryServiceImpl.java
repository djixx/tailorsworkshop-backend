package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CategoryMapper;
import com.eonis.demo.core.model.Category;
import com.eonis.demo.core.service.CategoryService;
import com.eonis.demo.persistence.entity.CategoryEntity;
import com.eonis.demo.persistence.jpa_repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> all = categoryRepository.findAll();
        return categoryMapper.map(all);
    }
}
