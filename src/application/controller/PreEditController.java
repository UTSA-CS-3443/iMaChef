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
	private ListView listIng;
	
	@FXML
	private ListView listTool;
	
	private int toolSelect;
	
	private int ingSelect;
	
	
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
	}

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
			textfieldIngAmt.setText("");
		}
						
	}
	
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
		}
	}
	
		
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
