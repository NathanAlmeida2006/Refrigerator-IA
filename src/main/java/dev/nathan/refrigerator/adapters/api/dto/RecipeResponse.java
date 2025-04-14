package dev.nathan.refrigerator.adapters.api.dto;

import lombok.Data;

@Data
public class RecipeResponse {
    private Long id;
    private String name;
    private String instructions;
}