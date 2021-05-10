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

			for(Element e: title) {
				s += e.wholeText();
			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// TIME

	public static String YummlyTime(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();
			
			Element time = link.getElementsByClass("recipe-summary-item unit h2-text").get(0);

			String t = time.text();
			
			t = t.substring(0, t.indexOf("M")) + " " + t.substring(t.indexOf("M"));

			return t;

		}
		catch (IOException e) {
			return "Not found.";
		}	
	}
	
	// CALORIES

	public static String YummlyCalories(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();
			
			Element time = link.getElementsByClass("recipe-summary-item nutrition h2-text").get(0);

			String t = time.text();
			
			t = t.substring(0, t.indexOf("C")) + " " + t.substring(t.indexOf("C"));

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
			
			Element time = link.getElementsByClass("servings micro-caps font-bold").get(0);

			String t = time.text();
						
			return t;

		}
		catch (IOException e) {
			return "Not found.";
		}	
	}

	// OVERVIEW

	public static String YummlyOverview(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl + "#directions").get();

			Elements overview = link.select("p");

			String view = "";

			for(Element e: overview) {
				view += e.html() + "\n";
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

			Elements ingredients = link.getElementsByClass("IngredientLine");
			Elements ingred = ingredients.select("li");

			String ingr = "";

			for(Element e: ingred) {
				ingr += e.wholeText() + "\n";
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

	// IMAGE
	
	public static String YummlyImage(String topicUrl) {

		try { 

			Document link = Jsoup.connect(topicUrl).timeout(0).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
			Elements image = link.getElementsByClass("dpsp-pin-it-wrapper alignnone size-full wp-image-51549"); 
			Elements img = image.select("src");

			String cover = "";
			
			for(Element e: img) 
				cover += img.text();
			
			return cover;
		}
			
		catch (IOException e) {
			System.out.println(e.toString());
			return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPEAAADRCAMAAAAquaQNAAAAA1BMVEX///+nxBvIAAAAR0lEQVR4nO3BMQEAAADCoPVP7WULoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABuxZIAAeHuCGgAAAAASUVORK5CYII=";
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

	public static String YummlyRecipeLinks(int index, String type) {

		String http = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			String topicUrl = "https://www.yummly.com/recipes?q=best+chicken";		

			Document link = Jsoup.connect(topicUrl).get();
			Element name = link.getElementsByClass("card-info-wrapper flex-row").get(index);
			Element url = name.tagName("href");

			String dish = url.text();
			dish = dish.substring(0, dish.indexOf(" ") + 1);
			http = url.html();
			http = http.substring(http.indexOf("/recipe/"));
			http = http.substring(http.indexOf("/recipe/"), http.indexOf(dish) - 2);
			http = "http://yummly.com" + http;

			return http;
		}
		catch (IOException e) {
			System.out.println("Not found.");
			String n = "Not found.";
			return n;
		}
	} 
	
	
	public static void main(String[] args) {

		String YummlyChickenUrl = "https://www.yummly.com/recipes?q=best+chicken";
		String YummlyUrl = "http://yummly.com/recipe/Chicken-Adobo-2671007";

		String[] str = ChickenIngred(YummlyChickenUrl);

//		for(String s: str)
//			System.out.println(s);
//		System.out.println(YummlyRecipeLinks(1, "Chicken"));
		
				System.out.println(YummlyOverview(YummlyUrl));
//		System.out.println(YummlyTitle(YummlyUrl));
//		System.out.println(YummlyTime(YummlyUrl));
//		System.out.println(YummlyCalories(YummlyUrl));
//		System.out.println(YummlyServing(YummlyUrl));
//		System.out.println(YummlyIngredients(YummlyUrl));
//				YummlyProcedure(YummlyUrl);		
//		System.out.println(YummlyImage(YummlyUrl));		
	}
	
}
