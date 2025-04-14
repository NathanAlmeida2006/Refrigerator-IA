package dev.nathan.refrigerator.adapters.api.controller;

import dev.nathan.refrigerator.application.dto.InventoryDto;
import dev.nathan.refrigerator.application.mapper.InventoryMapper;
import dev.nathan.refrigerator.application.usecase.ManageInventoryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    private final ManageInventoryUseCase manageInventoryUseCase;
    private final InventoryMapper inventoryMapper = InventoryMapper.INSTANCE;

    public InventoryController(ManageInventoryUseCase manageInventoryUseCase) {
        this.manageInventoryUseCase = manageInventoryUseCase;
    }

    @PostMapping
    public ResponseEntity<InventoryDto> addInventory(@RequestBody InventoryDto inventoryDto) {
        InventoryDto savedInventory = inventoryMapper.toDto(
                manageInventoryUseCase.addInventory(inventoryMapper.toEntity(inventoryDto))
        );
        return ResponseEntity.ok(savedInventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventory(@PathVariable Long id) {
        InventoryDto inventoryDto = inventoryMapper.toDto(manageInventoryUseCase.getInventory(id));
        return ResponseEntity.ok(inventoryDto);
    }
}