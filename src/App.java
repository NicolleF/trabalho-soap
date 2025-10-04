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

        System.out.println("=== Iniciando testes do WebService SOAP ===\n");

        // ====== Ingredientes ======
        ingredientServer.createIngredient("Farinha");
        System.out.println("Ingrediente 'Farinha' criado com sucesso.");

        ingredientServer.createIngredient("Leite");
        System.out.println("Ingrediente 'Leite' criado com sucesso.");

        ingredientServer.createIngredient("Chocolate");
        System.out.println("Ingrediente 'Chocolate' criado com sucesso.");

        ingredientServer.createIngredient("Ovo");
        System.out.println("Ingrediente 'Ovo' criado com sucesso.");

        ingredientServer.createIngredient("Açúcar");
        System.out.println("Ingrediente 'Açúcar' criado com sucesso.");

        ingredientServer.createIngredient("Fermento");
        System.out.println("Ingrediente 'Fermento' criado com sucesso.");

        ingredientServer.createIngredient("Morango");
        System.out.println("Ingrediente 'Morango' criado com sucesso.\n");

        // ====== Receita 1 ======
        recipeServer.createRecipe("Chocolate quente",
                "1. Misture o leite com o chocolate em pó.\n2. Aqueça até ferver.\n3. Sirva quente.");
        List<Recipe> recipes = recipeServer.returnRecipesByName("Chocolate quente");
        Recipe chocolateQuente = recipes.get(0);

        recipeServer.addIngredientToRecipe(chocolateQuente.getId(), "Chocolate", 100, "gramas");
        recipeServer.addIngredientToRecipe(chocolateQuente.getId(), "Leite", 200, "ml");

        System.out.println("Receita 'Chocolate quente' criada e ingredientes adicionados.\n");

        // ====== Receita 2 ======
        recipeServer.createRecipe("Bolo de Chocolate",
                "1. Misture a farinha, o açúcar e o chocolate.\n2. Adicione ovos e leite.\n3. Asse por 40 minutos.");
        Recipe boloChocolate = recipeServer.returnRecipesByName("Bolo de Chocolate").get(0);

        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Farinha", 300, "gramas");
        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Açúcar", 200, "gramas");
        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Chocolate", 100, "gramas");
        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Ovo", 3, "unidades");
        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Leite", 150, "ml");
        recipeServer.addIngredientToRecipe(boloChocolate.getId(), "Fermento", 10, "gramas");

        System.out.println("Receita 'Bolo de Chocolate' criada e ingredientes adicionados.\n");

        // ====== Receita 3 ======
        recipeServer.createRecipe("Panqueca Simples",
                "1. Misture todos os ingredientes no liquidificador.\n2. Frite pequenas porções em uma frigideira antiaderente.");
        Recipe panqueca = recipeServer.returnRecipesByName("Panqueca Simples").get(0);

        recipeServer.addIngredientToRecipe(panqueca.getId(), "Farinha", 200, "gramas");
        recipeServer.addIngredientToRecipe(panqueca.getId(), "Leite", 300, "ml");
        recipeServer.addIngredientToRecipe(panqueca.getId(), "Ovo", 2, "unidades");

        System.out.println("Receita 'Panqueca Simples' criada e ingredientes adicionados.\n");

        // ====== Receita 4 ======
        recipeServer.createRecipe("Vitamina de Morango",
                "1. Coloque o leite, morangos e açúcar no liquidificador.\n2. Bata até ficar homogêneo.\n3. Sirva gelado.");
        Recipe vitamina = recipeServer.returnRecipesByName("Vitamina de Morango").get(0);

        recipeServer.addIngredientToRecipe(vitamina.getId(), "Morango", 150, "gramas");
        recipeServer.addIngredientToRecipe(vitamina.getId(), "Leite", 200, "ml");
        recipeServer.addIngredientToRecipe(vitamina.getId(), "Açúcar", 50, "gramas");

        System.out.println("Receita 'Vitamina de Morango' criada e ingredientes adicionados.\n");

        // ====== Exibindo todas as receitas ======
        List<Recipe> allRecipes = recipeServer.returnAllRecipes();
        System.out.println("===== RECEITAS CADASTRADAS =====");
        for (Recipe r : allRecipes) {
            System.out.println("\nID: " + r.getId());
            System.out.println("Nome: " + r.getName());
            System.out.println("Instruções:\n" + r.getInstructions());
            System.out.println("Ingredientes:");
            r.getRecipeIngredients().forEach(ri -> {
                System.out.println("- " + ri.getIngredient().getName() + ": " + ri.getQuantity() + " " + ri.getUnit());
            });
        }

        // ====== Removendo um ingrediente ======
        recipeServer.removeIngredientFromRecipe(boloChocolate.getId(), "Fermento");
        ingredientServer.deleteIngredient("Fermento");
        System.out.println("\nIngrediente 'Fermento' removido da receita 'Bolo de Chocolate' e do sistema.");

        // ====== Removendo uma receita ======
        System.out.println("\nRemovendo a receita 'Panqueca Simples'...");
        recipeServer.deleteRecipe(panqueca.getId());
        System.out.println("Receita 'Panqueca Simples' removida com sucesso.");

        // ====== Confirmando remoção ======
        List<Recipe> recipesAfterDelete = recipeServer.returnAllRecipes();
        System.out.println("\n===== RECEITAS RESTANTES =====");
        recipesAfterDelete.forEach(r -> System.out.println("- " + r.getName()));

        System.out.println("\n=== Testes finalizados ===");
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