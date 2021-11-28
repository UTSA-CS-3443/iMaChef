package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

import application.Main;
import application.model.Step;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class CookController implements EventHandler<ActionEvent>, Initializable {

	private Step currentStep;
	
	private int stepNumber;
	
	private int repeatNumber;
	
	private int duration;
	
	private MediaPlayer mplayer;
	
	@FXML
	private Button buttonAuto;
	
	@FXML
	private Pane mediaPane;
	
	@FXML
	private TextFlow tfDesc;
	
	@FXML
	private TableView<Step> tableSteps;
	
	
	/**
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stepNumber = 0;
		currentStep = Main.currentRecipe.getSteps().get(stepNumber);
		repeatNumber = currentStep.getRepeat();
		if (Main.auto) {
			startStepTimer();
			Image auto = new Image("file:images/auto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(auto));
		} else {
			Image noauto = new Image("file:images/noauto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(noauto));
		}
		
		tfDesc.getChildren().clear();
		Text desc = new Text(currentStep.getDesc());
		tfDesc.getChildren().add(desc);
		Main.currentRecipe.setStepsAsTableView(tableSteps);
		resetMedia();
		
	}

	/**
	 *  handle method handles clicking the QUIT button
	 */
	@Override
	public void handle(ActionEvent event) {
		try {
			if (mplayer != null) {
				mplayer.stop();
			}			
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void handleBack(ActionEvent event) {
		
		if (repeatNumber < currentStep.getRepeat()) {
			repeatNumber+= 2;
		} else {
			stepNumber -= 2;
		}
		
		if (stepNumber < -1) {
			if (mplayer != null) {
				mplayer.stop();
			}
			try {
				
				Parent root = FXMLLoader.load(getClass().getResource("../view/Prep.fxml"));
				Main.stage.setScene(new Scene(root, 800, 600));
				Main.stage.show();
		
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			updateStep();
		}
		
	}
	
	public void handleNext(ActionEvent event) {
		updateStep();
		
	}
	
	public void handleAuto(ActionEvent event) {
		if (Main.auto) {
			Main.auto = false;
			Image noauto = new Image("file:images/noauto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(noauto));
		} else {
			Main.auto = true;
			Image auto = new Image("file:images/auto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(auto));
			startStepTimer();
		}
		
	}
	
	private void resetMedia() {
		if (mplayer != null) {
			mplayer.stop();
		}
		mediaPane.getChildren().clear();
		if (currentStep.getMediaType().equals("image")) {
			Image stepImg = new Image("file:" + currentStep.getMediaPath());
			ImageView stepIV = new ImageView(stepImg);
			stepIV.fitHeightProperty().bind(mediaPane.heightProperty());
			stepIV.fitWidthProperty().bind(mediaPane.widthProperty());
			mediaPane.getChildren().add(stepIV);
		} else if (currentStep.getMediaType().equals("video")) {
			Media vid = new Media(new File(currentStep.getMediaPath()).toURI().toString());
			mplayer = new MediaPlayer(vid);
			mplayer.setAutoPlay(true);
			MediaView mview = new MediaView(mplayer);
			mview.fitHeightProperty().bind(mediaPane.heightProperty());
			mview.fitWidthProperty().bind(mediaPane.widthProperty());
			mediaPane.getChildren().add(mview);
		}
		
	}
	
	private void startStepTimer() {
		duration = currentStep.getDurationInMilli();
		
		
	}
	
	private void updateStep() {
		if (mplayer != null) {
			mplayer.stop();
		}
		
		if (repeatNumber > 0) {
			repeatNumber--;
			
		} else {
			stepNumber++;
		}
		
		if (stepNumber > -1 && stepNumber < (Main.currentRecipe.getSteps().size()) ) {
			currentStep = Main.currentRecipe.getSteps().get(stepNumber);
			if (Main.auto) {
				startStepTimer();
			} 
			resetMedia();
			tfDesc.getChildren().clear();
			Text desc = new Text(currentStep.getDesc());
			tfDesc.getChildren().add(desc);
		} else if (stepNumber >= Main.currentRecipe.getSteps().size()) {
			try {
				
				Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
				Main.stage.setScene(new Scene(root, 800, 600));
				Main.stage.show();
		
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
