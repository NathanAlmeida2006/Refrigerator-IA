package dev.nathan.refrigerator.application.mapper;

import dev.nathan.refrigerator.application.dto.RecipeDto;
import dev.nathan.refrigerator.domain.model.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    RecipeDto toDto(Recipe recipe);
    Recipe toEntity(RecipeDto recipeDto);
}