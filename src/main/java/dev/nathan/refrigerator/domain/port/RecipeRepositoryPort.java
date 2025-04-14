package dev.nathan.refrigerator.domain.port;

import dev.nathan.refrigerator.domain.model.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepositoryPort {
    Recipe save(Recipe recipe);

    Optional<Recipe> findById(Long id);

    List<Recipe> findAll();

    void deleteById(Long id);
}