package dev.nathan.refrigerator.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class InventoryDto {
    private Long id;
    private List<IngredientDto> ingredients;
}