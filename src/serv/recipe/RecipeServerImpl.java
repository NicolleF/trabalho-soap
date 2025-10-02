package serv.recipe;

import java.net.URL;
import java.util.HashMap;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import model.Recipe;

@WebService(endpointInterface = "serv.recipe.RecipeServer")
public class RecipeServerImpl implements RecipeServer {
    HashMap<Integer, Recipe> recipes = new HashMap<>();

    public static RecipeServer getRecipeServerPort() throws Exception {
        try {
            URL url = new URL("http://127.0.0.1:3000/api/recipe?wsdl");
            QName qName = new QName("http://recipe/", "RecipeServerImplService");

            Service ws = Service.create(url, qName);

            return ws.getPort(RecipeServer.class);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao conectar ao RecipeServer: " + ex.getMessage());
        }
    }
    
    @Override
    public void createRecipe(int id, String name, String instructions) {
        Recipe recipe = new Recipe(id, name, instructions);
        recipes.put(id, recipe);
    }

    @Override
    public Recipe returnRecipeById(int id) {
        return recipes.get(id);
    }

    @Override
    public HashMap<Integer, Recipe> returnAllRecipes() {
        return recipes;
    }

    @Override
    public void updateRecipe(int id, String newName, String newInstructions) {
        Recipe recipe = recipes.get(id);
        recipe.setName(newName);
        recipe.setInstructions(newInstructions);
    }

    @Override
    public void deleteRecipe(int id) {
        Recipe recipe = recipes.get(id);

        // nao sei se essa linha é necessária, validar dps
        recipe.getRecipeIngredients().forEach(ri -> recipe.removeIngredient(ri.getIngredient()));

        recipes.remove(id);
    }

}
