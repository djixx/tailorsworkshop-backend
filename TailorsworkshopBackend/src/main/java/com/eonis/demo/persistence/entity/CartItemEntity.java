package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "cartItems")
public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @DecimalMin(value = "0.0", inclusive = false, message = "Product price must be greater than zero")
    private BigDecimal productPrice;

    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero")
    private BigDecimal totalPrice;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId")
    private ShoppingCartEntity cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductEntity product;

    @Column(columnDefinition = "TEXT")
    private String optionsJson;
}
