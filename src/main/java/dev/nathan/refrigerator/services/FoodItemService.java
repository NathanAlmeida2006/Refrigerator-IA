package dev.nathan.refrigerator.services;

import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;

import java.util.List;

public interface FoodItemService {
    FoodItemResponseDTO createFoodItem(FoodItemRequestDTO requestDTO);

    List<FoodItemResponseDTO> findAllFoodItems();

    FoodItemResponseDTO getFoodItemById(Long id); // Implicitly throws if not found

    FoodItemResponseDTO updateFoodItem(Long id, FoodItemRequestDTO requestDTO); // Implicitly throws if not found

    void deleteFoodItem(Long id); // Implicitly throws if not found (or is idempotent)

    String suggestRecipe(); // Returns String or throws AI/runtime exception
}