package dev.nathan.refrigerator.dtos;

import dev.nathan.refrigerator.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItemRequestDTO {
    private String name;
    private Category category;
    private Integer amount;
    private LocalDateTime expirationDate;
}