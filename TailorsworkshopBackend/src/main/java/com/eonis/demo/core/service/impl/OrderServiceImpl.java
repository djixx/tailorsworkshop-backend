package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.service.OrderService;
import com.eonis.demo.core.service.OrderValidationService;
import com.eonis.demo.persistence.entity.*;
import com.eonis.demo.persistence.jpa_repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderValidationService orderValidationService;

    @Override
    public void save(Long productId, Map<String, Map<Long, String>> selectedChoiceMap) {
        ProductEntity product = productRepository.findWithOptions(productId);
        Set<OptionTypeEntity> productOptionTypes = product.getOptionTypes();

        Map<String, Set<String>> validOptionsMap = productOptionTypes.stream()
                .collect(Collectors.toMap(
                        OptionTypeEntity::getName,
                        ot -> ot.getOptionChoices().stream()
                                .map(OptionChoiceEntity::getName)
                                .collect(Collectors.toSet())
                ));


        orderValidationService.validate(validOptionsMap, selectedChoiceMap);

        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProduct(product);
        cartItemEntity.setProductName(product.getName());
        cartItemEntity.setUnitPrice(product.getPrice());
        cartItemEntity.setQuantity(1);

        //check if user has active cart - cart status - has one until its ordered


    }

}
