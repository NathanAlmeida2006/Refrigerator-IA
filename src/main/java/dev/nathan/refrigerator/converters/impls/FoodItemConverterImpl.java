package dev.nathan.refrigerator.converters.impls;

import dev.nathan.refrigerator.converters.FoodItemConverter;
import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;
import dev.nathan.refrigerator.models.FoodItem;
import org.springframework.stereotype.Component;

@Component
public class FoodItemConverterImpl implements FoodItemConverter {

    @Override
    public FoodItem toEntity(FoodItemRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        FoodItem foodItem = new FoodItem();
        foodItem.setName(dto.getName());
        foodItem.setCategory(dto.getCategory());
        foodItem.setAmount(dto.getAmount());
        foodItem.setExpirationDate(dto.getExpirationDate());
        return foodItem;
    }

    @Override
    public FoodItemResponseDTO toResponseDTO(FoodItem foodItem) {
        if (foodItem == null) {
            return null;
        }

        return new FoodItemResponseDTO(
                foodItem.getId(),
                foodItem.getName(),
                foodItem.getCategory(),
                foodItem.getAmount(),
                foodItem.getExpirationDate()
        );
    }
}