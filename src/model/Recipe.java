package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe {
    private String name;
    private String instructions;
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Recipe(String name, String instructions) {
        validateString(name, "Nome da receita não pode ser vazio");
        validateString(instructions, "As instruções da receita devem ser fornecidas");

        this.name = name;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void addIngredient(Ingredient ingredient, int quantity, String unit) {
        // validar se esse é o melhor meio pra validar a adição do ingrediente
        if (hasIngredient(ingredient)) {
            throw new IllegalArgumentException("Ingrediente já existe nesta receita");
        }
        
        RecipeIngredient ri = new RecipeIngredient(this, ingredient, quantity, unit);
        recipeIngredients.add(ri);
        ingredient.addRecipeIngredient(ri);
    }

    public void setName(String name) {
        validateString(name, "Nome da receita não pode ser vazio");
        this.name = name;
    }

    public void setInstructions(String instructions) {
        validateString(instructions, "As instruções da receita devem ser fornecidas");
        this.instructions = instructions;
    }

    public boolean hasIngredient(Ingredient ingredient) {
        return recipeIngredients.stream()
                .anyMatch(ri -> ri.getIngredient().equals(ingredient));
    }

    public void removeIngredient(Ingredient ingredient) {
        RecipeIngredient toRemove = recipeIngredients.stream()
                .filter(ri -> ri.getIngredient().equals(ingredient))
                .findFirst()
                .orElse(null);
        
        if (toRemove != null) {
            recipeIngredients.remove(toRemove);
            ingredient.removeRecipeIngredient(toRemove);
        }
    }

    public List<Ingredient> getIngredients() {
        return recipeIngredients.stream()
                .map(RecipeIngredient::getIngredient)
                .collect(Collectors.toList());
    }

    private void validateString(String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Recipe recipe = (Recipe) obj;
        return name.equals(recipe.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
