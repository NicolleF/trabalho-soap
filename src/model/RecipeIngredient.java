package model;

public class RecipeIngredient {
    private Recipe recipe;
    private Ingredient ingredient;
    private int quantity;
    private String unit;

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, int quantity, String unit) {
        validateQuantity(quantity);
        validateString(unit);

        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setQuantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        validateString(unit);
        this.unit = unit;
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
    }

    private void validateString(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("A unidade de medida não pode ser vazia");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) obj;
        return recipe.equals(that.recipe) && ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return recipe.hashCode() + ingredient.hashCode();
    }
}
