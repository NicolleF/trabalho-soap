package controller;

import java.util.HashMap;

import model.Recipe;

public class RecipeController {
    HashMap<Integer, Recipe> recipes = new HashMap<>();

    public void createRecipe(int id, String name, String instructions){
        Recipe recipe = new Recipe(id, name, instructions);
        recipes.put(id, recipe);
    }

    public Recipe returnRecipeById(int id){
        return recipes.get(id);
    }

    public HashMap<Integer, Recipe> returnAllRecipes(){
        return recipes;
    }
    
    public void updateRecipe(int id, String newName, String newInstructions){
        Recipe recipe = recipes.get(id);
        recipe.setName(newName);
        recipe.setInstructions(newInstructions);
    }

    public void deleteRecipe(int id){
        Recipe recipe = recipes.get(id);

        //nao sei se essa linha é necessária, validar dps
        recipe.getRecipeIngredients().forEach(ri -> recipe.removeIngredient(ri.getIngredient()));

        recipes.remove(id);
    }
}
