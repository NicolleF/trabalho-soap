package serv.recipe;

import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.Recipe;

@WebService
@SOAPBinding
public interface RecipeServer {

    @WebMethod
    public void createRecipe(int id, String name, String instructions);

    @WebMethod
    public Recipe returnRecipeById(int id);

    @WebMethod
    public HashMap<Integer, Recipe> returnAllRecipes();
    
    @WebMethod
    public void updateRecipe(int id, String newName, String newInstructions);

    @WebMethod
    public void deleteRecipe(int id);
}
