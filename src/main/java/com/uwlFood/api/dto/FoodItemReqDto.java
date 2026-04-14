package com.uwlFood.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodItemReqDto {
    private String foodName;
    private String foodCategory;
    private BigDecimal foodPrice;
}
