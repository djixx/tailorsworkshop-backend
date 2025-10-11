package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.service.OrderValidationService;
import com.eonis.demo.persistence.entity.OptionChoiceEntity;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;



@Service
public class OrderValidationServiceImpl implements OrderValidationService {
    @Override
    public void validate(Set<OptionTypeEntity> validOptionTypes, Map<String, Map<Long, String>> selectedChoiceMap) {
        Map<String, Set<String>> validOptionsMap = getValidOptionsMap(validOptionTypes);

        selectedChoiceMap.forEach((optionTypeName, choices) -> {
            if ("LENGTH".equalsIgnoreCase(optionTypeName)) {
                validateNumberOption(choices);

            } else {
                validateStandardOption(optionTypeName, choices, validOptionsMap);
            }
        });
    }

    private Map<String, Set<String>> getValidOptionsMap(Set<OptionTypeEntity> validOptionTypes) {
        return validOptionTypes.stream()
                .collect(Collectors.toMap(
                        OptionTypeEntity::getName,
                        ot -> ot.getOptionChoices().stream()
                                .map(OptionChoiceEntity::getName)
                                .collect(Collectors.toSet())
                ));
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
