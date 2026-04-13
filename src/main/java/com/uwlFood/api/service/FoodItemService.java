package com.uwlFood.api.service;

import com.uwlFood.api.dto.FoodItemResDto;
import com.uwlFood.api.entity.FoodItem;
import com.uwlFood.api.repository.FoodItemRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    private final FoodItemRepo foodItemRepo;

    public FoodItemService(FoodItemRepo foodItemRepo) {
        this.foodItemRepo = foodItemRepo;
    }

    public List<FoodItemResDto> getAllFoodItem() {
        return foodItemRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public FoodItemResDto mapToDto(FoodItem foodItem) {
        return new FoodItemResDto(foodItem);
    }
}
