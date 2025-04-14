package dev.nathan.refrigerator.infrastructure.aiclient.ollama;

import dev.nathan.refrigerator.domain.model.Ingredient;
import dev.nathan.refrigerator.domain.model.Recipe;
import dev.nathan.refrigerator.domain.port.AiRecipeGeneratorPort;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OllamaAiRecipeAdapter implements AiRecipeGeneratorPort {

    private final ChatClient chatClient;
    private final String systemPrompt;

    public OllamaAiRecipeAdapter(ChatClient.Builder chatClientBuilder, @Value("${app.ai.system-prompt}") String systemPrompt) {
        this.chatClient = chatClientBuilder.build();
        this.systemPrompt = systemPrompt;
    }

    @Override
    public Recipe generateRecipe(List<String> ingredients) {
        if (ingredients == null || ingredients.isEmpty()) {
            return Recipe.builder()
                    .name("Receita Não Gerada")
                    .ingredients(List.of())
                    .instructions("Nenhum ingrediente fornecido para gerar uma receita.")
                    .build();
        }

        Prompt prompt = buildRecipePrompt(ingredients);
        String response = chatClient.prompt(prompt).call().content();

        if (response == null || response.isBlank()) {
            response = "A IA não conseguiu gerar uma receita com os ingredientes fornecidos.";
        }

        // Convert List<String> to List<Ingredient> for Recipe
        List<Ingredient> ingredientObjects = ingredients.stream()
                .distinct()
                .map(name -> Ingredient.builder().name(name).build())
                .collect(Collectors.toList());

        return Recipe.builder()
                .name("Receita Gerada")
                .ingredients(ingredientObjects)
                .instructions(response.trim())
                .build();
    }

    private Prompt buildRecipePrompt(List<String> ingredients) {
        String ingredientList = ingredients.stream().distinct().collect(Collectors.joining(", "));
        String userPromptContent = String.format(
                "Crie uma receita simples usando APENAS os seguintes ingredientes disponíveis: %s. " +
                        "A receita deve incluir: " +
                        "1. Um título criativo. " +
                        "2. A lista de ingredientes (apenas os da lista fornecida que foram usados). " +
                        "3. Instruções de preparo passo a passo claras e concisas.",
                ingredientList);

        SystemMessage systemMessage = new SystemMessage(systemPrompt);
        UserMessage userMessage = new UserMessage(userPromptContent);

        return new Prompt(List.of(systemMessage, userMessage));
    }
}