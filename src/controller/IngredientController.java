package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Ingredient;

public class IngredientController {
    private static IngredientController instance;
    private HashMap<String, Ingredient> ingredients = new HashMap<>();

    private IngredientController() {
    }

    public static IngredientController getInstance() {
        if (instance == null) {
            instance = new IngredientController();
        }
        return instance;
    }

    public void createIngredient(String name){
        String normalizedName = normalizeString(name);
        if (ingredients.containsKey(normalizedName)) {
            throw new IllegalArgumentException("Já existe um ingrediente com o nome: " + name);
        }
        Ingredient ingredient = new Ingredient(name);
        ingredients.put(normalizedName, ingredient);
    }

    public Ingredient returnIngredientByName(String name){
        Ingredient ingredient = ingredients.get(normalizeString(name));
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediente não encontrado com o nome: " + name);
        }
        return ingredient;
    }

    public List<Ingredient> returnAllIngredients(){
        return new ArrayList<>(ingredients.values());
    }

    public void updateIngredient(String currentName, String newName){
        String normalizedCurrentName = normalizeString(currentName);
        String normalizedNewName = normalizeString(newName);

        Ingredient ingredient = ingredients.get(normalizedCurrentName);
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediente não encontrado com o nome: " + currentName);
        }
        
        if (!normalizedCurrentName.equals(normalizedNewName) && ingredients.containsKey(normalizedNewName)) {
            throw new IllegalArgumentException("Já existe um ingrediente com o nome: " + newName);
        }
        
        ingredient.setName(newName);
        
        if (!normalizedCurrentName.equals(normalizedNewName)) {
            ingredients.remove(normalizedCurrentName);
            ingredients.put(normalizedNewName, ingredient);
        }
    }

    public void deleteIngredient(String name){
        String normalizedName = normalizeString(name);
        Ingredient ingredient = ingredients.get(normalizedName);
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediente não encontrado com o nome: " + name);
        }
        
        RecipeController recipeController = RecipeController.getInstance();
        if (recipeController.isIngredientUsedInAnyRecipe(ingredient)) {
            List<model.Recipe> recipesUsingIngredient = recipeController.getRecipesByIngredient(ingredient);
            String recipeNames = recipesUsingIngredient.stream()
                    .map(model.Recipe::getName)
                    .collect(java.util.stream.Collectors.joining(", "));
            throw new IllegalArgumentException("Não é possível excluir o ingrediente '" + name + 
                    "' pois ele está sendo usado nas seguintes receitas: " + recipeNames);
        }

        ingredients.remove(normalizedName);
    }

    private String normalizeString(String value) {
        return value == null ? null : value.trim().toLowerCase();
    }
}
