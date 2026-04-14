package com.uwlFood.api.controller;

import com.uwlFood.api.dto.ApiResponseDto;
import com.uwlFood.api.dto.FoodItemReqDto;
import com.uwlFood.api.dto.FoodItemResDto;
import com.uwlFood.api.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/food-item")
@RestController
public class FoodItemController {
    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<FoodItemResDto>>> getFoodItems() {
        List<FoodItemResDto> response = foodItemService.getAllFoodItem();
        return ResponseEntity.ok(
                new ApiResponseDto<>(true, response, "Food item fetched successfully")
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto<FoodItemResDto>> getFoodItemById(@PathVariable Long id) {
        FoodItemResDto response = foodItemService.getFoodItemById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(true, response, "Food item by id")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<FoodItemResDto>> saveFoodItem(@RequestBody FoodItemReqDto req) {
        FoodItemResDto response = foodItemService.saveFoodItem(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(true, response, "Food item added successfully")
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseDto<FoodItemResDto>> updateFoodItem(@PathVariable Long id,@RequestBody FoodItemReqDto req) {
        FoodItemResDto response = foodItemService.updateFoodItem(id, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponseDto<>(true, response, "Food item updated successfully")
        );
    }


    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponseDto<>(true, "Food item deleted successfully")
        );
    }
}
