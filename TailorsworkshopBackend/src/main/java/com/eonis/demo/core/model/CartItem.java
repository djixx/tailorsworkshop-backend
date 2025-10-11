package com.eonis.demo.core.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItem {
    private Long id;

    private String productName;

    private BigDecimal productPrice;

    private BigDecimal totalPrice;

    private int quantity;

    private Long cartId;

    private String optionsJson;
}
