package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategoryEntity productCategory;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems;

    @ManyToMany
    @JoinTable(
            name = "product_options",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "option_type_id")
    )
    private Set<OptionTypeEntity> optionTypes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;

}
