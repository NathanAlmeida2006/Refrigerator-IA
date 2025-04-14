package dev.nathan.refrigerator.controllers;

import dev.nathan.refrigerator.services.FoodItemService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    private final FoodItemService foodItemService;

    public RecipeController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @GetMapping("/suggest")
    public ResponseEntity<String> suggestRecipe() {
        String recipeSuggestion = foodItemService.suggestRecipe();
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(recipeSuggestion);
    }
}