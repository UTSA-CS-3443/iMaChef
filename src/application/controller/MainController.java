package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import application.model.CookBook;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

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

}
