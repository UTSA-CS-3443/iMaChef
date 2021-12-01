package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Recipe;
import application.model.CookBook;
import application.model.Ingredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * PreEditController class handles interactions with the PreEdit view
 * @author Thomas Herron hgo525
 *
 */
public class PreEditController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	private TextField textfieldName;
	
	@FXML
	private TextField textfieldIngName;
	
	@FXML
	private TextField textfieldIngAmt;
	
	@FXML
	private TextField textfieldTool;
	
	@FXML
	private ChoiceBox<String> cbIng;
	
	@FXML
	private Label labelError;
	
	@FXML
	private ListView<String> listIng;
	
	@FXML
	private ListView<String> listTool;
	
	private int toolSelect;
	
	private int ingSelect;
	
	/**
	 * initialize method handle pre-launch view operations.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (Main.currentRecipe == null) {
			Main.currentRecipe = new Recipe("Unnamed New Recipe");
		} else {
			textfieldName.setText(Main.currentRecipe.getName());
			
		}
		listIng.getItems().addAll(Main.currentRecipe.getIngredientsAsList());
		listTool.getItems().addAll(Main.currentRecipe.getToolsAsList());
		// cbIng = Ingredient.getIngredientChoiceBox();
		cbIng.setValue("ea");
		Ingredient.loadIngredientChoiceBox(cbIng);
		
		toolSelect = -1;
		ingSelect = -1;
		
		textfieldIngName.setPromptText("Ingredient");
		textfieldIngAmt.setPromptText("Amount");
		textfieldTool.setPromptText("Tool");
	}

	/**
	 * handle method handles clicking the QUIT button
	 * @param event clicking the QUIT button
	 */
	@Override
	public void handle(ActionEvent event) {
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * handleNext handles clicking the NEXT button
	 * @param event clicking the NEXT button
	 */
	public void handleNext(ActionEvent event) {
		try {
			boolean isValid = true;
			String name = textfieldName.getText();
			if (name == null || name == "") {
				isValid = false;
				labelError.setText("Invalid input. Please fill all fields.");
			} else {
				name = CookBook.cleanString(name);
				Main.currentRecipe.setName(name);
			}
				
				
			if (isValid) {
				Parent root = FXMLLoader.load(getClass().getResource("../view/Edit.fxml"));
				Main.stage.setScene(new Scene(root, 800, 600));
				Main.stage.show();
			}
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * handleIngAdd method handles clicking the ADD button for the ingredient list
	 * @param event clicking the ADD button for the ingredient list
	 */
	public void handleIngAdd(ActionEvent event) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		String name = "";
		String amt = "";
		if (textfieldIngName.getText().isEmpty()) {
			isValid = false;
			labelError.setText("Please enter an ingredient first.");
		} else {
			name = CookBook.cleanString(textfieldIngName.getText());
		}
		
		if (textfieldIngAmt.getText().isEmpty()) {
			isValid = false;
			labelError.setText("Please enter an amount first.");
		} else {
			amt = textfieldIngAmt.getText();
		}
		
		if (isValid && (name == null || amt == null || name == "" || amt == "")) {
			isValid = false;
			labelError.setText("Invalid input. Please fill all fields.");
		}
		
		double amount = 0.0;
		try {
			double a = Double.parseDouble(amt);
			amount = a;
							
		} catch ( NumberFormatException e){
			isValid = false;
			if (amt != "") {
				labelError.setText("Please use only numbers in the 'Amount' field.");
			}
		}
		
		String unit = cbIng.getValue();
		
		if (isValid) {
			Main.currentRecipe.addIngredient(name, amount, unit);
			labelError.setText("");
			this.listIng.getItems().clear();
			listIng.getItems().addAll(Main.currentRecipe.getIngredientsAsList());
			textfieldIngName.setText("");
			textfieldIngName.setPromptText("Ingredient");
			textfieldIngAmt.setText("");
			textfieldIngAmt.setPromptText("Amount");
		}
						
	}
	
	/**
	 * handleIngDelete method handles clicking the DELETE button for the ingredient list
	 * @param event clicking the DELETE button for the ingredient list
	 */
	public void handleIngDelete(ActionEvent event) {
		// TODO Auto-generated method stub
		ingSelect = listIng.getSelectionModel().getSelectedIndex();
		if (ingSelect > -1 && ingSelect < Main.currentRecipe.getIngredients().size()) {
			Main.currentRecipe.getIngredients().remove(ingSelect);
			ingSelect = -1;
			this.listIng.getItems().clear();
			this.listIng.getItems().addAll(Main.currentRecipe.getIngredientsAsList());
		}
		
	}
	
	/**
	 * handleToolAdd method handles clicking the ADD button for the tool list
	 * @param event clicking the ADD button for the tool list
	 */
	public void handleToolAdd(ActionEvent event) {
		// TODO Auto-generated method stub
		boolean isValid = true;
		String t = "";
		if (textfieldTool.getText().isEmpty()) {
			isValid = false;
			this.labelError.setText("Please enter a tool to add.");
		} else {
			t = CookBook.cleanString(textfieldTool.getText());
		}
		if (isValid && (t == null || t == "")) {
			isValid = false;
			this.labelError.setText("Invalid input. Please fill all fields.");
		}
		
		if (isValid) {
			Main.currentRecipe.addTool(t);
			labelError.setText("");
			this.listTool.getItems().clear();
			this.listTool.getItems().addAll(Main.currentRecipe.getToolsAsList());
			textfieldTool.setText("");
			textfieldTool.setPromptText("Tool");
		}
	}
	
	/**
	 * handleToolDelete method handles clicking the DELETE button for the tool list
	 * @param event clicking the DELETE button for the tool list
	 */
	public void handleToolDelete(ActionEvent event) {
		
		toolSelect = listTool.getSelectionModel().getSelectedIndex();
	
		if (toolSelect > -1 && toolSelect < Main.currentRecipe.getTools().size()) {
			Main.currentRecipe.getTools().remove(toolSelect);
			toolSelect = -1;
			listTool.getItems().clear();
			listTool.getItems().addAll(Main.currentRecipe.getToolsAsList());
		}
		
	}
}
