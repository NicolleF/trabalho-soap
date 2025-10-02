package serv.recipe;

import javax.xml.ws.Endpoint;

public class RecipeServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:3000/api/recipe", new RecipeServerImpl());
    }
}
