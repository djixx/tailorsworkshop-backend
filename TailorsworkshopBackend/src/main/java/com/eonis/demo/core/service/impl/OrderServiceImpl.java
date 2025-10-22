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

    public CartItem save(Long productId, Map<String, String> selectedChoiceMap, String email) {
        ProductEntity product = productService.findWithOptions(productId);//vrati veliku mapu, product withOptions je sa svim opcijama kad nije selektovano,
        //ja od prodact optios isto pravim mapui saljem te opcije i sta je selektovano da bi validirala da li je ono sto je selektovano dozvoleno
        //  u bazi imam sta je dozvoljeno i validiram taj request , ako je
        Set<OptionTypeEntity> productOptionTypes = product.getOptionTypes();

        orderValidationService.validate(productOptionTypes, selectedChoiceMap);

        ShoppingCartEntity usersCart = cartService.getOrCreateActiveCart(email);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(product);
        cartItemEntity.setProductName(product.getName());
        cartItemEntity.setQuantity(1);
        cartItemEntity.setProductPrice(product.getPrice());
        cartItemEntity.setTotalPrice(product.getPrice());
        cartItemEntity.setOptionsJson(selectedChoiceMap.toString());
        cartItemEntity.setCart(usersCart);

        CartItemEntity savedItem = cartItemRepository.save(cartItemEntity);
        return cartItemMapper.map(savedItem);
    }


}
