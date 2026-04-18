package com.uwlFood.api.dto;

import lombok.Data;

@Data
public class CartItemReqDto {
    private Long foodItemId;
    private int quantity;
}
