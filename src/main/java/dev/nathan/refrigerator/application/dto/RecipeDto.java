package dev.nathan.refrigerator.application.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipeDto {
    private Long id;
    private String name;
    private List<IngredientDto> ingredients;
    private String instructions;
}