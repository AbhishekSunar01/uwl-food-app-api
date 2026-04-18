package com.uwlFood.api.service;

import com.uwlFood.api.dto.CartItemReqDto;
import com.uwlFood.api.dto.CartItemResDto;
import com.uwlFood.api.entity.CartItem;
import com.uwlFood.api.entity.FoodItem;
import com.uwlFood.api.repository.CartItemRepo;
import com.uwlFood.api.repository.FoodItemRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartItemRepo cartItemRepo;
    private final FoodItemRepo foodItemRepo;

    public CartService(CartItemRepo cartItemRepo, FoodItemRepo foodItemRepo) {
        this.cartItemRepo = cartItemRepo;
        this.foodItemRepo = foodItemRepo;
    }

    public List<CartItemResDto> getCartItems(String clerkUserId) {
        return cartItemRepo.findByClerkUserId(clerkUserId)
                .stream()
                .map(CartItemResDto::new)
                .toList();
    }

    public CartItemResDto addToCart(String clerkUserId, CartItemReqDto req) {
        FoodItem foodItem = foodItemRepo.findById(req.getFoodItemId())
                .orElseThrow(() -> new RuntimeException("Food item not found"));


        CartItem cartItem = cartItemRepo
                .findByClerkUserIdAndFoodItem_FoodId(clerkUserId, req.getFoodItemId())
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + req.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setClerkUserId(clerkUserId);
            cartItem.setFoodItem(foodItem);
            cartItem.setQuantity(req.getQuantity());
        }

        return new CartItemResDto(cartItemRepo.save(cartItem));
    }

    public CartItemResDto updateCartItem(String clerkUserId, Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getClerkUserId().equals(clerkUserId)) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantity <= 0) {
            cartItemRepo.delete(cartItem);
            return null;
        }

        cartItem.setQuantity(quantity);
        return new CartItemResDto(cartItemRepo.save(cartItem));
    }

    public void removeCartItem(String clerkUserId, Long cartItemId) {
        CartItem cartItem = cartItemRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getClerkUserId().equals(clerkUserId)) {
            throw new RuntimeException("Unauthorized");
        }

        cartItemRepo.delete(cartItem);
    }

    @Transactional
    public void clearCart(String clerkUserId) {
        cartItemRepo.deleteByClerkUserId(clerkUserId);
    }

}
