package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * 
 * @author Thomas Herron hgo525
 * @author 
 * @author 
 * @author 
 *
 */

public class Main extends Application {
	
	public static Stage stage;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {


			Parent root = FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			primaryStage.setScene(new Scene(root, 800, 600));
			primaryStage.show();
			
			
			stage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}