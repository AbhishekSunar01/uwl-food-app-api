package com.uwlFood.api.dto;

import com.uwlFood.api.entity.FoodItem;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FoodItemResDto {
    private long foodId;
    private String foodName;
    private String foodCategory;
    private BigDecimal foodPrice;

    public FoodItemResDto(FoodItem foodItem) {
        this.foodId = foodItem.getFoodId();
        this.foodName = foodItem.getFoodName();
        this.foodCategory = foodItem.getFoodCategory();
        this.foodPrice = foodItem.getFoodPrice();
    }
}
