package application.model;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * Step class stores information on a Step of a Recipe. It also includes methods for initializing
 * JavaFX GUI elements related to Steps
 * @author Thomas Herron hgo525
 *
 */
public class Step {

	private String name;
	private String desc;
	private int stepDuration;
	private String stepDurationType; 
	private String mediaPath;
	private String mediaType; 
	private int timerDuration;
	private String timerDurationType;
	private int repeat;
	
	/**
	 * Step constructor method
	 * @param name of the Step
	 * @param desc Description of Step instructions as a String
	 * @param stepDuration duration the step should take to complete as an int
	 * @param stepDurationType unit of time for the stepDuration int, as a String
	 * @param mediaPath String with the filePath to the media object accompanying the step
	 * @param mediaType type of media found at the mediaPath address as a String (image or video)
	 * @param timerDuration duration of a post-step timer as an int
	 * @param timerDurationType unit of time for the timerDuration int, as a String
	 * @param repeat the number of times to repeat the step, as an int
	 */
	public Step(String name, String desc, int stepDuration, String stepDurationType, String mediaPath, String mediaType, int timerDuration, String timerDurationType, int repeat) {
		this.name = name;
		this.desc = desc;
		this.stepDuration = stepDuration;
		this.stepDurationType = stepDurationType;
		this.mediaPath = mediaPath;
		this.mediaType = mediaType;
		this.timerDuration = timerDuration;
		this.timerDurationType = timerDurationType;
		this.repeat = repeat;
		
		
	} // end of constructor method
	
	/**
	 * parseStep method converts an array of Strings taken from a CookBook .dat file and converts them into a Step object
	 * @param tokens a String array from a CookBook .dat file
	 * @return the Step object described in the provided String array
	 */
	public static Step parseStep(String[] tokens) {
		Step ret;
		if (tokens.length != 9) {
			System.out.println("ERROR: Unexpected token count in parseStep method");
		}
		ret = new Step(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7], Integer.parseInt(tokens[8]));
		
		return ret;
	} // end of parseStep method
	
	/**
	 * toData method returns a String representation of the Step, formatted for a CookBook .dat file
	 * @return the formatted String for storing the Step in a CookBook .dat file
	 */
	public String toData() {
		String ret = "";
		ret += this.name + "@";
		ret += this.desc + "@";
		ret += String.valueOf(this.stepDuration) + "@";
		ret += this.stepDurationType + "@";
		ret += this.mediaPath + "@";
		ret += this.mediaType + "@";
		ret += String.valueOf(this.timerDuration) + "@";
		ret += this.timerDurationType + "@";
		ret += String.valueOf(this.repeat);
		
		
		return ret;
	}
	
	/**
	 * setDurationChoiceBox method initializes a ChoiceBox with the String options using in Duration Strings
	 * @param cb the ChoiceBox to fill
	 */
	public static void setDurationChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("sec", "min", "hr"));
		
		
	}
	
	/**
	 * setMediaChoiceBox method initializes a ChoiceBox with the String options using in mediaType Strings
	 * @param cb the ChoiceBox to fill
	 */
	public static void setMediaChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("image", "video"));
		
	}
	
	/**
	 * getTime method returns a String representation of the Step duration
	 * @return String of the Step duration
	 */
	public String getTime() {
		String ret = String.valueOf(getStepDuration()) + getStepDurationType();
		return ret;
	}
	
	/**
	 * getDurationInMilli returns the Step's duration in milliseconds
	 * @return stepDuration in milliseconds
	 */
	public int getDurationInMilli() {
		double ret = stepDuration * 1000;
		if (stepDurationType.equals("min")) {
			ret = ret * 60;
		} else if (stepDurationType.equals("hr")) {
			ret = ret * 3600;
		}
		if (ret > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE - 1;
		} else {		
			return (int) ret;
		}
	}
	
	/**
	 * getTimerInMilli method returns the Step's post-Step timer duration in milliseconds
	 * @return the timerDuration in milliseconds
	 */
	public int getTimerInMilli() {
		double ret = timerDuration * 1000;
		if (timerDurationType.equals("min")) {
			ret = ret * 60;
		} else if (timerDurationType.equals("hr")) {
			ret = ret * 3600;
		}
		if (ret > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE - 1;
		} else {		
			return (int) ret;
		}
	}
	
	// Auto-generated getters and setters beyond this point

	/**
	 * getter method that returns the requested variable
	 * @return the name of the Step
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the description of the Step
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param desc the Step description or instructions to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the stepDuration as an int
	 */
	public int getStepDuration() {
		return stepDuration;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param stepDuration the stepDuration to set as an int
	 */
	public void setStepDuration(int stepDuration) {
		this.stepDuration = stepDuration;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the stepDurationType as a String
	 */
	public String getStepDurationType() {
		return stepDurationType;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param stepDurationType the stepDurationType to set as a String
	 */
	public void setStepDurationType(String stepDurationType) {
		this.stepDurationType = stepDurationType;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the mediaPath as a String
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param mediaPath the mediaPath to set as a String
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the mediaType as a String
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param mediaType the mediaType to set as a String
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the timerDuration
	 */
	public int getTimerDuration() {
		return timerDuration;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param timerDuration the timerDuration to set as an int
	 */
	public void setTimerDuration(int timerDuration) {
		this.timerDuration = timerDuration;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the timerDurationType as a String
	 */
	public String getTimerDurationType() {
		return timerDurationType;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param timerDurationType the timerDurationType to set as a String
	 */
	public void setTimerDurationType(String timerDurationType) {
		this.timerDurationType = timerDurationType;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the repeat count for the Step
	 */
	public int getRepeat() {
		return repeat;
	}

	/**
	 * setter method that sets the Step variable to the given parameter
	 * @param repeat the repeat count to set
	 */
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	
	
} // end of Step class
