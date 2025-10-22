package com.eonis.demo.core.service.impl;

import com.eonis.demo.core.mapper.CartItemMapper;
import com.eonis.demo.core.mapper.ShoppingCartMapper;
import com.eonis.demo.core.model.CartItem;
import com.eonis.demo.core.model.ReviewCart;
import com.eonis.demo.core.model.ShoppingCart;
import com.eonis.demo.core.service.ShoppingCartService;
import com.eonis.demo.core.service.UserHelper;
import com.eonis.demo.persistence.entity.ShoppingCartEntity;
import com.eonis.demo.persistence.entity.UserEntity;
import com.eonis.demo.persistence.enums.CartStatus;
import com.eonis.demo.persistence.jpa_repository.CartItemRepository;
import com.eonis.demo.persistence.jpa_repository.ShoppingCartRepository;
import com.eonis.demo.persistence.jpa_repository.UserRepository;
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
    private final UserRepository userRepository;
    private final ShoppingCartRepository cartRepository;

    @Override
    public ShoppingCartEntity getOrCreateActiveCart(String email) {
        return getCart(email);
    }

    @Override
    public ShoppingCart getCartForUser() {
        String email = UserHelper.getLoggedInUserEmail();
        ShoppingCartEntity cartEntity = getExistingCartWithDetails(email);
        ShoppingCart cart = mapper.map(cartEntity);

        List<CartItem> items = itemMapper.map(cartEntity.getItems());
        cart.setItems(items);
        return cart;
    }

    @Override
    public void submitForReview(ShoppingCart cart) {
        ShoppingCartEntity cartEntity = cartRepository.getReferenceById(cart.getId());
        cartEntity.setStatus(CartStatus.SUBMITTED);
        cartRepository.save(cartEntity);
    }

    @Override
    public ShoppingCart review(ReviewCart reviewCart) {
        UserEntity reviewer = userRepository.findByEmail(reviewCart.getReviewerEmail())
                .orElseThrow(() -> new EntityNotFoundException("Reviewer not found"));

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

    @Override
    public ShoppingCart getCartById(Long id) {
        ShoppingCartEntity entity = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));

        ShoppingCart cart = mapper.map(entity);
        cart.setItems(itemMapper.map(entity.getItems()));

        return cart;
    }

    @Override
    public void save(ShoppingCartEntity cart) {
        cartRepository.save(cart);
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
     * Returns the active cart for a user with all details,
     * but does NOT create a new one if none exists.
     *
     * @param email user's email
     * @return existing cart or throws EntityNotFoundException
     */
    private ShoppingCartEntity getExistingCartWithDetails(String email) {
        return cartRepository.findByCreatedBy_EmailAndStatusFetchAll(email, CartStatus.ACTIVE)
                .orElseThrow(() -> new EntityNotFoundException("No active cart found for user: " + email));
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
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ShoppingCartEntity newCart = new ShoppingCartEntity();
        newCart.setCreatedBy(user);
        newCart.setStatus(CartStatus.ACTIVE);
        newCart.setCreatedOn(LocalDateTime.now());

        return cartRepository.save(newCart);
    }

}
