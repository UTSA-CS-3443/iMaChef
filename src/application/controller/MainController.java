package application.controller;

import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import application.Main;
import application.model.CookBook;
import application.model.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;


/**
 * MainController class handles interaction with the Main view
 * @author Thomas Herron hgo525
 * @author Marcus Lopez (xst253)
 */
public class MainController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	private ImageView mainImage;
	
	@FXML
    private ListView<String> recipeList;
	
	/**
	 * initialize method handle pre-launch view operations.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//rotate the main.fxml image
		
		//
		RotateTransition rotate = new RotateTransition();
	 	rotate.setNode(mainImage);
	 	rotate.setDuration(Duration.millis(1000));
	 	rotate.setCycleCount(1);
	 	rotate.setInterpolator(Interpolator.LINEAR);
	 	rotate.setByAngle(360);
	 	rotate.setAxis(Rotate.Z_AXIS);
	 	rotate.play();
	
		// TODO Auto-generated method stub
		if (Main.cookBook == null) {
			Main.cookBook = CookBook.loadCookBook(Main.DATA_FILE);
		}
		Main.recipeSelected = -1;
		recipeList.getItems().addAll(Main.cookBook.getRecipesAsList());
		
	}
	
	/**
	 * handle method handles clicking the LET'S COOK button
	 * @param event clicking the "Let's Cook!" button
	 */
	@Override
	public void handle(ActionEvent event) {
		if(Main.recipeSelected != -1 && Main.recipeSelected < Main.cookBook.getRecipes().size())
			{
				try
				{
					Main.currentRecipe = Main.cookBook.getRecipes().get(Main.recipeSelected);	
					Parent root = FXMLLoader.load(getClass().getResource("../view/Prep.fxml"));
					Main.stage.setScene(new Scene(root, 800, 600));
					Main.stage.show();
					
				}
				 catch(Exception e) {
					e.printStackTrace();
				}
			}
			
	} // end of handle method
	
	
	/**
	 * handleEdit method handles clicking the EDIT button
	 * @param event clicking the "Edit" button
	 */
	public void handleEdit(ActionEvent event) {
		try {
			
			if (Main.recipeSelected == -1) {
				Main.currentRecipe = null;
			}
			else
			{
				Main.currentRecipe = Main.cookBook.getRecipes().get(Main.recipeSelected);
			}
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/PreEdit.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * handleAddRecipe button handles clicking the ADD RECIPE button
	 * @param event clicking the ADD RECIPE button
	 */
	public void handleAddRecipe(ActionEvent event) {
		try {
			
			Main.currentRecipe = null;
			Main.recipeSelected = -1;
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/PreEdit.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * handleList method handles clicking the recipe list and updates the variable
	 * tracking the currently selected recipe
	 * @param t clicking on the recipe listview
	 */
	public void handleList(MouseEvent t)
	{
		Main.recipeSelected = recipeList.getSelectionModel().getSelectedIndex();
	}
	

}
