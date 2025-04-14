package dev.nathan.refrigerator.infrastructure.persistence.jpa.adapter;

import dev.nathan.refrigerator.domain.model.Recipe;
import dev.nathan.refrigerator.domain.port.RecipeRepositoryPort;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.RecipeEntity;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.repository.RecipeJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RecipeRepositoryAdapter implements RecipeRepositoryPort {
    private final RecipeJpaRepository recipeJpaRepository;

    public RecipeRepositoryAdapter(RecipeJpaRepository recipeJpaRepository) {
        this.recipeJpaRepository = recipeJpaRepository;
    }

    @Override
    public Recipe save(Recipe recipe) {
        RecipeEntity entity = RecipeEntity.builder()
                .id(recipe.getId())
                .name(recipe.getName())
                .instructions(recipe.getInstructions())
                .build();
        RecipeEntity savedEntity = recipeJpaRepository.save(entity);
        return Recipe.builder()
                .id(savedEntity.getId())
                .name(savedEntity.getName())
                .instructions(savedEntity.getInstructions())
                .build();
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return recipeJpaRepository.findById(id)
                .map(entity -> Recipe.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .instructions(entity.getInstructions())
                        .build());
    }

    @Override
    public List<Recipe> findAll() {
        return recipeJpaRepository.findAll().stream()
                .map(entity -> Recipe.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .instructions(entity.getInstructions())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        recipeJpaRepository.deleteById(id);
    }
}