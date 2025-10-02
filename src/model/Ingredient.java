package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ingredient {
    private int id;
    private String name;
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Ingredient(int id, String name) {
        validateString(name);
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateString(name);
        this.name = name;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    protected void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        if (!recipeIngredients.contains(recipeIngredient)) {
            recipeIngredients.add(recipeIngredient);
        }
    }

    protected void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
    }

    public List<Recipe> getRecipes() {
        return recipeIngredients.stream()
                .map(RecipeIngredient::getRecipe)
                .collect(Collectors.toList());
    }

    private void validateString(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Nome do ingrediente não pode ser vazio");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient that = (Ingredient) obj;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
