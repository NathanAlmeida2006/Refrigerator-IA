package dev.nathan.refrigerator.infrastructure.persistence.jpa.adapter;

import dev.nathan.refrigerator.domain.model.Ingredient;
import dev.nathan.refrigerator.domain.model.Inventory;
import dev.nathan.refrigerator.domain.port.InventoryRepositoryPort;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.IngredientEntity;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.InventoryEntity;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.repository.InventoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InventoryRepositoryAdapter implements InventoryRepositoryPort {
    private final InventoryJpaRepository inventoryJpaRepository;

    public InventoryRepositoryAdapter(InventoryJpaRepository inventoryJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
    }

    @Override
    public Inventory save(Inventory inventory) {
        InventoryEntity entity = InventoryEntity.builder()
                .id(inventory.getId())
                .ingredients(inventory.getIngredients() != null
                        ? inventory.getIngredients().stream()
                        .map(ingredient -> IngredientEntity.builder()
                                .id(ingredient.getId())
                                .name(ingredient.getName())
                                .quantity(ingredient.getQuantity())
                                .unit(ingredient.getUnit())
                                .build())
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
        InventoryEntity savedEntity = inventoryJpaRepository.save(entity);
        return Inventory.builder()
                .id(savedEntity.getId())
                .ingredients(savedEntity.getIngredients() != null
                        ? savedEntity.getIngredients().stream()
                        .map(ingredientEntity -> Ingredient.builder()
                                .id(ingredientEntity.getId())
                                .name(ingredientEntity.getName())
                                .quantity(ingredientEntity.getQuantity())
                                .unit(ingredientEntity.getUnit())
                                .build())
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryJpaRepository.findById(id)
                .map(entity -> Inventory.builder()
                        .id(entity.getId())
                        .ingredients(entity.getIngredients() != null
                                ? entity.getIngredients().stream()
                                .map(ingredientEntity -> Ingredient.builder()
                                        .id(ingredientEntity.getId())
                                        .name(ingredientEntity.getName())
                                        .quantity(ingredientEntity.getQuantity())
                                        .unit(ingredientEntity.getUnit())
                                        .build())
                                .collect(Collectors.toList())
                                : new ArrayList<>())
                        .build());
    }
}