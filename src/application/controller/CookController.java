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

/**
 * CookController class handles interaction with the Cook view. It manages media display, auto-play timers
 * and post-step timers.
 * @author Thomas Herron hgo525
 *
 */
public class CookController implements EventHandler<ActionEvent>, Initializable {

	private Step currentStep;
	
	private int stepNumber;
	
	private int repeatNumber;
	
	private int duration;
	
	private int postDuration;
	
	private MediaPlayer mplayer;
	
	private Timer timer;
	
	private boolean exited;
	
	private boolean skipthrough;
	
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
	 * initialize method handle pre-launch view operations.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		stepNumber = 0;
		currentStep = Main.currentRecipe.getSteps().get(stepNumber);
		repeatNumber = currentStep.getRepeat();
		postDuration = currentStep.getTimerInMilli();
		timer = new Timer();
		exited = false;
		skipthrough = false;
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
	 *  @param event clicking the QUIT button
	 */
	@Override
	public void handle(ActionEvent event) {
		try {
			if (mplayer != null && mplayer.getStatus() != MediaPlayer.Status.STOPPED) {
				mplayer.stop();
			}			
			exited = true;
			Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
			Main.stage.setScene(new Scene(root, 800, 600));
			Main.stage.show();
	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * handleBack method handles clicking the BACK button. It returns the user to the Prep view
	 * if they attempt to go back from the first step.
	 * @param event clicking the BACK button
	 */
	public void handleBack(ActionEvent event) {
		
		if (repeatNumber < currentStep.getRepeat()) {
			repeatNumber+= 2;
		} else {
			stepNumber -= 2;
		}
		
		if (stepNumber < -1) {
			if (mplayer != null && mplayer.getStatus() != MediaPlayer.Status.STOPPED) {
				mplayer.stop();
			}
			try {
				exited = true;
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
	
	/**
	 * handleNext method handles clicking the NEXT button
	 * @param event clicking the NEXT button
	 */
	public void handleNext(ActionEvent event) {
		updateStep();
		
	}
	
	
	/**
	 * handleAuto button handles clicking the Auto button by toggling auto-play mode on and off
	 * @param event clicking the AUTO button
	 */
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
	
	/**
	 * resetMedia method handles resetting the previous media being displayed and loading the new
	 * media for the current step.
	 */
	private void resetMedia() {
		if (mplayer != null && mplayer.getStatus() != MediaPlayer.Status.STOPPED) {
			mplayer.stop();
		}
		
		if ( ! exited) {
			mediaPane.getChildren().clear();
			mplayer = null;
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
		
	}
	
	/**
	 * startStepTimer method runs the auto-play timer for the current step and advances to the
	 * next step if auto-play hasn't been disable at the time the timer completes. It also manages
	 * the countdown timer display.
	 */
	private void startStepTimer() {
		duration = currentStep.getDurationInMilli();
		
		Platform.runLater(() -> postLabel.setText("Auto-Play will move to the next step in:"));
		
		timer.schedule(new TimerTask(){
			
			@Override
			public void run() {
				if (Main.auto) {
					Platform.runLater(() -> updateStep());
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
	
	/**
	 * the startPostTimer method handles post-step delay timers and manages the countdown timer
	 * display.
	 */
	private void startPostTimer() {
		
		Platform.runLater(() -> postLabel.setText("There's a timer before you start the next step. Relax for a bit!"));
		skipthrough = true;
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
	
	/**
	 * updateStep method manages advancing to the next step in the recipe, depending on the
	 * number of step repeats left and whether there is a post-step delay timer. It returns 
	 * to main menu if they user attempts to advance past the last step.
	 */
	private void updateStep() {
		if (mplayer != null && mplayer.getStatus() != MediaPlayer.Status.STOPPED) {
			mplayer.stop();
		}
		Platform.runLater(() -> timeLeft.setText(""));
		Platform.runLater(() -> postLabel.setText(""));
		timer.cancel();
		timer = new Timer();
		
		if (postDuration > 0 && skipthrough == false) {
			startPostTimer();
			
		} else {		
		
			if (repeatNumber > 0) {
				repeatNumber--;
				postDuration = currentStep.getTimerInMilli();
				skipthrough = false;
				
			} else {
				stepNumber++;
				if (stepNumber > -1 && stepNumber < (Main.currentRecipe.getSteps().size()) ) {
					currentStep = Main.currentRecipe.getSteps().get(stepNumber);
					repeatNumber = currentStep.getRepeat();
					postDuration = currentStep.getTimerInMilli();
					skipthrough = false;
				} else if (stepNumber >= Main.currentRecipe.getSteps().size()) {
					try {
						Main.auto = false;
						exited = true;
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
