package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.Ingredient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * 
 * @author dylan tran (lvw272)
 * Class that implements the EventHandler called ActionEvent and creates a TableView for the ingredients and a Label for all the tools needed.
 */
public class PrepController implements EventHandler<ActionEvent>, Initializable{
	@FXML
	TableView tableView;
	@FXML
	Label toolsLabel;
	
	/**
	 * @param URL and resources, ResourceBundle
	 * Runs once the view is changed to Prep View.
	 * Takes the data from Main and sets the table and label to the data
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		try {
			TableColumn ingredient = new TableColumn("Ingredient");
			TableColumn amount = new TableColumn("Amount");
			TableColumn checkBox = new TableColumn("Select");
			String tools = "";
		
			for(int i = 0; i < Main.currentRecipe.getTools().size(); i++) {
				tools += Main.currentRecipe.getTools().get(i) + " \n";
			}
			toolsLabel.setText(tools);
			tableView.getColumns().addAll(ingredient, amount, checkBox);
		
			ObservableList<Ingredient> data = FXCollections.observableArrayList(Main.currentRecipe.getIngredients());
		
			ingredient.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("name"));
			amount.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("amountAndUnit"));
			checkBox.setCellValueFactory(new PropertyValueFactory<Ingredient, String>("checkBox"));
			tableView.setItems(data);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param event clicking the Quit button
	 * Changes to Main view 
	 */
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
		
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param event clicking the "Let's Cook" button
	 * Changes to Cook View and sets auto in main to False
	 */
	public void handleCook(ActionEvent event) {
		Main.auto = false;
		
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Cook.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param event clicking the Auto button
	 * Changes to Cook View and sets auto in main to True
	 */
	public void handleAuto(ActionEvent event) {
		Main.auto=true;
		System.out.println("Auto selected");
				
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Cook.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
 	
}
