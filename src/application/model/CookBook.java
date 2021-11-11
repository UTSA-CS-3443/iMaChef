package application.model;

import java.io.File;
import java.io.IOException;
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
				
				String[] tokens = inLine.split("|");
				
				if(tokens.length == 1) {
					if (temp != null) {
						ret.addRecipe(temp);
					}
					temp = new Recipe(tokens[0]);
				} else if (tokens.length == 9) {
					// TODO fix token length for step here
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
	
	public void addRecipe( Recipe temp ) {
		this.recipes.add(temp);
	} // end of addRecipe method


} // end of CookBook class
