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
     * key : value
     * "COLOR": "RED",
     * "SIZE": "S"
     * }
     */
    private Map<String, String> selectedChoiceMap;
}
