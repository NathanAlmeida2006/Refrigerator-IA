package dev.nathan.refrigerator.application.usecase;

import dev.nathan.refrigerator.domain.model.Inventory;
import dev.nathan.refrigerator.domain.port.InventoryRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ManageInventoryUseCase {
    private final InventoryRepositoryPort inventoryRepository;

    public ManageInventoryUseCase(InventoryRepositoryPort inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory addInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory getInventory(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }
}