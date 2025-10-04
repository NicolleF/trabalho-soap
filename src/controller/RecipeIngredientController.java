// package controller;

// import java.util.HashMap;
// import java.util.UUID;

// import model.Ingredient;
// import model.Recipe;
// import model.RecipeIngredient;

// public class RecipeIngredientController {
//     private HashMap<UUID, RecipeIngredient> recipeIngredients = new HashMap<>();
//     private IngredientController ingredientController;
//     private RecipeController recipeController;

//     // Construtor que recebe as instâncias dos controladores
//     public RecipeIngredientController(RecipeController recipeController, IngredientController ingredientController) {
//         this.recipeController = recipeController;
//         this.ingredientController = ingredientController;
//     }

//     public void createRecipeIngredient(UUID id, UUID recipeId, String ingredientName, int quantity, String unit) {
//         if (recipeIngredients.containsKey(id)) {
//             throw new IllegalArgumentException("Já existe um RecipeIngredient com este ID");
//         }
        
//         Recipe recipe = recipeController.returnRecipeById(recipeId);
//         Ingredient ingredient = ingredientController.returnIngredientByName(ingredientName);
        
//         RecipeIngredient recipeIngredient = new RecipeIngredient(id, recipe, ingredient, quantity, unit);
//         recipeIngredients.put(id, recipeIngredient);
//     }

//     public RecipeIngredient returnRecipeIngredientById(UUID id) {
//         RecipeIngredient recipeIngredient = recipeIngredients.get(id);
//         if (recipeIngredient == null) {
//             throw new IllegalArgumentException("RecipeIngredient não encontrado com o ID: " + id);
//         }
//         return recipeIngredient;
//     }

//     public HashMap<UUID, RecipeIngredient> returnAllRecipeIngredients() {
//         return recipeIngredients;
//     }

//     public void updateRecipeIngredient(UUID id, int newQuantity, String newUnit) {
//         RecipeIngredient recipeIngredient = recipeIngredients.get(id);
//         if (recipeIngredient == null) {
//             throw new IllegalArgumentException("RecipeIngredient não encontrado com o ID: " + id);
//         }
//         recipeIngredient.setQuantity(newQuantity);
//         recipeIngredient.setUnit(newUnit);
//     }

//     public void deleteRecipeIngredient(UUID id) {
//         RecipeIngredient recipeIngredient = recipeIngredients.get(id);
//         if (recipeIngredient == null) {
//             throw new IllegalArgumentException("RecipeIngredient não encontrado com o ID: " + id);
//         }
//         recipeIngredients.remove(id);
//     }
// }
