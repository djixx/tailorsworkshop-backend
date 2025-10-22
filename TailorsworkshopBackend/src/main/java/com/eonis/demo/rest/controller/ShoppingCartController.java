package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.CreateOrder;
import com.eonis.demo.core.model.ReviewCart;
import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.core.service.OrderService;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.persistence.enums.CartStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {


    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<Object> addOrderItem(
            @PathVariable Long productId,
            @RequestBody CreateOrder request
    ) {
        try {
            CartItem savedItem = orderService.save(productId, request.getSelectedChoiceMap(), request.getEmail());
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> getShoppingCart(@PathVariable String email) {
        try {
            ShoppingCart cart = shoppingCartService.getCartByEmail(email);
            return ResponseEntity.ok(cart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Nema aktivne korpe za korisnika: " + email));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update/{email}")
    public ResponseEntity<Object> updateShoppingCart(@PathVariable String email, @RequestBody ShoppingCart cart) {
        ResponseEntity<Object> validationResponse = validateRequestUser(email, cart);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            ShoppingCart updatedCart = shoppingCartService.update(cart);
            return ResponseEntity.ok(updatedCart);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit/{email}")
    public ResponseEntity<Object> submitCart(@PathVariable String email) {
        try {
            ShoppingCart cart = shoppingCartService.getCartByEmail(email);
            if (cart == null) {
                return new ResponseEntity<>("Cart not found for user: " + email, HttpStatus.NOT_FOUND);
            }
            shoppingCartService.submitForReview(cart);
            return ResponseEntity.ok("Cart submitted successfully for user: " + email);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Object> validateRequestUser(String email, ShoppingCart cart) {
        if (!Objects.equals(email, cart.getCreatedBy())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "You are not authorized to update this shopping cart."));
        }
        return null;
    }

    @PostMapping("/review")
    public ResponseEntity<Object> reviewCart(@RequestBody ReviewCart reviewCart) {
        try {
            ShoppingCart cart = shoppingCartService.review(reviewCart);
            return ResponseEntity.ok(cart);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(value = "status", required = false) CartStatus status) {
        try {
            List<ShoppingCart> cartList = shoppingCartService.getAll(status);
            return ResponseEntity.ok(cartList);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long id) {
        ShoppingCart cart = shoppingCartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

}
