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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Main.cookBook = CookBook.loadCookBook(Main.DATA_FILE);
		
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleEdit(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/PreEdit.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
