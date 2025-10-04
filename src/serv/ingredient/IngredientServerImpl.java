package serv.ingredient;

import java.net.URL;
import java.util.List;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import controller.IngredientController;
import model.Ingredient;

@WebService(endpointInterface = "serv.ingredient.IngredientServer")
public class IngredientServerImpl implements IngredientServer {

    // public static IngredientServer getIngredientServerPort() throws Exception {
    //  try {
    //         URL url = new URL("http://127.0.0.1:8080/api/ingredient?wsdl");
    //         QName qName = new QName("http://ingredient.serv/", "IngredientServerImplService");

    //         Service ws = Service.create(url, qName);

    //         return ws.getPort(IngredientServer.class);
    //     } catch (Exception ex) {
    //         throw new RuntimeException("Erro ao conectar ao IngredientServer: " + ex.getMessage());
    //     }   
    // }

    @Override
    public void createIngredient(String name){
        //chamar o metodo do controller
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.createIngredient(name);
    }

    @Override
    public Ingredient returnIngredientByName(String name){
        //buscar do controller
        IngredientController ingredientController = IngredientController.getInstance();
        return ingredientController.returnIngredientByName(name);
    }

    @Override
    public List<Ingredient> returnAllIngredients(){
        //buscar do controller
        IngredientController ingredientController = IngredientController.getInstance();
        return ingredientController.returnAllIngredients();
    }

    @Override
    public void updateIngredient(String currentName, String newName){
        //chamar o metodo do controller
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.updateIngredient(currentName, newName);
    }

    @Override
    public void deleteIngredient(String name){
        //chamar o metodo do controller
        IngredientController ingredientController = IngredientController.getInstance();
        ingredientController.deleteIngredient(name);
    }
}
