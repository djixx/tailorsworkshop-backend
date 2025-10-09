package com.eonis.demo.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends Model {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long categoryId;
    private String categoryName;
}
