package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.CookBook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController implements EventHandler<ActionEvent>, Initializable{

	private int recipeSelected;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if (Main.cookBook == null) {
			Main.cookBook = CookBook.loadCookBook(Main.DATA_FILE);
		}
		recipeSelected = -1; // safety value to make sure a recipe was selected
		
	}

	
	/**
	 * @param event clicking the "Let's Cook!" button
	 */
	@Override
	public void handle(ActionEvent event) {
			
		try {
			// TODO: check the cookbook listView and set currentRecipe to the selected index
			
			
			
			
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Prep.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * @param event clicking the "Edit" button
	 */
	public void handleEdit(ActionEvent event) {
		try {
			
			// TODO: check the cookbook listView and set currentRecipe to the selected index
			
			
			
			
			
			
			
			// Back-up code in case we enter Edit without a selected recipe. This will have the same outcome
			// as hitting "Create new recipe"
			if (recipeSelected == -1) {
				Main.currentRecipe = null;
			}
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/PreEdit.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
