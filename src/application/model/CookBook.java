package application.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Thomas Herron hgo525
 *
 */

public class CookBook {

	private ArrayList<Recipe> recipes;
	
	
	public CookBook() {
		this.recipes = new ArrayList<Recipe>();
		
	} // end of constructor method
	
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
					System.out.println("Error: Unexpected number of tokens in data file.");
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
	
	
	
	public void addRecipe( Recipe temp ) {
		this.recipes.add(temp);
	} // end of addRecipe method
	
	public void addRecipe( int index, Recipe temp) {
		this.recipes.add(index, temp);
	} // end of addRecipe method
	
	
	public static String cleanString (String inString) {
		return inString.replaceAll("@", "(a)");
	}
	
	// Auto-generated getters and setters beyond this point
	/**
	 * @return the recipes
	 */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}

	/**
	 * @param recipes the recipes to set
	 */
	public void setRecipes(ArrayList<Recipe> recipes) {
		this.recipes = recipes;
	}


} // end of CookBook class
