package application.controller;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import application.model.CookBook;
import application.model.Step;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * The EditController class handles interactions with the Edit view.
 * @author Thomas Herron hgo525
 *
 */
public class EditController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	private TableView<Step> tableSteps;
	
	@FXML
	private TextField tfName;
	
	@FXML
	private TextField tfDesc;
	
	@FXML
	private TextField tfStepDuration;
	
	@FXML
	private TextField tfFilePath;
	
	@FXML
	private TextField tfTimerDuration;
	
	@FXML
	private TextField tfRepeat;
	
	@FXML
	private ChoiceBox<String> cbStepDuration;
	
	@FXML
	private ChoiceBox<String> cbTimerDuration;
	
	@FXML
	private ChoiceBox<String> cbMedia;
	
	@FXML
	private ChoiceBox<String> cbLibrary;
	
	@FXML
	private CheckBox checkRepeat;
	
	@FXML
	private CheckBox checkTimer;
	
	@FXML
	private Label labelError;
	
	private int stepSelect;
	
	/**
	 * initialize method performs operations required before the GUI is displayed
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// tableSteps = Main.currentRecipe.getStepsAsTableView();
		Main.currentRecipe.setStepsAsTableView(tableSteps);
		cbStepDuration.setValue("min");
		Step.setDurationChoiceBox(cbStepDuration);
		cbTimerDuration.setValue("min");
		Step.setDurationChoiceBox(cbTimerDuration);
		cbMedia.setValue("image");
		Step.setMediaChoiceBox(cbMedia);
		stepSelect = -1;
		
	}

	
	/**
	 * handle method handles clicking of the BACK button by returning to the PreEdit view
	 * @param event clicking the Back button
	 */
	@Override
	public void handle(ActionEvent event) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/PreEdit.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * handleCBMedia method resets the text in the filePath textfield if the Media type 
	 * choice box is clicked to prevent type-mismatch
	 */
	public void handleCBMedia() {
		tfFilePath.setText("");
	}
	
	/**
	 * handleBrowse method handles clicking of the BROWSE button by opening a file chooser that only
	 * displays the type of media currently displayed in the media choice box
	 * @param event clicking the Browse button
	 */
	public void handleBrowse(ActionEvent event) {
		FileChooser fc= new FileChooser();
		 fc.setTitle("Open Media File");
		 if (cbMedia.getSelectionModel().getSelectedItem().equals("image")) {
			 fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		 } else {
			 fc.getExtensionFilters().addAll(new ExtensionFilter("Video Files", "*.mp4", "*.avi"));
		 }
		 String currentDir = "media";
		 File mediaDir = new File(currentDir);
		 fc.setInitialDirectory(mediaDir);
		 File selectedFile = fc.showOpenDialog(Main.stage);
		 if (selectedFile != null) {
		    tfFilePath.setText(selectedFile.getAbsolutePath());  
		 }

		
		
	}
	
	/**
	 * handleAdd button handles clicking the ADD button by validating user entries and adding
	 * the step to the recipe if valid. It also manages placement in the event a step is currently
	 * selected in the tableview
	 * @param event clicking the Add Step button
	 */
	public void handleAdd(ActionEvent event) {
		boolean isValid = true;
		boolean replace = false;
		String name;
		String desc;
		int stepDuration;
		String stepDurationType;
		String mediaPath;
		String mediaType;
		int timerDuration;
		String timerDurationType;
		int repeat;
		int addIndex = Main.currentRecipe.getSteps().size();
		Step temp;
		
		stepSelect = tableSteps.getSelectionModel().getSelectedIndex();
		if (stepSelect > -1 && stepSelect < Main.currentRecipe.getSteps().size()) { 
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Add Step");
			alert.setHeaderText("You currently have a step selected.");
			alert.setContentText("Where do you want to add this step? Choose your option.");
	
			ButtonType buttonTypeOne = new ButtonType("Before");
			ButtonType buttonTypeTwo = new ButtonType("After");
			ButtonType buttonTypeThree = new ButtonType("Add to end");
			ButtonType buttonTypeFour = new ButtonType("Replace");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeCancel);
	
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
			    addIndex = stepSelect;
			} else if (result.get() == buttonTypeTwo) {
				addIndex = stepSelect + 1;
			} else if (result.get() == buttonTypeThree) {
				addIndex = Main.currentRecipe.getSteps().size();
			} else if (result.get() == buttonTypeFour) {
				addIndex = stepSelect;
				replace = true;
			} else {
			    isValid = false;
			}
			
		} // end of pop-up if
		
		
		if (tfName.getText().isEmpty() || tfDesc.getText().isEmpty() || tfFilePath.getText().isEmpty() || (checkTimer.isSelected() && tfTimerDuration.getText().isEmpty())  
				|| (checkRepeat.isSelected() && tfRepeat.getText().isEmpty())) {
			isValid = false;
			labelError.setText("Please check that all fields are filled correctly");
		}
				
		if (isValid) {
			try {
				name = tfName.getText();
				desc = tfDesc.getText();
				
				if (tfStepDuration.getText().isEmpty()) {
					stepDuration = 1;
					stepDurationType = "min";
				} else {
					stepDuration = Integer.parseInt(tfStepDuration.getText());
					stepDurationType = cbStepDuration.getValue();
				}
				
				
				mediaPath = tfFilePath.getText();
				
				// System.out.printf("Path before clean: %s\n", mediaPath);
				int pathClean = mediaPath.indexOf("iMaChef\\media");
				if (pathClean != -1 ) {
					pathClean += 8;
					mediaPath = mediaPath.substring(pathClean);
					mediaPath = mediaPath.replace('\\', '/');
					// System.out.printf("Path after clean: %s\n", mediaPath);
				}
					
				mediaType = cbMedia.getValue();
				
				if (checkTimer.isSelected()) {
					timerDuration = Integer.parseInt(tfTimerDuration.getText());
				} else {
					timerDuration = 0;
				}
				timerDurationType = cbTimerDuration.getValue();
				
				if (checkRepeat.isSelected()) {
					repeat = Integer.parseInt(tfRepeat.getText());
				} else {
					repeat = 0;
				}
				
				if (replace) {
					Main.currentRecipe.getSteps().remove(addIndex);
				}
				
				if (isValid) {
					temp = new Step(name, desc, stepDuration, stepDurationType, mediaPath, mediaType, timerDuration, timerDurationType, repeat);
				
					Main.currentRecipe.getSteps().add(addIndex, temp);
					refreshView();
					stepSelect = -1;
				} else {
					labelError.setText("Please check that all fields are filled correctly");
				}
			
			} catch ( NumberFormatException e){
				isValid = false;
				labelError.setText("Please check that all fields are filled correctly");
			}
			
		} // end of if isValid		
		
	} // end of handleAdd method
	
	/**
	 * handleRemove handles clicking the Remove Step button
	 * @param event clicking the Remove Step button
	 */
	public void handleRemove(ActionEvent event) {
		stepSelect = tableSteps.getSelectionModel().getSelectedIndex();
		if (stepSelect > -1 && stepSelect < Main.currentRecipe.getSteps().size()) { 
			Main.currentRecipe.getSteps().remove(stepSelect);
			refreshView();
			stepSelect = -1;
		}
	}
	
	
	/**
	 * handleSave method handles clicking the DONE button by adding or overwriting the current
	 * recipe and calling the CookBook method to rewrite the .dat file.
	 * @param event clicking the DONE button
	 */
	public void handleSave(ActionEvent event) {
		
		if (Main.recipeSelected > -1 && Main.recipeSelected < Main.cookBook.getRecipes().size()) {
			Main.cookBook.getRecipes().remove(Main.recipeSelected);
			Main.cookBook.addRecipe(Main.recipeSelected, Main.currentRecipe);
		} else {
			Main.cookBook.addRecipe(Main.currentRecipe);
		}
		
		CookBook.writeCookBook(Main.DATA_FILE, Main.cookBook);
		
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * refreshView method resets all text fields and choiceboxes
	 */
	public void refreshView() {
		Main.currentRecipe.setStepsAsTableView(tableSteps);
		tfName.setText("");
		tfDesc.setText("");
		tfStepDuration.setText("");
		tfFilePath.setText("");
		tfTimerDuration.setText("");
		tfRepeat.setText("");
		cbStepDuration.setValue("min");
		cbTimerDuration.setValue("min");
		cbMedia.setValue("image");
		labelError.setText("");

		
	}

}
