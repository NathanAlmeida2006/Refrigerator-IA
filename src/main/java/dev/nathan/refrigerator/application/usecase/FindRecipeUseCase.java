package dev.nathan.refrigerator.application.usecase;

import dev.nathan.refrigerator.domain.exception.RecipeNotFoundException;
import dev.nathan.refrigerator.domain.model.Recipe;
import dev.nathan.refrigerator.domain.port.RecipeRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindRecipeUseCase {
    private final RecipeRepositoryPort recipeRepository;

    public FindRecipeUseCase(RecipeRepositoryPort recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }
}