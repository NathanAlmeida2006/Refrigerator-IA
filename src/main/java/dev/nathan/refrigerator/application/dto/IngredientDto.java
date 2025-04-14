package dev.nathan.refrigerator.application.dto;

import lombok.Data;

@Data
public class IngredientDto {
    private Long id;
    private String name;
    private double quantity;
    private String unit;
}