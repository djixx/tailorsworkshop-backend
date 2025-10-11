package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.core.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{email}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable String email) {

        try {
            ShoppingCart cart = shoppingCartService.getCartByEmail(email);
            return ResponseEntity.ok(cart);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
