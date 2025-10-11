package com.eonis.demo.core.mapper;

import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.persistence.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper implements Mapper<CartItemEntity, CartItem> {

    @Override
    public CartItem map(CartItemEntity input) {
        CartItem cartItem = new CartItem();
        cartItem.setId(input.getId());
        cartItem.setProductName(input.getProductName());
        cartItem.setQuantity(input.getQuantity());
        cartItem.setProductPrice(input.getProductPrice());
        cartItem.setTotalPrice(input.getTotalPrice());
        cartItem.setCartId(input.getCart().getId());
        cartItem.setOptionsJson(input.getOptionsJson());
        return cartItem;
    }
}
