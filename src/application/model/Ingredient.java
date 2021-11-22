package application.model;

import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

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

	public String toData() {
		String ret = this.name + "`" + String.valueOf(this.amount) + "`" + this.unit;
		return ret;
		
	}
	

	public static void loadIngredientChoiceBox(ChoiceBox<String> cb) {
		cb.setItems(FXCollections.observableArrayList("ea", "tsp", "/2 tsp", "Tbsp", "oz", "lb", "grams", "pinch", "cup", "/2 cup", "/3 cup", "/4 cup", "can", "oz can", "clove"));
	}
	
		
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * @return the amountAndUnit
	 */
	public String getAmountAndUnit() {
		return amountAndUnit;
	}

	/**
	 * @param amountAndUnit the amountAndUnit to set
	 */
	public void setAmountAndUnit(String amountAndUnit) {
		this.amountAndUnit = amountAndUnit;
	}
	
	/**
	 * @return the checkBox
	 */
	public CheckBox getCheckBox() {
		return checkBox;
	}
	
	/**
	 * @param checkBox the checkBox to set
	 */
	public void setCheckBox(CheckBox checkBox) {
		this.checkBox = checkBox;
	}
	
	
	
}
