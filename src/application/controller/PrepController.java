package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PrepController implements EventHandler<ActionEvent>, Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param event clicking the Quit button
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
	 */
	public void handleAuto(ActionEvent event) {
		Main.auto=true;
				
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Cook.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
 	
}
