// Khalid Siraj
// April 19, 2021
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JLabel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RecipeWebsites {

	// T A S T Y

	public static String TastyTitle(String topicUrl) {

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

	// TIME

	public static String TastyTime(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Element str = link.select("p").get(1);

			String time = str.text();

			if(time.indexOf("min") == -1)
				time = "–––Unavailable–––";

			return time;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// SERVING

	public static String TastyServing(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements servings = link.getElementsByClass("servings-display xs-text-2 xs-mb2");
			String serve = servings.text();

			return serve;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// OVERVIEW

	@SuppressWarnings("unlikely-arg-type")
	public static String TastyOverview(String topicUrl) {


		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements overview = link.getElementsByClass("description xs-text-4 md-text-3 lg-text-2 xs-mb2 lg-mb2 lg-pb05");
			String over = overview.text();
			
			String temp = over;
			
			for(int i = 1; temp.indexOf(" ") != -1; i++) {
				if(i % 5 != 0) 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				else {
					over = over.substring(0, temp.indexOf(" ")) + "\n" + over.substring(temp.indexOf(" ") + 1); 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				}
			}
			
			return over;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// INGREDIENTS

	public static String TastyIngredients(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements ingredients = link.getElementsByClass("ingredient xs-mb1 xs-mt0");

			String ingr = "";

			for(Element e: ingredients) {
				ingr += e.wholeText()+ "\n" ;
			}

			return ingr;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// PROCEDURE

	public static String TastyProcedure(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements procedure = link.select("ol").select("li");

			String proc = "";

			int i = 1;

			for(Element e: procedure) {
				proc += i + ". " + e.wholeText()+ "\n" ;
				i++;
			}

			return proc;

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// IMAGE

	public static String TastyImage(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements overview = link.getElementsByClass("image-wrapper xs-relative xs-overflow-hidden xs-col-12 xs-height-auto xs-mb1");
				
			return "still unfinished";
			
		}
		catch (IOException e) {
			return "Not found.";
		}
	}
	
	//////////
	//////////

	// F O O D  N E T W O R K

	// TITLE

	public static String NetworkTitle(String topicUrl) {
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

	public static String NetworkDifficulty(String topicUrl) {

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

	public static String NetworkTime(String topicUrl) {

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

	public static String NetworkServing(String topicUrl) {

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

	public static String NetworkOverview(String topicUrl) {

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

	public static String NetworkIngredients(String topicUrl) {

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

	public static String NetworkProcedure(String topicUrl) {

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

	//////////
	//////////
	
	// Y U M M L Y

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

	//////////
	//////////

	public static void main(String[] args) {

		String TastyUrl = "https://tasty.co/recipe/creamy-one-pot-spinach-and-egg-breakfast";

		String FoodNetworkUrl = "https://www.foodnetwork.com/recipes/food-network-kitchen/chicken-tortilla-dump-dinner-5500633";

		String YummlyUrl = "";
//
//		TastyTitle(TastyUrl);
//		TastyTime(TastyUrl);
//		TastyServing(TastyUrl);
		TastyOverview(TastyUrl);
//		TastyIngredients(TastyUrl);
//		TastyProcedure(TastyUrl);

//		NetworkTitle(FoodNetworkUrl);
//		NetworkDifficulty(FoodNetworkUrl);
//		NetworkTime(FoodNetworkUrl);
//		NetworkServing(FoodNetworkUrl);
//		NetworkOverview(FoodNetworkUrl);
//		NetworkIngredients(FoodNetworkUrl);
//		NetworkProcedure(FoodNetworkUrl);	
		
//		YummlyTitle(YummlyUrl);
//		YummlyDifficulty(YummlyUrl);
//		YummlyTime(YummlyUrl);
//		YummlyServing(YummlyUrl);
//		YummlyOverview(YummlyUrl);
//		YummlyIngredients(YummlyUrl);
//		YummlyProcedure(YummlyUrl);		

	}
}