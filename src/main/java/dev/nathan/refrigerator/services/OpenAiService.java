package dev.nathan.refrigerator.services;

import java.util.List;

public interface OpenAiService {
    String generateRecipe(List<String> ingredients); // Returns String or throws AI/runtime exception
}