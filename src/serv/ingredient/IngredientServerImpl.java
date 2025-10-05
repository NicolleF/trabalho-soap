package serv.ingredient;
import java.util.List;

import javax.jws.WebService;

import controller.IngredientController;
import model.Ingredient;

@WebService(endpointInterface = "serv.ingredient.IngredientServer")
public class IngredientServerImpl implements IngredientServer {

    @Override
    public void createIngredient(String name){
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.createIngredient(name);
    }

    @Override
    public Ingredient returnIngredientByName(String name){
        IngredientController ingredientController = IngredientController.getInstance();
        return ingredientController.returnIngredientByName(name);
    }

    @Override
    public List<Ingredient> returnAllIngredients(){
        IngredientController ingredientController = IngredientController.getInstance();
        return ingredientController.returnAllIngredients();
    }

    @Override
    public void updateIngredient(String currentName, String newName){
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.updateIngredient(currentName, newName);
    }

    @Override
    public void deleteIngredient(String name){
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.deleteIngredient(name);
    }
}
