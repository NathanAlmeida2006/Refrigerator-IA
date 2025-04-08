package dev.nathan.refrigerator.services.impls;

import dev.nathan.refrigerator.models.FoodItem;
import dev.nathan.refrigerator.repositories.FoodItemRepository;
import dev.nathan.refrigerator.services.FoodItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemServiceImpl(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @Override
    public FoodItem createFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public List<FoodItem> findAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        Optional<FoodItem> foodItem = foodItemRepository.findById(id);
        return foodItem.orElse(null);
    }

    @Override
    public FoodItem updateFoodItem(Long id, FoodItem foodItem) {
        if (foodItemRepository.existsById(id)) {
            foodItem.setId(id);
            return foodItemRepository.save(foodItem);
        }
        return null;
    }

    @Override
    public void deleteFoodItem(Long id) {
        foodItemRepository.deleteById(id);
    }
}