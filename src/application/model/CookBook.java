package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * CookBook class handles storing, loading, saving, and backing-up CookBooks and data files storing cookbooks.
 * It includes methods for sanitizing strings prior to saving in CookBook .dat files and methods for returning 
 * objects needed to displaying cookbook information in JavaFX GUIs.
 * @author Thomas Herron hgo525
 * @author Marcus
 */

public class CookBook {

	private ArrayList<Recipe> recipes;
	
	/**
	 * CookBook empty constructor
	 */
	public CookBook() {
		this.recipes = new ArrayList<Recipe>();
		
	} // end of constructor method
	
	/**
	 * loadCookBook method reads a cookbook .dat file and returns a cookbook loaded with the formatted recipes
	 * in the file
	 * @param filePath path to the cookbook .dat file as a String
	 * @return a cookbook object containing the file's recipes
	 */
	public static CookBook loadCookBook ( String filePath ) {
		CookBook ret = new CookBook();
		
		try {
			File inFile = new File( filePath );
			Scanner sc = new Scanner ( inFile );
			Recipe temp = null;
			
			//TODO: comment out debug code
			if (inFile.exists()) {
				System.out.printf("File found at %s\n", filePath);
			} else {
				System.out.printf("File NOT FOUND at %s\n", filePath);
			}
			
			while (sc.hasNextLine()) {
				String inLine = sc.nextLine();
				
				String[] tokens = inLine.split("@");
				
				// TODO: comment out debug code
				System.out.printf("Got %d tokens\n", tokens.length);
				
				if(tokens.length == 1) {
					if (temp != null) {
						ret.addRecipe(temp);
						// TODO: Debugging code. Remove later
						System.out.println(temp.getName() + " loaded into cookbook");
					}
					temp = new Recipe(tokens[0]);
				} else if (tokens.length == 2) {
					temp.addTool(tokens);
				} else if (tokens.length == 3) {
					temp.addIngredient(tokens);
				} else if (tokens.length == 9) {
					// TODO confirm token length for step here
					temp.addStep(tokens);
				} else if (tokens.length == 0) {
					continue;
				} else {
					System.out.printf("Error: Unexpected number of tokens ( %d ) in data file: %s\n", tokens.length, Arrays.toString(tokens));
				}
							
				
			} // end while loop
			
			// Add the last recipe after no more lines to read
			if (temp != null) {
				ret.addRecipe(temp);
				// TODO: Debugging code. Remove later
				System.out.println(temp.getName() + " loaded into cookbook");
			}
			
			sc.close();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		
		return ret;
	} // end of loadCookBook method
	
	/**
	 * writeCookBook method saves the cookbook as the given file path string as a .bak file (replacing an existing
	 * .bak file if there is one) and saves the current CookBook in app memory as a .dat file at that location.
	 * @param filePath the file path to write the cookbook .dat file to
	 * @param cookBook the cookbook to save
	 */
	public static void writeCookBook(String filePath, CookBook cookBook) {
		
		try {
			// Shift current file to bak and delete old bak
			File oldFile = new File(filePath);
			System.out.printf("Opened %s\n", filePath);
			File oldBak = new File(filePath + ".bak");
			if (oldBak.delete()) {
				System.out.println("Deleted old bak file");
			} else {
				System.out.println("Failed to delete old bak file");
			}
			File newBak = new File(filePath + ".bak");
			if (oldFile.renameTo(newBak)) {
				System.out.println("Renamed previous dat to bak");
			} else {
				System.out.println("Failed to renamed previous dat to bak");
			}
			FileWriter outFile = new FileWriter( filePath );
			PrintWriter outPrint = new PrintWriter(outFile);
			
			for (Recipe tempRec : cookBook.getRecipes()) {
				outPrint.println(tempRec.getName());
				System.out.println(tempRec.getName());
				for (Ingredient ing : tempRec.getIngredients()) {
					outPrint.println(ing.toData());
					System.out.println(ing.toData());
				}
				for (String tool : tempRec.getTools()) {
					outPrint.println("tool@" + tool);
					System.out.println("tool@" + tool);
				}
				for (Step tempStep : tempRec.getSteps()) {
					outPrint.println(tempStep.toData());
					System.out.println(tempStep.toData());
				}
			}
			System.out.println("Write completed");
			outPrint.close();
			outFile.close();
			
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * getRecipesAsList method returns an observable list containing the cookbook's recipes
	 * @return an ObservableList of recipe names
	 */
	public ObservableList<String> getRecipesAsList() {
		ObservableList<String> ret = FXCollections.observableArrayList();
		ret.removeAll(ret);
		for (Recipe temp : recipes) {
			String desc = temp.getName();
			ret.add(desc);
						
		} // end of for loop
				
		return ret;
	}
	
	
	/**
	 * addRecipe method adds a recipe object to the cookbook's recipe ArrayList
	 * @param temp the recipe to add
	 */
	public void addRecipe( Recipe temp ) {
		this.recipes.add(temp);
	} // end of addRecipe method
	
	/**
	 * addReipe overloaded method adds the recipe to the cookbook's recipe ArrayList at the given index
	 * @param index the index to insert the recipe at
	 * @param temp the recipe to add
	 */
	public void addRecipe( int index, Recipe temp) {
		this.recipes.add(index, temp);
	} // end of addRecipe method
	
	/**
	 * cleanString method sanitizes a string to remove the character used as a delimiter in cookbook .dat files
	 * @param inString the string to be sanitized
	 * @return the sanitized string
	 */
	public static String cleanString (String inString) {
		return inString.replaceAll("@", "(a)");
	}
	
	// Auto-generated getters and setters beyond this point
	/**
	 * getRecipes is the getter method that returns the ArrayList of recipes
	 * @return the recipes ArrayList of recipes
	 */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * setRecipes is the getter method that sets the ArrayList of recipes
	 * @param recipes the recipes ArrayList of recipes to set
	 */
	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}


} // end of CookBook class
