package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CartItemMapper;
import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.SaveOrder;
import com.eonis.demo.core.service.OrderService;
import com.eonis.demo.core.service.OrderValidationService;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.core.service.UserHelper;
import com.eonis.demo.persistence.entity.CartItemEntity;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import com.eonis.demo.persistence.entity.ProductEntity;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.jpa_repository.CartItemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.io.SerializationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ObjectMapper objectMapper;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartService cartService;
    private final ProductServiceImpl productService;
    private final OrderValidationService orderValidationService;
    private final CartItemRepository cartItemRepository;

    /**
     * vrati veliku mapu, product withOptions je sa svim opcijama kad nije selektovano,
     * ja od prodact optios isto pravim mapui saljem te opcije i sta je selektovano da bi validirala da li je ono sto je selektovano dozvoleno
     * u bazi imam sta je dozvoljeno i validiram taj request , ako je
     */
    public CartItem save(Long productId, Map<String, String> selectedChoiceMap) {
        ProductEntity product = productService.findWithOptions(productId);
        Set<OptionTypeEntity> productOptionTypes = product.getOptionTypes();

        orderValidationService.validate(productOptionTypes, selectedChoiceMap);

        ShoppingCartEntity usersCart = cartService.getOrCreateActiveCart(UserHelper.getLoggedInUserEmail());
        CartItemEntity item = findInCart(usersCart, productId, selectedChoiceMap);

        try {
            return saveItem(selectedChoiceMap, item, product, usersCart);

        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing JSON");

        }
    }

    @Override
    public void update(Long itemId, SaveOrder request) {
        try {
            CartItemEntity item = cartItemRepository.getReferenceById(itemId);
            Boolean shouldDelete = request.getDelete();

            if (Boolean.TRUE.equals(shouldDelete)) {
                cartItemRepository.delete(item);

            } else {
                item.setQuantity(request.getQuantity());
                item.setOptionsJson(objectMapper.writeValueAsString(request.getSelectedChoiceMap()));
                item.setTotalPrice(item.getProductPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
                cartItemRepository.save(item);

            }

            ShoppingCartEntity usersCart = cartService.getOrCreateActiveCart(UserHelper.getLoggedInUserEmail());
            updateCart(usersCart);

        } catch (JsonProcessingException e) {
            throw new SerializationException("Error serializing JSON");

        }
    }

    private void updateCart(ShoppingCartEntity cart) {
        try {
            BigDecimal total = cart.getItems().stream()
                    .map(CartItemEntity::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            cart.setTotalPrice(total);
            cartService.save(cart);

        } catch (Exception e) {
            throw new RuntimeException("Unable to save cart: " + e.getMessage());
        }
    }

    private CartItem saveItem(Map<String, String> selectedChoiceMap, CartItemEntity item, ProductEntity product,
                              ShoppingCartEntity usersCart) throws JsonProcessingException {
        if (item != null) {
            item.setQuantity(item.getQuantity() + 1);
            item.setTotalPrice(item.getTotalPrice().add(product.getPrice()));

        } else {
            item = new CartItemEntity();
            item.setProduct(product);
            item.setProductName(product.getName());
            item.setQuantity(1);
            item.setProductPrice(product.getPrice());
            item.setTotalPrice(product.getPrice());
            item.setOptionsJson(objectMapper.writeValueAsString(selectedChoiceMap));
            item.setCart(usersCart);
        }

        CartItemEntity savedItem = cartItemRepository.save(item);
        return cartItemMapper.map(savedItem);
    }

    private CartItemEntity findInCart(ShoppingCartEntity usersCart, Long productId, Map<String, String> selectedChoiceMap) {
        if (usersCart.getItems() == null || usersCart.getItems().isEmpty()) return null;

        return usersCart.getItems().stream()
                .filter(item -> {
                    if (!item.getProduct().getId().equals(productId)) return false;

                    try {
                        Map<String, String> existingMap = objectMapper.readValue(item.getOptionsJson(),
                                new TypeReference<>() {
                                });

                        return existingMap.equals(selectedChoiceMap);
                    } catch (JsonProcessingException e) {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);
    }
}
