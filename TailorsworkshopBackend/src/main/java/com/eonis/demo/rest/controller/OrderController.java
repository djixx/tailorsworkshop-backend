package com.eonis.demo.rest.controller;

import com.eonis.demo.core.model.CreateOrder;
import com.eonis.demo.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{productId}")
    public ResponseEntity<String> makeOrder(
            @PathVariable Long productId,
            @RequestBody CreateOrder request
    ) {
        try {
            orderService.save(productId, request.getSelectedChoiceMap());
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}
