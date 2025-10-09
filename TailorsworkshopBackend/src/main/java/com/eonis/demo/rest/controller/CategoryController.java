package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.Category;
import com.eonis.demo.core.model.Subtype;
import com.eonis.demo.core.service.CategoryService;
import com.eonis.demo.core.service.SubtypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final SubtypeService subtypeService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public Subtype getAllSubtypes(@PathVariable Long categoryId) {
        return subtypeService.get(categoryId);
    }
}
