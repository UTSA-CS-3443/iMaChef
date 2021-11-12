package application.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Recipe {

	private ArrayList<Step> steps;
	private ArrayList<String> tools;
	private ArrayList<Ingredient> ingredients;
	private String name;
	
	public Recipe(String name) {
		this.name = name;
		this.steps = new ArrayList<Step>();
		this.tools = new ArrayList<String>();
		this.ingredients = new ArrayList<Ingredient>();
		
	} // end of constructor method
	
	public void addStep (String[] tokens) {
		Step temp = Step.parseStep(tokens);
		addStep(temp);
		
	}
	
	public void addStep (Step temp) {
		this.steps.add(temp);
	}
	
	public ObservableList<String> getToolsAsList(String[] clues) {
		ObservableList<String> ret = FXCollections.observableArrayList();
		ret.removeAll(ret);
		int size = this.tools.size();
		for (int i = 0; i < size; i++) {
			if (tools.get(i) != null) {
				ret.add(tools.get(i));
			}
			
		} // end of for loop
				
		return ret;
	}
	
	public ObservableList<String> getIngredientsAsList(String[] clues) {
		ObservableList<String> ret = FXCollections.observableArrayList();
		ret.removeAll(ret);
		for (Ingredient temp : ingredients) {
			String desc = temp.getName() + " - " + temp.getAmount() + " " + temp.getUnit();
			ret.add(desc);
						
		} // end of for loop
				
		return ret;
	}
	
	// Auto-generated getters and setters beyond this point

	/**
	 * @return the steps
	 */
	public ArrayList<Step> getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

	/**
	 * @return the tools
	 */
	public ArrayList<String> getTools() {
		return tools;
	}

	/**
	 * @param tools the tools to set
	 */
	public void setTools(ArrayList<String> tools) {
		this.tools = tools;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
	
	
	
} // end of Recipe class
