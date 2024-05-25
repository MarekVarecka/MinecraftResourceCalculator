package logic;

import java.io.FileReader;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class RecipeLoader {
    private List<Recipe> recipes;

    public RecipeLoader() {
        this.recipes = new ArrayList<>();
    }

    public void loadRecipes(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Recipes recipes = gson.fromJson(reader, Recipes.class);
            this.recipes = recipes.getRecipes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getItemNames() {
        List<String> itemNames = new ArrayList<>();
        for (Recipe recipe : recipes) {
            itemNames.add(recipe.getItem());
        }
        return itemNames;
    }
    
	public Recipe getRecipe(String item) {
		for (Recipe recipe : recipes) {
			if (recipe.getItem().equals(item)) {
				return recipe;
			}
		}
		return null;
	}
}

