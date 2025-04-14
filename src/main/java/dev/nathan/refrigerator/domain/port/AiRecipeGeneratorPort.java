package dev.nathan.refrigerator.domain.port;

import dev.nathan.refrigerator.domain.model.Recipe;

import java.util.List;

public interface AiRecipeGeneratorPort {
    Recipe generateRecipe(List<String> ingredients);
}