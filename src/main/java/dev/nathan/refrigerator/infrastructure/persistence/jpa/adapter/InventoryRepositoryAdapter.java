package dev.nathan.refrigerator.infrastructure.persistence.jpa.adapter;

import dev.nathan.refrigerator.domain.model.Inventory;
import dev.nathan.refrigerator.domain.port.InventoryRepositoryPort;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.InventoryEntity;
import dev.nathan.refrigerator.infrastructure.persistence.jpa.repository.InventoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .build();
        InventoryEntity savedEntity = inventoryJpaRepository.save(entity);
        return Inventory.builder()
                .id(savedEntity.getId())
                .ingredients(inventory.getIngredients())
                .build();
    }

    @Override
    public Optional<Inventory> findById(Long id) {
        return inventoryJpaRepository.findById(id)
                .map(entity -> Inventory.builder()
                        .id(entity.getId())
                        .build());
    }
}