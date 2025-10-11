package com.eonis.demo.core.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CreateOrder {

    private String email;

    private Long productId;
    /**
     * Map of OptionType name -> Map of OptionChoice ID -> selected value
     * Example:
     * {
     * "COLOR": {1: "RED"},
     * "SIZE": {8: "S"}
     * }
     */
    private Map<String, Map<Long, String>> selectedChoiceMap;
}
