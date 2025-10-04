package serv.publisher;

import javax.xml.ws.Endpoint;

import serv.ingredient.IngredientServerImpl;
import serv.recipe.RecipeServerImpl;

public class ServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8080/api/ingredient", new IngredientServerImpl());
        Endpoint.publish("http://127.0.0.1:8080/api/recipe", new RecipeServerImpl());
    }
}
