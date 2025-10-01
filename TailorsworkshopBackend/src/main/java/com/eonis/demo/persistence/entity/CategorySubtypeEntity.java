package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "categorySubtype", cascade = CascadeType.ALL)
    private List<ProductEntity> product;
}
