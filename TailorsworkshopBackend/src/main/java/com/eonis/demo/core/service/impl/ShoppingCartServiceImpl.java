package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CartItemMapper;
import com.eonis.demo.core.mapper.ShoppingCartMapper;
import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.ReviewCart;
import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.persistence.entity.CartItemEntity;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.entity.UserEntity;
import com.eonis.demo.persistence.enums.CartStatus;
import com.eonis.demo.persistence.jpa_repository.CartItemRepository;
import com.eonis.demo.persistence.jpa_repository.ShoppingCartRepository;
import com.eonis.demo.persistence.jpa_repository.UsersRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemMapper itemMapper;
    private final ShoppingCartMapper mapper;
    private final UsersRepository usersRepository;
    private final ShoppingCartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

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
     * Updates the shopping cart: sets quantities for existing items,
     * removes items not present in the update list, and persists changes.
     *
     * @param cart the DTO containing updated items
     * @return the updated ShoppingCart DTO
     */
    @Override
    @Transactional
    public ShoppingCart update(ShoppingCart cart) {
        ShoppingCartEntity cartEntity = cartRepository.getReferenceById(cart.getId());

        removeMissingItems(cartEntity, cart.getItems());
        updateQuantities(cartEntity, cart.getItems());

        ShoppingCartEntity updatedCart = cartRepository.save(cartEntity);
        return mapper.map(updatedCart);
    }

    /**
     * Removes items from the cart that are not present in the update list.
     *
     * @param cartEntity the entity being updated
     * @param items      the list of items from the DTO
     */
    private void removeMissingItems(ShoppingCartEntity cartEntity, List<CartItem> items) {
        Set<Long> updatedIds = items.stream()
                .map(CartItem::getId)
                .collect(Collectors.toSet());
        List<CartItemEntity> cartItems = cartEntity.getItems();

        List<CartItemEntity> toRemove = cartItems.stream()
                .filter(item -> !updatedIds.contains(item.getId()))
                .toList();

        cartItems.removeAll(toRemove);
        cartItemRepository.deleteAll(toRemove);
    }

    /**
     * Updates quantities of items that exist in both the cart and the update list.
     *
     * @param cartEntity the entity being updated
     * @param items      the list of items from the DTO
     */
    private void updateQuantities(ShoppingCartEntity cartEntity, List<CartItem> items) {
        Map<Long, Integer> itemQuantities = items.stream()
                .collect(Collectors.toMap(CartItem::getId, CartItem::getQuantity));

        cartEntity.getItems().forEach(savedItem -> {
            Integer newQuantity = itemQuantities.get(savedItem.getId());
            if (newQuantity != null) {
                savedItem.setQuantity(newQuantity);
            }
        });
    }

    @Override
    public void submitForReview(ShoppingCart cart) {
        ShoppingCartEntity cartEntity = cartRepository.getReferenceById(cart.getId());
        cartEntity.setStatus(CartStatus.SUBMITTED);
        cartRepository.save(cartEntity);
    }

    @Override
    public ShoppingCart review(ReviewCart reviewCart) {
        UserEntity reviewer = usersRepository.findByEmail(reviewCart.getReviewerEmail());

        ShoppingCartEntity cartEntity = cartRepository.getReferenceById(reviewCart.getCartId());
        cartEntity.setStatus(reviewCart.getStatus());
        cartEntity.setReviewedBy(reviewer);
        cartEntity.setReviewedOn(LocalDateTime.now());

        ShoppingCartEntity savedCart = cartRepository.save(cartEntity);
        return mapper.map(savedCart);
    }

    @Override
    public List<ShoppingCart> getAll(CartStatus status) {
        List<ShoppingCartEntity> carts;
        if (status != null) {
            carts = cartRepository.findAllByStatus(status);
        } else {
            carts = cartRepository.findAll();
        }
        return mapper.map(carts);
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
