package dev.nathan.refrigerator.adapters.api.controller;

import dev.nathan.refrigerator.application.dto.RecipeDto;
import dev.nathan.refrigerator.application.mapper.RecipeMapper;
import dev.nathan.refrigerator.application.usecase.FindRecipeUseCase;
import dev.nathan.refrigerator.application.usecase.GenerateRecipeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final FindRecipeUseCase findRecipeUseCase;
    private final GenerateRecipeUseCase generateRecipeUseCase;
    private final RecipeMapper recipeMapper = RecipeMapper.INSTANCE;

    public RecipeController(FindRecipeUseCase findRecipeUseCase, GenerateRecipeUseCase generateRecipeUseCase) {
        this.findRecipeUseCase = findRecipeUseCase;
        this.generateRecipeUseCase = generateRecipeUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Long id) {
        RecipeDto recipeDto = recipeMapper.toDto(findRecipeUseCase.findById(id));
        return ResponseEntity.ok(recipeDto);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        List<RecipeDto> recipes = findRecipeUseCase.findAll().stream()
                .map(recipeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/generate/{inventoryId}")
    public ResponseEntity<RecipeDto> generateRecipe(@PathVariable Long inventoryId) {
        RecipeDto recipeDto = recipeMapper.toDto(generateRecipeUseCase.execute(inventoryId));
        return ResponseEntity.ok(recipeDto);
    }
}