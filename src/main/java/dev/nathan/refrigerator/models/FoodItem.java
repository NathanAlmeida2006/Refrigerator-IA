package dev.nathan.refrigerator.models;

import dev.nathan.refrigerator.models.enums.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    private Integer amount;
    private LocalDateTime expirationDate;

}
