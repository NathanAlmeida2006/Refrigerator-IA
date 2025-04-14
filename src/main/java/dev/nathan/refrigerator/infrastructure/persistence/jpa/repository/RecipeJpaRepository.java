package dev.nathan.refrigerator.infrastructure.persistence.jpa.repository;

import dev.nathan.refrigerator.infrastructure.persistence.jpa.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeJpaRepository extends JpaRepository<RecipeEntity, Long> {
}