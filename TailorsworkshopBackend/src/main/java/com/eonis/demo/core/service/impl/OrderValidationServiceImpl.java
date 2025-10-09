package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.service.OrderValidationService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class OrderValidationServiceImpl implements OrderValidationService {
    @Override
    public void validate(Map<String, Set<String>> validOptionsMap,
                         Map<String, Map<Long, String>> selectedChoiceMap) {

        selectedChoiceMap.forEach((optionTypeName, choices) -> {
            if ("LENGTH".equalsIgnoreCase(optionTypeName)) {
                validateNumberOption(choices);

            } else {
                validateStandardOption(optionTypeName, choices, validOptionsMap);
            }
        });
    }

    private void validateNumberOption(Map<Long, String> choices) {
        choices.forEach((choiceId, value) -> {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Missing numeric value for option NUMBER");
            }
            try {
                Double.parseDouble(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Invalid numeric value '" + value + "' for option NUMBER"
                );
            }
        });
    }

    private void validateStandardOption(String optionTypeName,
                                        Map<Long, String> choices,
                                        Map<String, Set<String>> validOptionsMap) {
        Set<String> validChoiceValues = validOptionsMap.get(optionTypeName);
        if (validChoiceValues == null) {
            throw new IllegalArgumentException("Invalid option type: " + optionTypeName);
        }

        for (String selectedValue : choices.values()) {
            if (!validChoiceValues.contains(selectedValue)) {
                throw new IllegalArgumentException(
                        "Invalid choice ID " + selectedValue + " for option type " + optionTypeName
                );
            }
        }
    }
}
