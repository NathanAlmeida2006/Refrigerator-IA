package dev.nathan.refrigerator.controllers;

import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;
import dev.nathan.refrigerator.services.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-items")
public class FoodItemController {

    private final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItemResponseDTO> createFoodItem(@RequestBody FoodItemRequestDTO requestDTO) {
        FoodItemResponseDTO createdItem = foodItemService.createFoodItem(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItemResponseDTO> getFoodItemById(@PathVariable Long id) {
        FoodItemResponseDTO foodItem = foodItemService.getFoodItemById(id);
        return ResponseEntity.ok(foodItem);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemResponseDTO>> getAllFoodItems() {
        List<FoodItemResponseDTO> foodItems = foodItemService.findAllFoodItems();
        return ResponseEntity.ok(foodItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodItemResponseDTO> updateFoodItem(@PathVariable Long id, @RequestBody FoodItemRequestDTO requestDTO) {
        FoodItemResponseDTO updatedItem = foodItemService.updateFoodItem(id, requestDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}