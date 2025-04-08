package dev.nathan.refrigerator.converters;

import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;
import dev.nathan.refrigerator.models.FoodItem;

public interface FoodItemConverter {
    FoodItem toEntity(FoodItemRequestDTO dto);

    FoodItemResponseDTO toResponseDTO(FoodItem foodItem);
}