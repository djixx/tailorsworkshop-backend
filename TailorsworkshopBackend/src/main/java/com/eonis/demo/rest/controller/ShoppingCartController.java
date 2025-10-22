package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.ReviewCart;
import com.eonis.demo.core.model.SaveOrder;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {


    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<Object> addOrderItem(
            @PathVariable Long productId,
            @RequestBody SaveOrder request
    ) {
        try {
            CartItem savedItem = orderService.save(productId, request.getSelectedChoiceMap());
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{itemId}")
    public ResponseEntity<Object> updateOrderItem(
            @PathVariable Long itemId,
            @RequestBody SaveOrder request) {
        try {
            orderService.update(itemId, request);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/all")
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

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getCartById(@PathVariable Long id) {
        ShoppingCart cart = shoppingCartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping
    public ResponseEntity<Object> getShoppingCart() {
        try {
            ShoppingCart cart = shoppingCartService.getCartForUser();
            return ResponseEntity.ok(cart);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Nema aktivne korpe za korisnika "));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> submitCart() {
        try {
            ShoppingCart cart = shoppingCartService.getCartForUser();
            if (cart == null) {
                return new ResponseEntity<>("Cart not found for user ", HttpStatus.NOT_FOUND);
            }
            shoppingCartService.submitForReview(cart);
            return ResponseEntity.ok("Cart submitted successfully for user");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
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

}
