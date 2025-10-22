package com.eonis.demo.core.model;

import com.eonis.demo.persistence.enums.CartStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ShoppingCart {
    private Long id;

    private LocalDateTime createdOn;

    private String createdBy;

    private LocalDateTime reviewedOn;

    private String reviewedBy;

    private CartStatus status;

    private List<CartItem> items;

    private BigDecimal totalPrice;
}
