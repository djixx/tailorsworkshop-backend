package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.CreateOrder;
import com.eonis.demo.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;

    @PostMapping("/{productId}")
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

}
