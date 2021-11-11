package application.model;

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
	
	public String toData(Step temp) {
		String ret = "";
		ret += this.name + "|";
		ret += this.desc + "|";
		ret += String.valueOf(this.stepDuration) + "|";
		ret += this.stepDurationType + "|";
		ret += this.mediaPath + "|";
		ret += this.mediaType + "|";
		ret += String.valueOf(this.timerDuration) + "|";
		ret += this.timerDurationType + "|";
		ret += String.valueOf(this.repeat);
		ret += "\n";
		
		return ret;
	}
	
	
} // end of Step class
