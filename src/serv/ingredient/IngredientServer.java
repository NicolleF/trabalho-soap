package serv.ingredient;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import model.Ingredient;

@WebService
@SOAPBinding
public interface IngredientServer {
    @WebMethod
    public void createIngredient(int id, String name);

    @WebMethod
    public Ingredient returnIngredientById(int id);

    @WebMethod
    public void updateIngredient(int id, String newName);

    @WebMethod
    public void deleteIngredient(int id);    
}
