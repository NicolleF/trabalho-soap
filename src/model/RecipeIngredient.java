package model;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RecipeIngredient {
    private UUID id;
    private Ingredient ingredient;
    private int quantity;
    private String unit;

    public RecipeIngredient() {
    }

    public RecipeIngredient(UUID id, Ingredient ingredient, int quantity, String unit) {
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingrediente não pode ser nulo");
        }
        validateQuantity(quantity);
        validateString(unit);

        this.id = id;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
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
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
