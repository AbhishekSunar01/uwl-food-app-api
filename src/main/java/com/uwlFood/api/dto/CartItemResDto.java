package com.uwlFood.api.dto;

import com.uwlFood.api.entity.CartItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResDto {
    private long cartItemId;
    private long foodId;
    private String foodName;
    private String foodCategory;
    private BigDecimal foodPrice;
    private String imageUrl;
    private int quantity;

    public CartItemResDto(CartItem cartItem) {
        this.cartItemId = cartItem.getCartItemId();
        this.foodId = cartItem.getFoodItem().getFoodId();
        this.foodName = cartItem.getFoodItem().getFoodName();
        this.foodCategory = cartItem.getFoodItem().getFoodCategory();
        this.foodPrice = cartItem.getFoodItem().getFoodPrice();
        this.imageUrl = cartItem.getFoodItem().getImageUrl();
        this.quantity = cartItem.getQuantity();
    }
}
