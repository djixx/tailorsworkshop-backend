package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.*;
import com.eonis.demo.core.service.CategoryService;
import com.eonis.demo.core.service.OptionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final OptionTypeService optionTypeService;
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAll();
    }

    @GetMapping
    public CreateProductData getOptionTypes() {
        List<Category> categories = categoryService.getAll();
        List<OptionType> optionTypes = optionTypeService.getAll();

        return new CreateProductData(categories, optionTypes);
    }
}
