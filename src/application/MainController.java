package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import logic.RecipeLoader;

public class MainController {
	
	private RecipeLoader RecipeLoader;
	
	@FXML private ComboBox<String> DropDown;
	@FXML private TextField QuantityField;
	@FXML private ComboBox<String> Multiplicatior;
	@FXML private Button CalculateBtn;
	@FXML private TextArea outputTextArea;

	@FXML
    public void initialize() {
        RecipeLoader = new RecipeLoader();
        RecipeLoader.loadRecipes("recipes.json"); // Ensure this path is correct
        DropDown.setItems(FXCollections.observableArrayList(RecipeLoader.getItemNames()));
        
        QuantityField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!keyEvent.getCharacter().matches("[0-9]")) {  // Check if the character is not a digit
                keyEvent.consume();  // Consume the event to prevent processing
            }
        });
        
        Multiplicatior.setItems(FXCollections.observableArrayList("Pcs", "Stacks"));
        Multiplicatior.getSelectionModel().selectFirst();
    }
	
	@FXML
	public void CalculateQuantity(ActionEvent event) {
		System.out.println("Calculate Quantity");
		
		outputTextArea.clear();
		outputTextArea.setVisible(true);
		String selectedItem = DropDown.getSelectionModel().getSelectedItem();
        String selectedMultiplier = Multiplicatior.getSelectionModel().getSelectedItem();
        int quantity = QuantityField.getText().isEmpty() ? 0 : Integer.parseInt(QuantityField.getText());
        
        int multiplier = selectedMultiplier.equals("Pcs") ? 1 : 64;
        
        System.out.println("You selected " + quantity*multiplier + " " + selectedItem + "(s)");
        
        Map<String, Integer> ingredients = RecipeLoader.getRecipe(selectedItem).getRecipe(quantity * multiplier);
        
        StringBuilder outputText = new StringBuilder();

        //outputText.append("You selected " + quantity*multiplier + " " + selectedItem + "(s):\n");
		for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
			outputText.append(entry.getKey() + ": " + entry.getValue() + "\n");
		}
		
		outputTextArea.setText(outputText.toString());
        
	}
}
