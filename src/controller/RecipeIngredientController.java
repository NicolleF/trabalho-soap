package controller;

import java.util.HashMap;

import model.Ingredient;
import model.Recipe;
import model.RecipeIngredient;

public class RecipeIngredientController {
    HashMap<Integer, RecipeIngredient> recipeIngredients = new HashMap<>();
    IngredientController ingredientController = new IngredientController();
    RecipeController recipeController = new RecipeController();

    public void createRecipeIngredient(int id, int recipeId, int ingredientId, int quantity, String unit) {
        Recipe recipe = recipeController.returnRecipeById(recipeId);
        Ingredient ingredient = ingredientController.returnIngredientById(ingredientId);
        RecipeIngredient recipeIngredient = new RecipeIngredient(id, recipe, ingredient, quantity, unit);
        recipeIngredients.put(id, recipeIngredient);
    }

    public RecipeIngredient returnRecipeIngredientById(int id) {
        return recipeIngredients.get(id);
    }

    public void updateRecipeIngredient(int id, int newQuantity, String newUnit) {
        RecipeIngredient recipeIngredient = recipeIngredients.get(id);
        recipeIngredient.setQuantity(newQuantity);
        recipeIngredient.setUnit(newUnit);
    }

    public void deleteRecipeIngredient(int id) {
        recipeIngredients.remove(id);
    }
}
