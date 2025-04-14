package dev.nathan.refrigerator.adapters.api.dto;

import lombok.Data;

@Data
public class RecipeRequest {
    private String name;
    private String instructions;
}