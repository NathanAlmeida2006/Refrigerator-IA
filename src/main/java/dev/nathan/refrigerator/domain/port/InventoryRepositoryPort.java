package dev.nathan.refrigerator.domain.port;

import dev.nathan.refrigerator.domain.model.Inventory;

import java.util.Optional;

public interface InventoryRepositoryPort {
    Inventory save(Inventory inventory);

    Optional<Inventory> findById(Long id);
}