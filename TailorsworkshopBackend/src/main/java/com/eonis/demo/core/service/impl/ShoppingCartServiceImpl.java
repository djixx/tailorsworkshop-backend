package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CartItemMapper;
import com.eonis.demo.core.mapper.ShoppingCartMapper;
import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.entity.UserEntity;
import com.eonis.demo.persistence.enums.CartStatus;
import com.eonis.demo.persistence.jpa_repository.ShoppingCartRepository;
import com.eonis.demo.persistence.jpa_repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemMapper itemMapper;
    private final ShoppingCartMapper mapper;
    private final UsersRepository usersRepository;
    private final ShoppingCartRepository cartRepository;

    @Override
    public ShoppingCart getCartByEmail(String email) {
        ShoppingCartEntity cartEntity = getCartWithDetails(email);
        ShoppingCart cart = mapper.map(cartEntity);

        List<CartItem> items = itemMapper.map(cartEntity.getItems());
        cart.setItems(items);
        return cart;
    }

    @Override
    public ShoppingCartEntity getOrCreateActiveCart(String email) {
        return getCart(email);
    }

    /**
     * Returns the active cart for a user with all related details fetched.
     * Creates a new one if none exists.
     *
     * @param email user's email
     * @return active cart with details or a newly created one
     */
    private ShoppingCartEntity getCartWithDetails(String email) {
        Optional<ShoppingCartEntity> cartDetails = cartRepository.findByCreatedBy_EmailAndStatusFetchAll(email, CartStatus.ACTIVE);
        return cartDetails.orElseGet(() -> createNewCart(email));
    }

    /**
     * Returns the active cart for a user.
     * Creates a new one if none exists.
     *
     * @param email user's email
     * @return active or newly created cart
     */
    private ShoppingCartEntity getCart(String email) {
        Optional<ShoppingCartEntity> existingCart =
                cartRepository.findByCreatedBy_EmailAndStatus(email, CartStatus.ACTIVE);

        return existingCart.orElseGet(() -> createNewCart(email));
    }

    /**
     * Creates and saves a new active cart for the user.
     *
     * @param email user's email
     * @return newly created cart
     * @throws EntityNotFoundException if user is not found
     */
    private ShoppingCartEntity createNewCart(String email) {
        UserEntity user = Optional.ofNullable(usersRepository.findByEmail(email))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingCartEntity newCart = new ShoppingCartEntity();
        newCart.setCreatedBy(user);
        newCart.setStatus(CartStatus.ACTIVE);
        newCart.setCreatedOn(LocalDateTime.now());

        return cartRepository.save(newCart);
    }
}
