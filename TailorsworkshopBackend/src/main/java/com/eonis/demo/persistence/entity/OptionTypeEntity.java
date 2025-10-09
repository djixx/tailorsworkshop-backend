package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "option_type")
public class OptionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "option_type_choice",
            joinColumns = @JoinColumn(name = "option_type_id"),
            inverseJoinColumns = @JoinColumn(name = "option_choice_id")
    )
    private Set<OptionChoiceEntity> optionChoices;
}
