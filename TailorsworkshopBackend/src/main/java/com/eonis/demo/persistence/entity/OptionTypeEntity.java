package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "optionTypes")
public class OptionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "optionTypeOptions",
            joinColumns = @JoinColumn(name = "optionTypeId"),
            inverseJoinColumns = @JoinColumn(name = "optionId")
    )
    private Set<OptionEntity> options;

}
