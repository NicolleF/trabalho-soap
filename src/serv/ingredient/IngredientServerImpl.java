package serv.ingredient;

import java.net.URL;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import model.Ingredient;
import serv.recipe.RecipeServer;

@WebService(endpointInterface = "serv.ingredient.IngredientServer")
public class IngredientServerImpl implements IngredientServer {
    HashMap<Integer, Ingredient> ingredients = new HashMap<>();

    public static IngredientServer getIngredientServerPort() throws Exception {
     try {
            URL url = new URL("http://127.0.0.1:3000/api/ingredient?wsdl");
            QName qName = new QName("http://ingredient/", "IngredientServerImplService");

            Service ws = Service.create(url, qName);

            return ws.getPort(IngredientServer.class);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao conectar ao IngredientServer: " + ex.getMessage());
        }   
    }
    @Override
    public void createIngredient(int id, String name){
        Ingredient ingredient = new Ingredient(id, name);
        ingredients.put(id, ingredient);
    }

    @Override
    public Ingredient returnIngredientById(int id){
        return ingredients.get(id);
    }

    @Override
    public void updateIngredient(int id, String newName){
        Ingredient ingredient = ingredients.get(id);
        ingredient.setName(newName);
    }

    @Override
    public void deleteIngredient(int id){
        Ingredient ingredient = ingredients.get(id);
        
        //nao sei se essa linha é necessária, validar dps
        ingredient.getRecipes().forEach(recipe -> recipe.removeIngredient(ingredient));

        ingredients.remove(id);
    }
}
