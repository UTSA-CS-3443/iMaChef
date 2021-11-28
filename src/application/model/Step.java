package application.model;

import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class Step {

	private String name;
	private String desc;
	private int stepDuration;
	private String stepDurationType; // TODO: change durType to enum ?
	private String mediaPath;
	private String mediaType; // TODO: change medaiType to enum ?
	private int timerDuration;
	private String timerDurationType;
	private int repeat;
	
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
	
	public static Step parseStep(String[] tokens) {
		Step ret;
		if (tokens.length != 9) {
			System.out.println("ERROR: Unexpected token count in parseStep method");
		}
		ret = new Step(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4], tokens[5], Integer.parseInt(tokens[6]), tokens[7], Integer.parseInt(tokens[8]));
		
		return ret;
	} // end of parseStep method
	
	public String toData() {
		String ret = "";
		ret += this.name + "`";
		ret += this.desc + "`";
		ret += String.valueOf(this.stepDuration) + "`";
		ret += this.stepDurationType + "`";
		ret += this.mediaPath + "`";
		ret += this.mediaType + "`";
		ret += String.valueOf(this.timerDuration) + "`";
		ret += this.timerDurationType + "`";
		ret += String.valueOf(this.repeat);
		
		
		return ret;
	}
	
	public static void setDurationChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("sec", "min", "hr"));
		
		
	}
	
	public static void setMediaChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("image", "video"));
		
	}
	
	public String getTime() {
		String ret = String.valueOf(getStepDuration()) + getStepDurationType();
		return ret;
	}
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the stepDuration
	 */
	public int getStepDuration() {
		return stepDuration;
	}

	/**
	 * @param stepDuration the stepDuration to set
	 */
	public void setStepDuration(int stepDuration) {
		this.stepDuration = stepDuration;
	}

	/**
	 * @return the stepDurationType
	 */
	public String getStepDurationType() {
		return stepDurationType;
	}

	/**
	 * @param stepDurationType the stepDurationType to set
	 */
	public void setStepDurationType(String stepDurationType) {
		this.stepDurationType = stepDurationType;
	}

	/**
	 * @return the mediaPath
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * @param mediaPath the mediaPath to set
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * @param mediaType the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the timerDuration
	 */
	public int getTimerDuration() {
		return timerDuration;
	}

	/**
	 * @param timerDuration the timerDuration to set
	 */
	public void setTimerDuration(int timerDuration) {
		this.timerDuration = timerDuration;
	}

	/**
	 * @return the timerDurationType
	 */
	public String getTimerDurationType() {
		return timerDurationType;
	}

	/**
	 * @param timerDurationType the timerDurationType to set
	 */
	public void setTimerDurationType(String timerDurationType) {
		this.timerDurationType = timerDurationType;
	}

	/**
	 * @return the repeat
	 */
	public int getRepeat() {
		return repeat;
	}

	/**
	 * @param repeat the repeat to set
	 */
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	
	
} // end of Step class
