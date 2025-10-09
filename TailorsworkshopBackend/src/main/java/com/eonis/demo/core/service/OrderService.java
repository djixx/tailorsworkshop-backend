package com.eonis.demo.core.service;

import java.util.Map;

public interface OrderService {
    void save(Long productId, Map<String, Map<Long, String>> selectedChoiceMap);
}
