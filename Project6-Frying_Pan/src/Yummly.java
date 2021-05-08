//Jianqiao Gao 27th/April
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Yummly {
	
	// P A R S E  D A T A 
	
	// TITLE

	public static String YummlyTitle(String topicUrl) {
		String s = "";

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements title = link.select("h1");

			title.text();

			for(Element e: title) {
				s = e.wholeText();
			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// DIFFICULTY

	public static String YummlyDifficulty(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Element procedure = link.getElementsByClass("o-RecipeInfo__a-Description").get(0);

			String proc = "Difficulty: " + procedure.text();

			return proc;

		}
		catch (IOException e) {
			return "Not found.";
		}		
	}

	// TIME

	public static String YummlyTime(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Element time = link.getElementsByClass("o-RecipeInfo__a-Description").get(1);

			String t = "Duration: " + time.text();

			return t;

		}
		catch (IOException e) {
			return "Not found.";
		}	
	}

	// SERVING 

	public static String YummlyServing(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Element servings = link.getElementsByClass("o-RecipeInfo__a-Description").get(3);

			String serv = "Servings: " + servings.text();

			return serv;

		}
		catch (IOException e) {
			return "Not found.";
		}	

	}

	// OVERVIEW

	public static String YummlyOverview(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements overview = link.getElementsByClass("o-AssetDescription__a-Description");

			String view = "";

			for(Element e: overview) {
				view = e.wholeText();
			}

			return view;

		}
		catch (IOException e) {
			return "Not found.";
		}	

	}

	// INGREDIETNS

	public static String YummlyIngredients(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements ingredients = link.getElementsByClass("o-Ingredients__a-Ingredient");

			String ingr = "";

			int i = 0;
			boolean x = false;

			for(Element e: ingredients) {
				if(x) {
					ingr = i + ". " + e.wholeText();
				}
				x = true;
				i++;
			}

			return ingr;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// PROCEDURE

	public static String YummlyProcedure(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements procedure = link.select("ol").select("li");

			String proc = "";

			int i = 1;

			for(Element e: procedure) {
				proc = i + ". " + e.wholeText();
				i++;
			}

			return proc;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	///////
	///////
	
	// L I S T S 
	
	// Chicken Ingredients
	
	public static String[] ChickenIngred(String ChickenUrl) {
		
		String s = "";
		ArrayList<String> r = new ArrayList<String>();
		
		try {
			Document link = Jsoup.connect(ChickenUrl).get();
			Elements list = link.getElementsByClass("card-title two-line-truncate p2-text font-normal");
			
			for(Element e: list) {
				r.add(e.text());
			}
			
			String[] recipes = new String[r.size()];
			r.toArray(recipes);

			return recipes;

		}
		catch (IOException e) {
			System.out.println("Not found.");
			String[] n = {"Not found."};
			return n;
		}
	}
	
	/////////
	/////////
	
	// O T H E R

	// Links

	public static String YummlyRecipeLinks(int index, String type, String dish) {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			String topicUrl = "https://www.yummly.com/recipes?q=best+chicken";		

			Document link = Jsoup.connect(topicUrl).get();
			Element name = link.getElementsByClass("card-info-wrapper flex-row").get(index);
			Element url = name.tagName("a href");

			s = url.html();
			s = s.substring(s.indexOf("/recipe/"), s.indexOf(">" + dish + "<") - 1);
			s = "http://yummly.com" + s;
			System.out.println(s);

			return s;
		}
		catch (IOException e) {
			System.out.println("Not found.");
			String n = "Not found.";
			return n;
		}
	} 
	
	
	public static void main(String[] args) {

		String YummlyChickenUrl = "https://www.yummly.com/recipes?q=best+chicken";
		
//		ChickenIngred(YummlyChickenUrl);
		YummlyRecipeLinks(0, "Chicken", "Southern Smothered Chicken");

		//		YummlyTitle(YummlyUrl);
		//		YummlyDifficulty(YummlyUrl);
		//		YummlyTime(YummlyUrl);
		//		YummlyServing(YummlyUrl);
		//		YummlyOverview(YummlyUrl);
		//		YummlyIngredients(YummlyUrl);
		//		YummlyProcedure(YummlyUrl);		
	}
	
}
