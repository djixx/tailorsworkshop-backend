package com.eonis.demo.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductData {

    private List<Category> categories;

    private List<OptionType> options;
}
