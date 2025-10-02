package serv.ingredient;

import javax.xml.ws.Endpoint;

public class IngredientServerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:3000/api/ingredient", new IngredientServerImpl());
        
    }
}
