package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CartItemMapper;
import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.service.OrderService;
import com.eonis.demo.core.service.OrderValidationService;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.persistence.entity.CartItemEntity;
import com.eonis.demo.persistence.entity.OptionTypeEntity;
import com.eonis.demo.persistence.entity.ProductEntity;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.jpa_repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CartItemMapper cartItemMapper;
    private final ShoppingCartService cartService;
    private final ProductServiceImpl productService;
    private final OrderValidationService orderValidationService;
    private final CartItemRepository cartItemRepository;

    public CartItem save(Long productId, Map<String, Map<Long, String>> selectedChoiceMap, String email) {
        ProductEntity product = productService.findWithOptions(productId);
        Set<OptionTypeEntity> productOptionTypes = product.getOptionTypes();

        orderValidationService.validate(productOptionTypes, selectedChoiceMap);

        ShoppingCartEntity usersCart = cartService.getOrCreateActiveCart(email);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(product);
        cartItemEntity.setProductName(product.getName());
        cartItemEntity.setUnitPrice(product.getPrice());
        cartItemEntity.setOptionsJson(selectedChoiceMap.toString());
        cartItemEntity.setQuantity(1);
        cartItemEntity.setCart(usersCart);

        CartItemEntity savedItem = cartItemRepository.save(cartItemEntity);
        return cartItemMapper.map(savedItem);
    }


}
