package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import model.Ingredient;
import model.Recipe;

public class RecipeController {
    private HashMap<UUID, Recipe> recipes = new HashMap<>();
    private static RecipeController instance;

    private RecipeController() {
    }

    public static RecipeController getInstance() {
        if (instance == null) {
            instance = new RecipeController();
        }
        return instance;
    }

    public void createRecipe(String name, String instructions){
        // Verificar se já existe receita com mesmo conteúdo
        if (recipeContentExists(name, instructions)) {
            throw new IllegalArgumentException("Já existe uma receita com o mesmo nome e instruções");
        }
        
        Recipe recipe = new Recipe(UUID.randomUUID(), name, instructions);
        recipes.put(recipe.getId(), recipe);
        System.out.println("Receita criada com ID: " + recipe.getId());
        System.out.println("Nome: " + recipe.getName());
        System.out.println("Instruções: " + recipe.getInstructions());
        System.out.println("-------------------------------");
    }

    public Recipe returnRecipeById(UUID id){
        Recipe recipe = recipes.get(id);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + id);
        }
        return recipe;
    }

    //retorna uma lista de receitas
    public List<Recipe> returnAllRecipes(){
        return new ArrayList<>(recipes.values());
    }

    public List<Recipe> returnRecipesByName(String name) {
        String normalizedName = normalizeString(name);
        
        List<Recipe> foundRecipes = recipes.values().stream()
                .filter(recipe -> normalizeString(recipe.getName()).equals(normalizedName))
                .collect(Collectors.toList());
        
        if (foundRecipes.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma receita encontrada com o nome: " + name);
        }
        
        return foundRecipes;
    }

    public void updateRecipe(UUID id, String newName, String newInstructions){
        Recipe recipe = recipes.get(id);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + id);
        }
        
        // Verificar se o novo conteúdo já existe em outra receita
        String normalizedNewName = normalizeString(newName);
        String normalizedNewInstructions = normalizeString(newInstructions);
        
        boolean duplicateExists = recipes.values().stream()
                .anyMatch(otherRecipe -> 
                    !otherRecipe.getId().equals(id) && // Não comparar com ela mesma
                    normalizeString(otherRecipe.getName()).equals(normalizedNewName) &&
                    normalizeString(otherRecipe.getInstructions()).equals(normalizedNewInstructions)
                );
        
        if (duplicateExists) {
            //é interessante dizer o nome da receita que ja existe
            throw new IllegalArgumentException("Já existe outra receita com o mesmo nome e instruções");
        }
        
        recipe.setName(newName);
        recipe.setInstructions(newInstructions);
    }

    public void deleteRecipe(UUID id){
        Recipe recipe = recipes.get(id);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + id);
        }

        //nao sei se essa linha é necessária, validar dps
        recipe.getRecipeIngredients().forEach(ri -> recipe.removeIngredient(ri.getIngredient()));

        recipes.remove(id);
    }

    public void addIngredientToRecipe(UUID recipeId, String ingredientName, int quantity, String unit) {
        Recipe recipe = recipes.get(recipeId);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + recipeId);
        }
        
        IngredientController ingredientController = IngredientController.getInstance();
        Ingredient ingredient = ingredientController.returnIngredientByName(ingredientName);
        recipe.addIngredient(ingredient, quantity, unit);
    }

    public void removeIngredientFromRecipe(UUID recipeId, String ingredientName) {
        Recipe recipe = recipes.get(recipeId);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + recipeId);
        }
        //remove da ligação entre ingrediente e receita, não remove o ingrediente do sistema
        IngredientController ingredientController = IngredientController.getInstance();
        Ingredient ingredient = ingredientController.returnIngredientByName(ingredientName);
        recipe.removeIngredient(ingredient);
    }

    public void updateIngredientInRecipe(UUID recipeId, String ingredientName, int newQuantity, String newUnit) {
        Recipe recipe = recipes.get(recipeId);
        if (recipe == null) {
            throw new IllegalArgumentException("Receita não encontrada com o ID: " + recipeId);
        }

        //atualiza na ligação entre ingrediente e receita
        IngredientController ingredientController = IngredientController.getInstance();
        Ingredient ingredient = ingredientController.returnIngredientByName(ingredientName);
        recipe.updateIngredient(ingredient, newQuantity, newUnit);
    }

    private String normalizeString(String input) {
        if (input == null) return null;
        return input.trim().toLowerCase();
    }

    private boolean recipeContentExists(String name, String instructions) {
        String normalizedName = normalizeString(name);
        String normalizedInstructions = normalizeString(instructions);
        
        return recipes.values().stream()
                .anyMatch(recipe -> 
                    normalizeString(recipe.getName()).equals(normalizedName) &&
                    normalizeString(recipe.getInstructions()).equals(normalizedInstructions)
                );
    }

    // Método para verificar se um ingrediente está sendo usado em alguma receita
    public boolean isIngredientUsedInAnyRecipe(Ingredient ingredient) {
        return recipes.values().stream()
                .anyMatch(recipe -> recipe.hasIngredient(ingredient));
    }

    // Método para retornar as receitas que usam um determinado ingrediente
    public List<Recipe> getRecipesByIngredient(Ingredient ingredient) {
        return recipes.values().stream()
                .filter(recipe -> recipe.hasIngredient(ingredient))
                .collect(Collectors.toList());
    }
}
