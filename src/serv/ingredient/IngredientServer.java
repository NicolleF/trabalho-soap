package serv.ingredient;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.Ingredient;

@WebService
@SOAPBinding
public interface IngredientServer {
    @WebMethod
    public void createIngredient(String name);

    @WebMethod
    public Ingredient returnIngredientByName(String name);

    @WebMethod
    public List<Ingredient> returnAllIngredients();

    @WebMethod
    public void updateIngredient(String currentName, String newName);

    @WebMethod
    public void deleteIngredient(String name);    
}
