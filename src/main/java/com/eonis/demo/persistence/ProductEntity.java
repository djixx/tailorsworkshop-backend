package com.eonis.demo.persistence;

import com.eonis.demo.persistence.enums.CategoryEnum;
import com.eonis.demo.persistence.enums.SubtypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "categoryId", referencedColumnName = "categoryId"),
            @JoinColumn(name = "subtypeId", referencedColumnName = "subtypeId")
    })
    private CategorySubtypeEntity categorySubtype;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems;

}
