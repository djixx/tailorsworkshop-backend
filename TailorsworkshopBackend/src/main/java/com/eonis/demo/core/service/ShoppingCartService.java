package com.eonis.demo.core.service;

import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;

public interface ShoppingCartService {

    ShoppingCart getCartByEmail(String email);

    ShoppingCartEntity getOrCreateActiveCart(String email);
}
