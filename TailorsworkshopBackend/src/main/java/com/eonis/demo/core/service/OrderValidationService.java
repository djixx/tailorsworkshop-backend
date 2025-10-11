package com.eonis.demo.core.service;

import com.eonis.demo.persistence.entity.OptionTypeEntity;

import java.util.Map;
import java.util.Set;

public interface OrderValidationService {
    void validate(Set<OptionTypeEntity> validOptionTypes, Map<String, Map<Long, String>> selectedChoiceMap);
}
