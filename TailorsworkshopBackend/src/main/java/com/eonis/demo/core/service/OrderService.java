package com.eonis.demo.core.service;

import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.SaveOrder;

import java.util.Map;

public interface OrderService {
    CartItem save(Long productId, Map<String, String> selectedChoiceMap);

    void update(Long itemId, SaveOrder request);
}
