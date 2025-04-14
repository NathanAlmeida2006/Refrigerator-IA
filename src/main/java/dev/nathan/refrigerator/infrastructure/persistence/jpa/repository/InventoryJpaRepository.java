package dev.nathan.refrigerator.infrastructure.persistence.jpa.repository;

import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {
}