package com.eonis.demo.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "subtypes")
public class SubtypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "subtype", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CategorySubtypeEntity> categorySubtypes;
}
