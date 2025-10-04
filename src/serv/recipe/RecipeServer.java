package serv.recipe;

import java.util.List;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.Recipe;

@WebService
@SOAPBinding
public interface RecipeServer {

    @WebMethod
    public void createRecipe(String name, String instructions);

    @WebMethod
    public Recipe returnRecipeById(UUID id);

    @WebMethod
    public List<Recipe> returnAllRecipes();

    @WebMethod
    public List<Recipe> returnRecipesByName(String name);
    
    @WebMethod
    public void updateRecipe(UUID id, String newName, String newInstructions);

    @WebMethod
    public void deleteRecipe(UUID id);

    @WebMethod
    public void addIngredientToRecipe(UUID recipeId, String ingredientName, int quantity, String unit);

    @WebMethod
    public void removeIngredientFromRecipe(UUID recipeId, String ingredientName);

    @WebMethod
    public void updateIngredientInRecipe(UUID recipeId, String ingredientName, int newQuantity, String newUnit);
}
