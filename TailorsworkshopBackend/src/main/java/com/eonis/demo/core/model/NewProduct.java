package com.eonis.demo.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewProduct {
    private String name;
    private BigDecimal price;
    private String description;
    private Long categoryId;
    private List<Long> optionTypes;
}
