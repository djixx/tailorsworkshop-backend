package com.eonis.demo.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categoriesAndSubtypes")
public class CategorySubtypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subtypeId", nullable = false)
    private SubtypeEntity subtype;

    @OneToOne(mappedBy = "categorySubtype", cascade = CascadeType.ALL)
    private ProductEntity product;
}
