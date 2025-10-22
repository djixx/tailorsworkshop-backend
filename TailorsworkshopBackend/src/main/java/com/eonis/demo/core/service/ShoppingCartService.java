package com.eonis.demo.core.service;

import com.eonis.demo.core.model.ReviewCart;
import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.enums.CartStatus;

import java.util.List;

public interface ShoppingCartService {

    ShoppingCartEntity getOrCreateActiveCart(String email);

    void submitForReview(ShoppingCart cart);

    ShoppingCart review(ReviewCart reviewCart);

    List<ShoppingCart> getAll(CartStatus status);

    ShoppingCart getCartById(Long id);

    void save(ShoppingCartEntity cart);

    ShoppingCart getCartForUser();
}
