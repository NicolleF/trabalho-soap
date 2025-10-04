import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import model.Recipe;
import serv.ingredient.IngredientServer;
import serv.recipe.RecipeServer;

public class App {
    public static void main(String[] args) throws Exception {
        IngredientServer ingredientServer = getIngredientServer();
        RecipeServer recipeServer = getRecipeServer();

        ingredientServer.createIngredient("Farinha");
        System.out.println("Ingrediente 'Farinha' criado com sucesso.");

        ingredientServer.createIngredient("Leite");
        System.out.println("Ingrediente 'Leite' criado com sucesso.");

        ingredientServer.createIngredient("Chocolate");
        System.out.println("Ingrediente 'Chocolate' criado com sucesso.");

        recipeServer.createRecipe("Chocolate quente", "1. Misture o leite com o chocolate em pó.\n2. Aqueça até ferver.\n3. Sirva quente.");
        List<Recipe> recipes = recipeServer.returnRecipesByName("Chocolate quente");
        System.out.println("Receitas encontradas: " + recipes.size());
        
        recipeServer.addIngredientToRecipe(recipes.get(0).getId(), "Chocolate", 100, "gramas");
        recipeServer.addIngredientToRecipe(recipes.get(0).getId(), "Leite", 200, "ml");

        Recipe updatedRecipe = recipeServer.returnRecipeById(recipes.get(0).getId());
        System.out.println("ID: " + updatedRecipe.getId());
        System.out.println("Nome: " + updatedRecipe.getName());
        System.out.println("Instruções: " + updatedRecipe.getInstructions());
        System.out.println("Ingredientes:");
        updatedRecipe.getRecipeIngredients().forEach(ri -> {
            System.out.println("- " + ri.getIngredient().getName() + ": " + ri.getQuantity() + " " + ri.getUnit());
        });
    }

    public static RecipeServer getRecipeServer() throws Exception {
        URL url = new URL("http://127.0.0.1:8080/api/recipe?wsdl");
        QName qName = new QName("http://recipe.serv/", "RecipeServerImplService");
        Service ws = Service.create(url, qName);
        return ws.getPort(RecipeServer.class);
    }

    public static IngredientServer getIngredientServer() throws Exception {
        URL url = new URL("http://127.0.0.1:8080/api/ingredient?wsdl");
        QName qName = new QName("http://ingredient.serv/", "IngredientServerImplService");
        Service ws = Service.create(url, qName);
        return ws.getPort(IngredientServer.class);
    }
}