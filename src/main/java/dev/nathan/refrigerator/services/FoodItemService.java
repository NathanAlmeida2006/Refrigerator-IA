package dev.nathan.refrigerator.services;

import dev.nathan.refrigerator.models.FoodItem;

import java.util.List;

public interface FoodItemService {
    FoodItem createFoodItem(FoodItem foodItem);

    List<FoodItem> findAllFoodItems();

    FoodItem getFoodItemById(Long id);

    FoodItem updateFoodItem(Long id, FoodItem foodItem);

    void deleteFoodItem(Long id);
}