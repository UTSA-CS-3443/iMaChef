package application.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import application.Main;
import application.model.Step;
import javafx.application.Platform;
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
import javafx.stage.WindowEvent;

public class CookController implements EventHandler<ActionEvent>, Initializable {

	private Step currentStep;
	
	private int stepNumber;
	
	private int repeatNumber;
	
	private int duration;
	
	private int postDuration;
	
	private MediaPlayer mplayer;
	
	private Timer timer;
	
	@FXML
	private Button buttonAuto;
	
	@FXML
	private Pane mediaPane;
	
	@FXML
	private Pane timerPane;
	
	@FXML
	private TextFlow tfDesc;
	
	@FXML
	private TableView<Step> tableSteps;
	
	@FXML
	private Label timeLeft;
	
	@FXML
	private Label postLabel;
	
	
	/**
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stepNumber = 0;
		currentStep = Main.currentRecipe.getSteps().get(stepNumber);
		repeatNumber = currentStep.getRepeat();
		postDuration = currentStep.getTimerInMilli();
		timer = new Timer();
		if (Main.auto) {
			startStepTimer();
			Image auto = new Image("file:images/auto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(auto));
		} else {
			timeLeft.setText("");
			Image noauto = new Image("file:images/noauto.png", 50, 50, true, false);
			buttonAuto.setGraphic(new ImageView(noauto));
		}
		postLabel.setText("");
		
		tfDesc.getChildren().clear();
		Text desc = new Text(currentStep.getDesc());
		tfDesc.getChildren().add(desc);
		Main.currentRecipe.setStepsAsTableView(tableSteps);
		resetMedia();
		
		Main.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	          public void handle(WindowEvent we) {
	        	  timer.cancel();
	          }
		}); 
		
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
		
		Platform.runLater(() -> postLabel.setText("Auto-Play will move to the next step in:"));
		
		timer.schedule(new TimerTask(){
			
			@Override
			public void run() {
				if (Main.auto) {
					updateStep();
				}
			}
			}, duration);
		
		timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            if(duration > 0)
	            {
	                int hours, minutes, seconds;
	                hours = duration / (1000*60*60);
	                minutes = (duration - (hours * (1000*60*60))) / (1000 * 60);
	                seconds = (duration - (hours * (1000*60*60)) - (minutes * (1000*60)) ) / 1000;
	                String timeOut = String.format("%d:%02d:%02d", hours, minutes, seconds);
	            	Platform.runLater(() -> timeLeft.setText(timeOut));
	                
	                duration -= 1000;
	            }
	            else
	                
	            	Platform.runLater(() -> timeLeft.setText("00:00:00"));
		        }
		}, 1000,1000);
		
		
	} // end of startStepTimer
	
	private void startPostTimer() {
		
		Platform.runLater(() -> postLabel.setText("There's a timer before you start the next step. Relax for a bit!"));
		
		timer.schedule(new TimerTask(){
			
			@Override
			public void run() {
				if (Main.auto) {
					
					updateStep();
				}
			}
		}, postDuration);
		
		timer.scheduleAtFixedRate(new TimerTask() {
	        public void run() {
	            if(postDuration > 0)
	            {
	                int hours, minutes, seconds;
	                hours = postDuration / (1000*60*60);
	                minutes = (postDuration - (hours * (1000*60*60))) / (1000 * 60);
	                seconds = (postDuration - (hours * (1000*60*60)) - (minutes * (1000*60)) ) / 1000;
	                String timeOut = String.format("%d:%02d:%02d", hours, minutes, seconds);
	            	Platform.runLater(() -> timeLeft.setText(timeOut));
	                
	                postDuration -= 1000;
	            }
	            else
	                
	            	Platform.runLater(() -> timeLeft.setText("00:00:00"));
		        }
		}, 1000,1000);
		
		
	} // end of startPostTimer
	
	private void updateStep() {
		if (mplayer != null) {
			mplayer.stop();
		}
		Platform.runLater(() -> timeLeft.setText(""));
		Platform.runLater(() -> postLabel.setText(""));
		timer.cancel();
		timer = new Timer();
		
		if (postDuration > 0) {
			startPostTimer();
			
		} else {		
		
			if (repeatNumber > 0) {
				repeatNumber--;
				postDuration = currentStep.getTimerDuration();
				
			} else {
				stepNumber++;
				if (stepNumber > -1 && stepNumber < (Main.currentRecipe.getSteps().size()) ) {
					currentStep = Main.currentRecipe.getSteps().get(stepNumber);
					repeatNumber = currentStep.getRepeat();
					postDuration = currentStep.getTimerInMilli();
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
					
			if (Main.auto) {
				startStepTimer();
			} 
			
			Platform.runLater(new Runnable() {
		        @Override 
		        public void run() {
		        	resetMedia();
		        }
		    });
			
			
			Platform.runLater(new Runnable() {
		        @Override 
		        public void run() {
					tfDesc.getChildren().clear();
					Text desc = new Text(currentStep.getDesc());
					tfDesc.getChildren().add(desc);
		        }
			});
		} // end of else postDuration	
	} // end of updateStep

} // end of class
