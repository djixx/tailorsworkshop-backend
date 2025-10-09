package com.eonis.demo.core.service;

import java.util.Map;
import java.util.Set;

public interface OrderValidationService {
    void validate(Map<String, Set<String>> validOptionsMap, Map<String, Map<Long, String>> selectedChoiceMap);
}
