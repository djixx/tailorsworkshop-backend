package com.eonis.demo.core.mapper;


import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper implements Mapper<ShoppingCartEntity, ShoppingCart> {
    @Override
    public ShoppingCart map(ShoppingCartEntity input) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(input.getId());
        shoppingCart.setReviewedOn(input.getReviewedOn());
        shoppingCart.setReviewedBy(input.getReviewedBy() != null ? input.getReviewedBy().getEmail() : null);
        shoppingCart.setCreatedOn(input.getCreatedOn());
        shoppingCart.setCreatedBy(input.getCreatedBy() != null ? input.getCreatedBy().getEmail() : null);
        shoppingCart.setStatus(input.getStatus());
        shoppingCart.setTotalPrice(input.getTotalPrice());

        return shoppingCart;
    }
}
