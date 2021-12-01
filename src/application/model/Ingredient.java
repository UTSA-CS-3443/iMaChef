package application.model;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
/**
 * 
 * Allows for creation of an Ingredient which has a name, amount, unit, amountAndUnit, and a CheckBox
 * Allows the user to create an Ingredient using the name, amount, and unit.
 * @author Thomas Herron hgo525
 * @author dylan tran (lvw272)
 */
public class Ingredient {

	private String name;
	private double amount;
	private String unit;
	private String amountAndUnit;
	private CheckBox checkBox;
	
	/**
	 * constructor method for Ingredient objects
	 * @param name of the ingredient
	 * @param amount of the ingredient's units as a double
	 * @param unit name as a string
	 */
	public Ingredient(String name, double amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
		this.amountAndUnit = "" + amount + " " + unit; 
		this.checkBox = new CheckBox();
	}

	/**
	 * toData method returns the ingredient as a formatted string for storing in a CookBook .dat file
	 * @return String representing the ingredient, formatted for a CookBook .dat file
	 */
	public String toData() {
		String ret = this.name + "@" + String.valueOf(this.amount) + "@" + this.unit;
		return ret;
		
	}
	
	/**
	 * loadIngredientChoiceBox loads a ChoiceBox with a list of common ingredient unit Strings
	 * @param ChoiceBox<String> cb the ChoiceBox to be loaded 
	 */
	public static void loadIngredientChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("ea", "tsp", "/2 tsp", "Tbsp", "oz", "lb", "grams", "pinch", "cup", "/2 cup", "/3 cup", "/4 cup", "can", "oz can", "clove"));
	}
	
		
	/**
	 * getter method that returns the requested variable
	 * @return String, the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter method that sets the requested variable to the parameter provided
	 * @param name, the ingredient name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter method that returns the requested variable
	 * @return double, the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * setter method that sets the requested variable to the parameter provided
	 * @param double, Sets the amount to userInput
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * getter method that returns the requested variable
	 * @return String, the unit type as a String
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * setter method that sets the requested variable to the parameter provided
	 * @param String, Sets the unit to userInput
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * getter method that returns the requested variable
	 * @return String, the amountAndUnit combined String
	 */
	public String getAmountAndUnit() {
		return amountAndUnit;
	}

	/**
	 * setter method that sets the requested variable to the parameter provided
	 * @param String, Sets the AmountAndUnit the userInput
	 */
	public void setAmountAndUnit(String amountAndUnit) {
		this.amountAndUnit = amountAndUnit;
	}
	
	/**
	 * getter method that returns the requested CheckBox
	 * @return CheckBox, the checkBox
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}
	
	/** setter method that sets the requested variable to the object provided
	 * @param CheckBox, Sets checkBox to the object parameter provided
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
	
	
}
