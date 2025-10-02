import java.util.HashMap;

import model.Ingredient;
import model.Recipe;
import serv.ingredient.IngredientServerPublisher;
import serv.recipe.RecipeServer;
import serv.recipe.RecipeServerImpl;
import serv.recipe.RecipeServerPublisher;

public class App {
    public App(){
        initPublisher();
        initTests();
    }

    public void initPublisher(){
        RecipeServerPublisher.main(null);
        IngredientServerPublisher.main(null);
    }

    public void initTests(){
        try{
            Recipe recipe = handleRecipeTests();
            Ingredient ingredient = handleIngredientTests();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Recipe handleRecipeTests() throws Exception{
        RecipeServer recipeServer = RecipeServerImpl.getRecipeServerPort();

        recipeServer.createRecipe(1, "Bolo de Chocolate", "1. Misture os ingredientes.\n2. Asse por 30 minutos.");
        recipeServer.createRecipe(2, "Salada Caesar", "1. Misture os ingredientes.\n2. Sirva gelado.");

        Recipe recipe1 = recipeServer.returnRecipeById(1);

        HashMap<Integer, Recipe> allRecipes = recipeServer.returnAllRecipes();

        recipeServer.updateRecipe(1, "Bolo de Chocolate Atualizado", "1. Misture os ingredientes.\n2. Asse por 40 minutos.");

        recipeServer.deleteRecipe(2);
        return recipe1;
    }

    public Ingredient handleIngredientTests() throws Exception{
        serv.ingredient.IngredientServer ingredientServer = serv.ingredient.IngredientServerImpl.getIngredientServerPort();

        ingredientServer.createIngredient(1, "Farinha de Trigo");
        ingredientServer.createIngredient(2, "Ovos");

        Ingredient ingredient1 = ingredientServer.returnIngredientById(1);

        ingredientServer.updateIngredient(1, "Farinha de Trigo Integral");

        ingredientServer.deleteIngredient(2);

        return ingredient1;
    }

    public static void main(String[] args) {
        new App();
    }
}
