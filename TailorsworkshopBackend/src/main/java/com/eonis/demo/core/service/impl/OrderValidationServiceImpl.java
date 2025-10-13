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
    public void validate(Set<OptionTypeEntity> validOptionTypes, Map<String, String> selectedChoiceMap) {
        Map<String, Set<String>> validOptionsMap = getValidOptionsMap(validOptionTypes);

        selectedChoiceMap.forEach((selectedKey, selectedValue) -> {
            if ("LENGTH".equalsIgnoreCase(selectedKey)) {
                validateNumberOption(selectedValue);

            } else {
                validateStandardOption(selectedKey, selectedValue, validOptionsMap);
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

    private void validateNumberOption(String selectedChoice) {
        try {
            Double.parseDouble(selectedChoice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Invalid numeric value '" + selectedChoice + "' for option NUMBER"
            );
        }
    }

    private void validateStandardOption(String selectedKey,
                                        String selectedValue,
                                        Map<String, Set<String>> validOptionsMap) {
        Set<String> validChoiceValues = validOptionsMap.get(selectedKey);
        if (validChoiceValues == null) {
            throw new IllegalArgumentException("Invalid option type: " + selectedKey);
        }

        if (!validChoiceValues.contains(selectedValue)) {
            throw new IllegalArgumentException(
                    "Invalid choice ID " + selectedValue + " for option type " + selectedKey
            );
        }
    }
}
