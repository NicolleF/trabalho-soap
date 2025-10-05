package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ingredient {
    private String name;

    public Ingredient() {
    }

    public Ingredient(String name) {
        validateString(name);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateString(name);
        this.name = name;
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
