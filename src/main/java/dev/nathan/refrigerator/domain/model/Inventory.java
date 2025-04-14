package dev.nathan.refrigerator.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private Long id;
    @Builder.Default
    private List<Ingredient> ingredients = new ArrayList<>();
}