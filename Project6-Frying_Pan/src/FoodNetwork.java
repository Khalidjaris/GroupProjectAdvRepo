//Khalid Siraj
//April 14, 2021
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FoodNetwork {

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

			Element difficulty = link.getElementsByClass("o-RecipeInfo__a-Description").get(0);

			String diff = difficulty.text();

			return diff;

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

			String t = time.text();

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

			String serv = servings.text();

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

			String temp = view;

			for(int i = 1; temp.indexOf(" ") != -1; i++) {
				if(i % 12 != 0) 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				else {
					view = view.substring(0, temp.indexOf(" ")) + "\n" + view.substring(temp.indexOf(" ") + 1); 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				}
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
					ingr += e.text() + "\n";
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
				proc += i + ". " + e.text() + "\n";
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

	// L I S T S

	// Best Recipes

	public static String[] NetworkBestRecipes() {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			String topicUrl = "https://www.foodnetwork.com/recipes/photos/food-network-kitchen-s-best-recipes#item-1";

			Document link = Jsoup.connect(topicUrl).get();
			Elements dishes = link.getElementsByClass("o-PhotoGalleryPromo__a-Cta");
			Elements url = dishes.tagName("a href");

			for(Element e: url) {
				s = e.text();

				r.add(s.substring(s.indexOf("The")));

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

	//////////
	//////////

	// O T H E R

	// Links

	public static String NetworkBestRecipesLinks(int index) {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			String topicUrl = "https://www.foodnetwork.com/recipes/photos/food-network-kitchen-s-best-recipes#item-1";

			Document link = Jsoup.connect(topicUrl).get();
			Element name = link.getElementsByClass("o-PhotoGalleryPromo__a-Cta").get(index);
			Element url = name.tagName("a href");

			s = url.html();
			s = s.substring(s.indexOf("www."), s.indexOf("target=") - 2);
			s = "http://" + s;
			System.out.println(s);

			return s;
		}
		catch (IOException e) {
			System.out.println("Not found.");
			String n = "Not found.";
			return n;
		}
	} 

	//////////
	//////////

	public static void main(String[] args) {

		String FoodNetworkUrl = NetworkBestRecipesLinks(1);

		//		System.out.println(NetworkTitle(FoodNetworkUrl));
		//		System.out.println(NetworkDifficulty(FoodNetworkUrl));
		//		System.out.println(NetworkTime(FoodNetworkUrl));
		//		System.out.println(NetworkServing(FoodNetworkUrl));
		//		System.out.println(NetworkOverview(FoodNetworkUrl));
//				System.out.println(NetworkIngredients(FoodNetworkUrl));
				System.out.println(NetworkProcedure(FoodNetworkUrl));	

	}
}