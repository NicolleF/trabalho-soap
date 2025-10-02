package controller;

import java.util.HashMap;

import model.Ingredient;

public class IngredientController {
    HashMap<Integer, Ingredient> ingredients = new HashMap<>();

    public void createIngredient(int id, String name){
        Ingredient ingredient = new Ingredient(id, name);
        ingredients.put(id, ingredient);
    }

    public Ingredient returnIngredientById(int id){
        return ingredients.get(id);
    }

    public void updateIngredient(int id, String newName){
        Ingredient ingredient = ingredients.get(id);
        ingredient.setName(newName);
    }

    public void deleteIngredient(int id){
        Ingredient ingredient = ingredients.get(id);
        
        //nao sei se essa linha é necessária, validar dps
        ingredient.getRecipes().forEach(recipe -> recipe.removeIngredient(ingredient));

        ingredients.remove(id);
    }
}
