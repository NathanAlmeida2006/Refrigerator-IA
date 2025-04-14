package dev.nathan.refrigerator.services.impls;

import dev.nathan.refrigerator.converters.FoodItemConverter;
import dev.nathan.refrigerator.dtos.FoodItemRequestDTO;
import dev.nathan.refrigerator.dtos.FoodItemResponseDTO;
import dev.nathan.refrigerator.exceptions.ResourceNotFoundException;
import dev.nathan.refrigerator.models.FoodItem;
import dev.nathan.refrigerator.repositories.FoodItemRepository;
import dev.nathan.refrigerator.services.FoodItemService;
import dev.nathan.refrigerator.services.OpenAiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class FoodItemServiceImpl implements FoodItemService {

    private static final Logger log = LoggerFactory.getLogger(FoodItemServiceImpl.class);

    private final FoodItemRepository foodItemRepository;
    private final OpenAiService openAiService;
    private final FoodItemConverter foodItemConverter;

    public FoodItemServiceImpl(FoodItemRepository foodItemRepository, OpenAiService openAiService, FoodItemConverter foodItemConverter) {
        this.foodItemRepository = foodItemRepository;
        this.openAiService = openAiService;
        this.foodItemConverter = foodItemConverter;
    }

    @Override
    @Transactional
    public FoodItemResponseDTO createFoodItem(FoodItemRequestDTO requestDTO) {
        log.info("Service: Creating food item from DTO: {}", requestDTO.getName());
        FoodItem foodItem = foodItemConverter.toEntity(requestDTO);
        FoodItem savedFoodItem = foodItemRepository.save(foodItem);
        log.info("Service: Food item saved with id: {}", savedFoodItem.getId());
        return foodItemConverter.toResponseDTO(savedFoodItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FoodItemResponseDTO> findAllFoodItems() {
        log.info("Service: Finding all food items");
        return foodItemRepository.findAll().stream().map(foodItemConverter::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FoodItemResponseDTO getFoodItemById(Long id) {
        log.info("Service: Finding food item by id: {}", id);
        FoodItem foodItem = foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));
        log.info("Service: Found food item with id: {}", id);
        return foodItemConverter.toResponseDTO(foodItem);
    }

    @Override
    @Transactional
    public FoodItemResponseDTO updateFoodItem(Long id, FoodItemRequestDTO requestDTO) {
        log.info("Service: Attempting to update food item with id: {}", id);
        FoodItem existingItem = foodItemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FoodItem", "id", id));

        log.debug("Service: Found existing item {}. Updating...", id);
        FoodItem updatedData = foodItemConverter.toEntity(requestDTO);
        existingItem.setName(updatedData.getName());
        existingItem.setCategory(updatedData.getCategory());
        existingItem.setAmount(updatedData.getAmount());
        existingItem.setExpirationDate(updatedData.getExpirationDate());

        FoodItem updated = foodItemRepository.save(existingItem);
        log.info("Service: Successfully updated food item with id: {}", id);
        return foodItemConverter.toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deleteFoodItem(Long id) {
        log.info("Service: Attempting to delete food item with id: {}", id);
        if (!foodItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("FoodItem", "id", id);
        }
        foodItemRepository.deleteById(id);
        log.info("Service: Successfully deleted food item with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public String suggestRecipe() {
        log.info("Service: Suggesting recipe based on available food items.");
        List<FoodItem> availableItems = foodItemRepository.findAll();

        if (availableItems.isEmpty()) {
            log.warn("Service: No food items found in the refrigerator.");
            return "Sua geladeira está vazia! Adicione alguns itens para receber sugestões de receitas.";
        }

        List<String> ingredientNames = availableItems.stream().map(FoodItem::getName).filter(name -> name != null && !name.trim().isEmpty()).distinct().collect(Collectors.toList());

        if (ingredientNames.isEmpty()) {
            log.warn("Service: No valid ingredient names could be extracted.");
            return "Não foi possível identificar nomes válidos nos itens da sua geladeira.";
        }

        log.debug("Service: Calling OpenAiService with ingredients: {}", ingredientNames);
        // Let OpenAiService handle its potential exceptions (AiRuntimeException)
        return openAiService.generateRecipe(ingredientNames);
    }
}