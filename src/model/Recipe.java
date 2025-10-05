package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Recipe {
    private UUID id;
    private String name;
    private String instructions;
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    public Recipe() {
        this.recipeIngredients = new ArrayList<>();
    }

    public Recipe(UUID id, String name, String instructions) {
        validateString(name, "Nome da receita não pode ser vazio");
        validateString(instructions, "As instruções da receita devem ser fornecidas");

        this.id = id;
        this.name = name;
        this.instructions = instructions;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    @XmlElementWrapper(name = "recipeIngredients")
    @XmlElement(name = "recipeIngredient")
    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public void addIngredient(Ingredient ingredient, int quantity, String unit) {
        if (hasIngredient(ingredient)) {
            throw new IllegalArgumentException("Ingrediente já existe nesta receita");
        }

        RecipeIngredient ri = new RecipeIngredient(UUID.randomUUID(), ingredient, quantity, unit);
        recipeIngredients.add(ri);
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
        }
    }

    public void updateIngredient(Ingredient ingredient, int newQuantity, String newUnit) {
        RecipeIngredient toUpdate = recipeIngredients.stream()
                .filter(ri -> ri.getIngredient().equals(ingredient))
                .findFirst()
                .orElse(null);
        
        if (toUpdate == null) {
            throw new IllegalArgumentException("Ingrediente não encontrado nesta receita");
        }
        
        toUpdate.setQuantity(newQuantity);
        toUpdate.setUnit(newUnit);
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
