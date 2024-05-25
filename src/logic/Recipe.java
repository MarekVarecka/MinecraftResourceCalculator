package logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recipe {
	private String item;
	private Map<String, List<Integer>> ingredients;
	private int quantity;
	
	public String getItem() {
		return item;
	}
	
	public Map<String, List<Integer>> getIngredients() {
		return ingredients;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public void setIngredients(Map<String, List<Integer>> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Map<String, Integer> getRecipe(int multiplier) {
		Map<String, Integer> recipe = new HashMap<>();
		
		System.out.println("For " + getQuantity() + " " + getItem() + " you will need:");
		for (Map.Entry<String, List<Integer>> entry : getIngredients().entrySet()) {
	        // Calculate the total amount required for each ingredient, considering the multiplier
	        int totalAmount = entry.getValue().size() * multiplier;
	        recipe.put(entry.getKey(), totalAmount);

	        // Optionally, you could still print out each requirement if needed
	        System.out.println(entry.getKey() + ": " + totalAmount);
	    }

	    return recipe;  // Return the map with all the calculated requirements
	}
	
	public Recipe(String item, Map<String, List<Integer>> ingredients, int quantity) {
		setItem(item);
		setIngredients(ingredients);
		setQuantity(quantity);
	}
	
}

class Recipes {
	private List<Recipe> recipes;

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public Recipes(List<Recipe> recipes) {
		setRecipes(recipes);
	}
}
