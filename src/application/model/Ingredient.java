package application.model;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
/**
 * 
 * Allows for creation of an Ingredient which has a name, amount, unit, amountAndUnit, and a CheckBox
 * Allows the user to create an Ingredient using the name, amount, and unit.
 * 
 */
public class Ingredient {

	private String name;
	private double amount;
	private String unit;
	private String amountAndUnit;
	private CheckBox checkBox;
	
	
	public Ingredient(String name, double amount, String unit) {
		this.name = name;
		this.amount = amount;
		this.unit = unit;
		this.amountAndUnit = "" + amount + " " + unit; 
		this.checkBox = new CheckBox();
	}

	/**
	 * @return String, information
	 */
	public String toData() {
		String ret = this.name + "@" + String.valueOf(this.amount) + "@" + this.unit;
		return ret;
		
	}
	
	/**
	 * @param ChoiceBox<String> cb
	 * Creates a choice box using a observableArrayList 
	 */
	public static void loadIngredientChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("ea", "tsp", "/2 tsp", "Tbsp", "oz", "lb", "grams", "pinch", "cup", "/2 cup", "/3 cup", "/4 cup", "can", "oz can", "clove"));
	}
	
		
	/**
	 * @return String, the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param String, Sets the name to userInput
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return double, the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param double, Sets the amount to userInput
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return String, the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param String, Sets the unit to userInput
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return String, the amountAndUnit
	 */
	public String getAmountAndUnit() {
		return amountAndUnit;
	}

	/**
	 * @param String, Sets the AmountAndUnit the userInput
	 */
	public void setAmountAndUnit(String amountAndUnit) {
		this.amountAndUnit = amountAndUnit;
	}
	
	/**
	 * @return CheckBox, the checkBox
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}
	
	/**
	 * @param CheckBox, Sets checkBox to the userInput
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
	
	
}
