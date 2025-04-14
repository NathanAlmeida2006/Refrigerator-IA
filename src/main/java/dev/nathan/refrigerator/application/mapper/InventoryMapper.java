package dev.nathan.refrigerator.application.mapper;

import dev.nathan.refrigerator.application.dto.InventoryDto;
import dev.nathan.refrigerator.domain.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    InventoryDto toDto(Inventory inventory);
    Inventory toEntity(InventoryDto inventoryDto);
}