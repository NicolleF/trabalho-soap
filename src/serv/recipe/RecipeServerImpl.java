package serv.recipe;
import java.util.List;
import java.util.UUID;

import javax.jws.WebService;

import controller.RecipeController;
import model.Recipe;

@WebService(endpointInterface = "serv.recipe.RecipeServer")
public class RecipeServerImpl implements RecipeServer {
    private RecipeController recipeController = RecipeController.getInstance();
    
    @Override
    public void createRecipe(String name, String instructions){
        recipeController.createRecipe(name, instructions);
    }

    @Override
    public Recipe returnRecipeById(UUID id){
        return recipeController.returnRecipeById(id);
    }

    @Override
    public List<Recipe> returnAllRecipes() {
        return recipeController.returnAllRecipes();
    }

    @Override
    public List<Recipe> returnRecipesByName(String name) {
        List<Recipe> foundRecipes = recipeController.returnRecipesByName(name);

        if (foundRecipes.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma receita encontrada com o nome: " + name);
        }
        
        return foundRecipes;
    }

    @Override
    public void updateRecipe(UUID id, String newName, String newInstructions){
        recipeController.updateRecipe(id, newName, newInstructions);
    }

    @Override
    public void deleteRecipe(UUID id){
        recipeController.deleteRecipe(id);
    }

    @Override
    public void addIngredientToRecipe(UUID recipeId, String ingredientName, int quantity, String unit) {
        recipeController.addIngredientToRecipe(recipeId, ingredientName, quantity, unit);
    }

    @Override
    public void removeIngredientFromRecipe(UUID recipeId, String ingredientName) {
        recipeController.removeIngredientFromRecipe(recipeId, ingredientName);
    }

    @Override
    public void updateIngredientInRecipe(UUID recipeId, String ingredientName, int newQuantity, String newUnit) {
        recipeController.updateIngredientInRecipe(recipeId, ingredientName, newQuantity, newUnit);
    }
}
