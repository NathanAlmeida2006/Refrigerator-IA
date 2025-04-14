package dev.nathan.refrigerator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private Long id;
    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
}