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
	
	// Chicken Ingrdient
	
	public static String ChickenIngred(String ChickenUrl) {
		String s = "";
		try {
			Document link = Jsoup.connect(ChickenUrl).get();
			Elements list = link.getElementsByClass("div.card-grid");
			//Elements subclass = list.select("div.carousel-card-tracker");
			//Elements titleClass = subclass.select("div. card-info primary-dark");
			//Elements title = titleClass.select("a").select("title");
			Elements subsubclass = list.select("card-title two-line-truncate p2-text font-bold");
			Element title = subsubclass.attr("title");
			System.out.println(title.text());
//			for(Element e: title) {
//				s = e.wholeText();
//				System.out.println(e.text());
//			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}
	
	
	public static void main(String[] args) {

		String YummlyChickenUrl = "https://www.yummly.com/ingredients/chicken";
		
		ChickenIngred(YummlyChickenUrl);

		//		YummlyTitle(YummlyUrl);
		//		YummlyDifficulty(YummlyUrl);
		//		YummlyTime(YummlyUrl);
		//		YummlyServing(YummlyUrl);
		//		YummlyOverview(YummlyUrl);
		//		YummlyIngredients(YummlyUrl);
		//		YummlyProcedure(YummlyUrl);		
	}
	
}
