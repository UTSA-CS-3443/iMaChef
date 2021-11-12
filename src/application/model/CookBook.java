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
			
			while (sc.hasNextLine()) {
				String inLine = sc.nextLine();
				
				String[] tokens = inLine.split("`");
				
				if(tokens.length == 1) {
					if (temp != null) {
						ret.addRecipe(temp);
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
				}
							
				
			} // end while loop
			
			sc.close();
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
		
		return ret;
	} // end of loadCookBook method
	
	public void writeCookBook(String filePath, CookBook cookBook) {
		
		try {
			// Shift current file to bak and delete old bak
			File oldFile = new File(filePath);
			File oldBak = new File(filePath + ".bak");
			oldBak.delete();
			File newBak = new File(filePath + ".bak");
			oldFile.renameTo(newBak);
			
			FileWriter outFile = new FileWriter( filePath );
			PrintWriter outPrint = new PrintWriter(outFile);
			
			for (Recipe tempRec : cookBook.getRecipes()) {
				outPrint.print(tempRec.getName());
				for (Ingredient ing : tempRec.getIngredients()) {
					outPrint.print(ing.toData());
				}
				for (String tool : tempRec.getTools()) {
					outPrint.print("tool`" + tool);
				}
				for (Step tempStep : tempRec.getSteps()) {
					outPrint.print(tempStep.toData());
				}
			}
			
			outPrint.close();
			outFile.close();
			
		} catch( IOException e ) {
			e.printStackTrace();
		}
		
	}
	
	// Auto-generated getters and setters beyond this point
	
	public void addRecipe( Recipe temp ) {
		this.recipes.add(temp);
	} // end of addRecipe method
	
	
	public static String cleanString (String inString) {
		return inString.replaceAll("`", "'");
	}
	

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
