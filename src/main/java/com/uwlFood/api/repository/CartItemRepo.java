package com.uwlFood.api.repository;

import com.uwlFood.api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByClerkUserId(String clerkUserId);

    Optional<CartItem> findByClerkUserIdAndFoodItem_FoodId(String clerkUserId, Long foodId);

    void deleteByClerkUserId(String clerkUserId);
}
