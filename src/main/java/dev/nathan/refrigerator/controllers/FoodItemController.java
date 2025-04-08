package dev.nathan.refrigerator.controllers;

import dev.nathan.refrigerator.converters.FoodItemConverter;
import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;
import dev.nathan.refrigerator.models.FoodItem;
import dev.nathan.refrigerator.services.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/food-items")
public class FoodItemController {

    private final FoodItemService foodItemService;
    private final FoodItemConverter foodItemConverter;

    public FoodItemController(FoodItemService foodItemService, FoodItemConverter foodItemConverter) {
        this.foodItemService = foodItemService;
        this.foodItemConverter = foodItemConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodItemResponseDTO createFoodItem(@RequestBody FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = foodItemConverter.toEntity(foodItemRequestDTO);
        FoodItem savedFoodItem = foodItemService.createFoodItem(foodItem);
        return foodItemConverter.toResponseDTO(savedFoodItem);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodItemResponseDTO getFoodItemById(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        return foodItemConverter.toResponseDTO(foodItem);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FoodItemResponseDTO> getAllFoodItems() {
        return foodItemService.findAllFoodItems().stream().map(foodItemConverter::toResponseDTO).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FoodItemResponseDTO updateFoodItem(@PathVariable Long id, @RequestBody FoodItemRequestDTO foodItemRequestDTO) {
        FoodItem foodItem = foodItemConverter.toEntity(foodItemRequestDTO);
        FoodItem updatedFoodItem = foodItemService.updateFoodItem(id, foodItem);
        return foodItemConverter.toResponseDTO(updatedFoodItem);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFoodItem(@PathVariable Long id) {
        foodItemService.deleteFoodItem(id);
    }
}