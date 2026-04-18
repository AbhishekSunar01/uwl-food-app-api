package com.uwlFood.api.controller;

import com.uwlFood.api.dto.ApiResponseDto;
import com.uwlFood.api.dto.CartItemReqDto;
import com.uwlFood.api.dto.CartItemResDto;
import com.uwlFood.api.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/cart")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<CartItemResDto>>> getCart(
            @RequestHeader("X-User-Id") String clerkUserId) {

        List<CartItemResDto> response = cartService.getCartItems(clerkUserId);
        return ResponseEntity.ok(
                new ApiResponseDto<>(true, response, "Cart fetched successfully")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<CartItemResDto>> addToCart(
            @RequestHeader("X-User-Id") String clerkUserId,
            @RequestBody CartItemReqDto req) {

        CartItemResDto response = cartService.addToCart(clerkUserId, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(true, response, "Item added to cart")
        );
    }

    @PutMapping("{cartItemId}")
    public ResponseEntity<ApiResponseDto<CartItemResDto>> updateCartItem(
            @RequestHeader("X-User-Id") String clerkUserId,
            @PathVariable Long cartItemId,
            @RequestParam int quantity) {

        CartItemResDto response = cartService.updateCartItem(clerkUserId, cartItemId, quantity);
        return ResponseEntity.ok(
                new ApiResponseDto<>(true, response, "Cart item updated")
        );
    }

    @DeleteMapping("{cartItemId}")
    public ResponseEntity<ApiResponseDto<Void>> removeCartItem(
            @RequestHeader("X-User-Id") String clerkUserId,
            @PathVariable Long cartItemId) {

        cartService.removeCartItem(clerkUserId, cartItemId);
        return ResponseEntity.ok(
                new ApiResponseDto<>(true, "Cart item removed")
        );
    }

    @DeleteMapping
    public ResponseEntity<ApiResponseDto<Void>> clearCart(
            @RequestHeader("X-User-Id") String clerkUserId) {

        cartService.clearCart(clerkUserId);
        return ResponseEntity.ok(
                new ApiResponseDto<>(true, "Cart cleared")
        );
    }

}
