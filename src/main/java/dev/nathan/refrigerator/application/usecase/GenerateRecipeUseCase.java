package dev.nathan.refrigerator.application.usecase;

import dev.nathan.refrigerator.domain.model.Ingredient;
import dev.nathan.refrigerator.domain.model.Inventory;
import dev.nathan.refrigerator.domain.model.Recipe;
import dev.nathan.refrigerator.domain.port.AiRecipeGeneratorPort;
import dev.nathan.refrigerator.domain.port.InventoryRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenerateRecipeUseCase {
    private final InventoryRepositoryPort inventoryRepository;
    private final AiRecipeGeneratorPort aiRecipeGenerator;

    public GenerateRecipeUseCase(InventoryRepositoryPort inventoryRepository, AiRecipeGeneratorPort aiRecipeGenerator) {
        this.inventoryRepository = inventoryRepository;
        this.aiRecipeGenerator = aiRecipeGenerator;
    }

    public Recipe execute(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        List<String> ingredients = inventory.getIngredients() != null
                ? inventory.getIngredients().stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList())
                : Collections.emptyList();
        return aiRecipeGenerator.generateRecipe(ingredients);
    }
}