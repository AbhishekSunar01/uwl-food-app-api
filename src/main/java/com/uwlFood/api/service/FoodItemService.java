package com.uwlFood.api.service;

import com.uwlFood.api.dto.FoodItemReqDto;
import com.uwlFood.api.dto.FoodItemResDto;
import com.uwlFood.api.entity.FoodItem;
import com.uwlFood.api.repository.FoodItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FoodItemService {
    private final FoodItemRepo foodItemRepo;
    private final S3Service s3Service;

    public FoodItemService(FoodItemRepo foodItemRepo, S3Service s3Service) {
        this.foodItemRepo = foodItemRepo;
        this.s3Service = s3Service;
    }

    public List<FoodItemResDto> getAllFoodItem() {
        return foodItemRepo.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public FoodItemResDto getFoodItemById(Long id) {
        FoodItem foodItem = foodItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Food item not found"));
        return mapToDto(foodItem);
    }

    public FoodItemResDto saveFoodItem(FoodItemReqDto req, MultipartFile image) {
        FoodItem item = new FoodItem();
        item.setFoodName(req.getFoodName());
        item.setFoodCategory(req.getFoodCategory());
        item.setFoodPrice(req.getFoodPrice());

        if (image != null && !image.isEmpty()) {
            String imageUrl = s3Service.uploadImage(image);
            item.setImageUrl(imageUrl);
        }

        FoodItem foodItem = foodItemRepo.save(item);
        return mapToDto(foodItem);
    }

    public FoodItemResDto updateFoodItem(Long id, FoodItemReqDto req, MultipartFile image) {
        FoodItem existingFoodItem = foodItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Food item not found"));
        existingFoodItem.setFoodName(req.getFoodName());
        existingFoodItem.setFoodPrice(req.getFoodPrice());
        existingFoodItem.setFoodCategory(req.getFoodCategory());

        if (image != null && !image.isEmpty()) {
            String imageUrl = s3Service.uploadImage(image);
            existingFoodItem.setImageUrl(imageUrl);
        }

        FoodItem updatedFoodItem = foodItemRepo.save(existingFoodItem);
        return mapToDto(updatedFoodItem);
    }

    public void deleteFoodItem(Long id) {
        FoodItem foodItem = foodItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Food item not found"));
        foodItemRepo.delete(foodItem);
    }

    public FoodItemResDto mapToDto(FoodItem foodItem) {
        return new FoodItemResDto(foodItem);
    }
}
