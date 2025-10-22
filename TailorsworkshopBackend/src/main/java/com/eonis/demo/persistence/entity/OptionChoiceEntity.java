package com.eonis.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "option_choice")
public class OptionChoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "optionChoices")
    private Set<OptionTypeEntity> optionTypeEntities;

}
