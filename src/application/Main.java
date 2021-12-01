package application;
	
import application.model.CookBook;
import application.model.Recipe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/** Main driver for iMaChef app. Launches the app GUI and stores static variables for the user's current
 * session with the app.
 * 
 * @author Thomas Herron hgo525
 * @author Dylan Tran (lvw272)
 * @author 
 * @author 
 *
 */

public class Main extends Application {
	
	public static Stage stage;
	public static CookBook cookBook;
	public static Recipe currentRecipe;
	public static int recipeSelected;
	public static final String DATA_FILE = "data/cookbook.dat";
	public static boolean auto;
	
	
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