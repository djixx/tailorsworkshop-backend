package com.eonis.demo.core.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class ProductDetails {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Long categoryId;
    private String categoryName;

    /**
     * Maps each option type name to its available choices.
     *
     * <p>Key: OptionType name (e.g., "COLOR", "SIZE")<br>
     * Value: Map of OptionChoice (ID - name)</p>
     *
     * <p>Example:</p>
     * <pre>
     * {
     *   "COLOR": {1L: "RED", 2L: "BLUE"},
     *   "SIZE": {8L: "S", 9L: "M"}
     * }
     * </pre>
     */
    private Map<String, Map<Long, String>> optionChoiceMap;

}
