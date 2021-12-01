package application.model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Recipe class stores data on Recipes and provides objects for JavaFX GUIs based on those
 * recipes.
 * @author Thomas Herron hgo525
 *
 */
public class Recipe {

	private ArrayList<Step> steps;
	private ArrayList<String> tools;
	private ArrayList<Ingredient> ingredients;
	private String name;
	
	/**
	 * Recipe constructor
	 * @param name the name of the recipe
	 */
	public Recipe(String name) {
		this.name = name;
		this.steps = new ArrayList<Step>();
		this.tools = new ArrayList<String>();
		this.ingredients = new ArrayList<Ingredient>();
		
	} // end of constructor method
	
	/**
	 * addStep method takes an array of tokens and processes it into a step and adds
	 * it to the recipe Step ArrayList
	 * @param tokens a String array representing the step, parsed from a CookBook .dat file
	 */
	public void addStep (String[] tokens) {
		Step temp = Step.parseStep(tokens);
		addStep(temp);
		
	}
	
	/**
	 * addStep overloaded method that adds the provided Step objecct to the Recipe's
	 * Step ArrayList
	 * @param temp the Step to add
	 */
	public void addStep (Step temp) {
		this.steps.add(temp);
	}
	
	/**
	 * addIngredient method takes an array of tokens and processes it into an Ingredient and adds
	 * it to the recipe Ingredient ArrayList
	 * @param tokens a String array representing the Ingredient, parsed from a CookBook .dat file
	 */
	public void addIngredient (String[] tokens) {
		Ingredient temp = new Ingredient(tokens[0], Double.parseDouble(tokens[1]), tokens[2]);
		this.ingredients.add(temp);
		
	}
	
	/**
	 * addIngredient overloaded method that adds an Ingredient to the Recipe's ArrayList
	 * @param name of the ingredient
	 * @param amount of ingredient, as a double
	 * @param unit pared with the amount, as a String
	 */
	public void addIngredient (String name, double amount, String unit) {
		Ingredient temp = new Ingredient(name, amount, unit);
		this.ingredients.add(temp);
		
	}
	
	/**
	 * addTool method extracts a tool from a String array to adds a tool to the Recipe's tool ArrayList
	 * @param tokens an array of Strings with a tool name, parsed from a CookBook .dat file
	 */
	public void addTool (String[] tokens) {
		this.tools.add(tokens[1]);
		
	}
	
	/**
	 * addTool overloaded method adds a tool to the Recipe's tool ArrayList
	 * @param tool the String representing the tool
	 */
	public void addTool (String tool) {
		this.tools.add(tool);
	}
	
	/**
	 * getToolsAsList method returns an ObservableList of the Recipe's tools ArrayList for use
	 * with JavaFX GUI elements
	 * @return an ObservableList<String> representing the Recipe's tool ArrayList
	 */
	public ObservableList<String> getToolsAsList() {
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
	
	/**
	 * getIngredientsAsList method returns an ObservableList of the Recipe's Ingredients ArrayList for use
	 * with JavaFX GUI elements
	 * @return an ObservableList<String> representing the Recipe's Ingredients ArrayList
	 */
	public ObservableList<String> getIngredientsAsList() {
		ObservableList<String> ret = FXCollections.observableArrayList();
		ret.removeAll(ret);
		for (Ingredient temp : ingredients) {
			String desc = temp.getName() + " - " + temp.getAmount() + " " + temp.getUnit();
			ret.add(desc);
						
		} // end of for loop
				
		return ret;
	}
	
	/**
	 * getStepsAsTableView method returns a TableView of the Recipe's Steps ArrayList for use
	 * with JavaFX GUI elements
	 * @return a TableView<Step> representing the Recipe's Steps ArrayList
	 */
	public TableView<Step> getStepsAsTableView() {
		TableView<Step> ret = new TableView<Step>();
		//ObservableList<Step> stepList = FXCollections.observableArrayList();
		
		TableColumn<Step, String> column1 = new TableColumn<>("Step");
	    column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		// column1.setCellValueFactory(cellData -> cellData.getValue().getName());

	    TableColumn<Step, String> column2 = new TableColumn<>("Time");
	    column2.setCellValueFactory(new PropertyValueFactory<>("time"));
	    // column2.setCellValueFactory(cellData -> cellData.getValue().getTime());

	    ret.getColumns().add(column1);
	    ret.getColumns().add(column2);

	    for (Step temp : steps) {
	    	ret.getItems().add(temp);
	    }
	 
				
		return ret;
	}
	
	/**
	 * setStepsAsTableView method initializes a TableView of the Recipe's Steps ArrayList for use
	 * with JavaFX GUI elements
	 * @param tableSteps the TableView to initialize
	 */
	public void setStepsAsTableView(TableView<Step> tableSteps) {
				
		TableColumn<Step, String> column1 = new TableColumn<>("Step");
	    column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		// column1.setCellValueFactory(cellData -> cellData.getValue().getName());

	    TableColumn<Step, String> column2 = new TableColumn<>("Time");
	    column2.setCellValueFactory(new PropertyValueFactory<>("time"));
	    // column2.setCellValueFactory(cellData -> cellData.getValue().getTime());
	    
	    
	    column1.setMinWidth(150);
	    column1.setMaxWidth(150);
	    column1.setSortable(false);
	    column2.setMinWidth(50);
	    column2.setMaxWidth(50);
	    column2.setSortable(false);
		
	    
	    tableSteps.getColumns().clear();
	    tableSteps.getColumns().add(column1);
	    tableSteps.getColumns().add(column2);
	    tableSteps.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    tableSteps.getItems().clear();
	    for (Step temp : steps) {
	    	tableSteps.getItems().add(temp);
	    }

	}
	
	
	// Auto-generated getters and setters beyond this point

	/**
	 * getter method that returns the requested ArrayList
	 * @return the steps ArrayList
	 */
	public ArrayList<Step> getSteps() {
		return steps;
	}

	/**
	 * setter method that sets the ArrayList to the given parameter
	 * @param steps the steps to set
	 */
	public void setSteps(ArrayList<Step> steps) {
		this.steps = steps;
	}

	/**
	 * getter method that returns the requested ArrayList
	 * @return the tools ArrayList
	 */
	public ArrayList<String> getTools() {
		return tools;
	}

	/**
	 * setter method that sets the ArrayList to the given parameter
	 * @param tools the tools to set
	 */
	public void setTools(ArrayList<String> tools) {
		this.tools = tools;
	}

	/**
	 * getter method that returns the requested ArrayList
	 * @return the ingredients ArrayList
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * setter method that sets the ArrayList to the given parameter
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * getter method that returns the requested variable
	 * @return the name String
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter method that sets the variable to the given parameter
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
} // end of Recipe class
