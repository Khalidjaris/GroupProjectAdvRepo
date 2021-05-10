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

public class Tasty {

	// T A S T Y

	// Title

	public static String TastyTitle(String topicUrl) {

		String s = "";

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements title = link.select("h1");

			for(Element e: title) {
				s = e.wholeText();
			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// Time

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

	// Serving

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
	
	// Rating*

	public static String TastyRating(String topicUrl) {

		String s = "";

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements rating = link.select("span");
			Elements rate = rating.select("h4");

			for(Element e: rate)
				s += e.text();
			
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// Overview

	@SuppressWarnings("unlikely-arg-type")
	public static String TastyOverview(String topicUrl) {


		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements overview = link.getElementsByClass("description xs-text-4 md-text-3 lg-text-2 xs-mb2 lg-mb2 lg-pb05");
			String over = overview.text();

			String temp = over;

//			for(int i = 1; temp.indexOf(" ") != -1; i++) {
//				if(i % 15 != 0) 
//					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
//				else {
//					over = over.substring(0, temp.indexOf(" ")) + "\n" + over.substring(temp.indexOf(" ") + 1); 
//					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
//				}
//			}

			if(over.length() != 0)
				return over;
			else
				return "No Available Overview...";

		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	// Ingredients

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

	// Procedure

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

	// Image

	public static String TastyImage(String topicUrl) {

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements img = link.select("meta");

			System.out.println(img.get(12).attr("content"));
			return img.get(12).attr("content");

		}
		catch (IOException e) {
			System.out.println(e.toString());
			return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAPEAAADRCAMAAAAquaQNAAAAA1BMVEX///+nxBvIAAAAR0lEQVR4nO3BMQEAAADCoPVP7WULoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABuxZIAAeHuCGgAAAAASUVORK5CYII=";
		}
	}

	/////////
	/////////

	// LISTS

	// Brunch

	public static String[] TastyBrunch(String topicUrl) {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements dishes = link.getElementsByClass("feed-item");

			dishes.text();
			
			for(Element e: dishes) {
				s = e.wholeText();
				if(!s.contains("{{")) 
					r.add(s);
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

	// Vegetarian

	public static String[] TastyVegetarian(String topicUrl) {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements dishes = link.getElementsByClass("item-title xs-text-4 md-text-3 extra-bold text-gray xs-mb05");

			dishes.text();

			for(Element e: dishes) {
				s = e.wholeText();
				if(!s.contains("{{")) 
					r.add(s);
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

	// One-Pot

	public static String[] TastyOnePot(String topicUrl) {

		String s = "";
		ArrayList<String> r = new ArrayList<String>();

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements dishes = link.getElementsByClass("item-title xs-text-4 md-text-3 extra-bold text-gray xs-mb05");

			dishes.text();

			for(Element e: dishes) {
				s = e.wholeText();
				if(!s.contains("{{")) 
					r.add(s);
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
	
	public static String Filter(String topicUrl) {
		
		// Special Characters
		if(topicUrl.contains(",") || topicUrl.contains("‘") || topicUrl.contains("’") || topicUrl.contains("(") || topicUrl.contains("& ")) {
			
			// Comma
			for(int i = topicUrl.indexOf(","); topicUrl.indexOf(",") != -1; i = topicUrl.indexOf(",")) // 6.1
				topicUrl = topicUrl.substring(0, i) + topicUrl.substring(i + 1);
			
			// Apostrophes
			for(int i = topicUrl.indexOf("‘"); topicUrl.indexOf("‘") != -1; i = topicUrl.indexOf("‘")) 
				topicUrl = topicUrl.substring(0, i) + topicUrl.substring(i + 1);	
			for(int i = topicUrl.indexOf("’"); topicUrl.indexOf("’") != -1; i = topicUrl.indexOf("’")) 
				topicUrl = topicUrl.substring(0, i) + topicUrl.substring(i + 1);
			
			// Brackets
			while(topicUrl.indexOf("(") != -1) 
				topicUrl = topicUrl.substring(0, topicUrl.indexOf("(") - 1);
			
			// Special
			for(int i = topicUrl.indexOf("&-"); topicUrl.indexOf("&-") != -1; i = topicUrl.indexOf("&-")) 
				topicUrl = topicUrl.substring(0, i) + topicUrl.substring(i + 1);
		}
		
		// Brunch
		if(topicUrl.equals("https://tasty.co/recipe/breakfast-sausage-egg-cups")) 
			return "https://tasty.co/recipe/breakfast-sausage-egg-cup";
		
		if(topicUrl.equals("https://tasty.co/recipe/egg-in-hole-layered-breakfast-bake"))
			return "https://tasty.co/recipe/ham-cheese-egg-in-the-hole-layered-bake";
		
		if(topicUrl.equals("https://tasty.co/recipe/ham-and-cheese-lattice-quiche")) 
			return "https://tasty.co/recipe/quiche-with-ham-and-cheese-lattice";
		
		if(topicUrl.equals("https://tasty.co/recipe/crème-brûlée-french-toast")) 
			return "https://tasty.co/recipe/creme-brulee-french-toast";
		
		// Vegetarian 
		if(topicUrl.equals("https://tasty.co/recipe/how-to-make-the-best-brownies")) 
			return "https://tasty.co/recipe/ultimate-brownies";
		
		if(topicUrl.equals("https://tasty.co/recipe/how-to-make-cinnamon-rolls")) 
			return "https://tasty.co/recipe/tasty-101-cinnamon-rolls";
		
		if(topicUrl.equals("https://tasty.co/recipe/how-to-make-macarons")) 
			return "https://tasty.co/recipe/macarons";
		
		if(topicUrl.equals("https://tasty.co/recipe/fluffy-pancakes")) 
			return "https://tasty.co/recipe/fluffy-perfect-pancakes";
		
		if(topicUrl.equals("https://tasty.co/recipe/the-best-ever-cauliflower-pizza-crust")) 
			return "https://tasty.co/recipe/cauliflower-crust-pizza";
		
		if(topicUrl.equals("https://tasty.co/recipe/the-best-chocolate-cake")) 
			return "https://tasty.co/recipe/the-ultimate-chocolate-cake";
		
		// One Pot
		if(topicUrl.equals("https://tasty.co/recipe/one-pot-shrimp-and-spinach-fettuccine-alfredo-pasta")) 
			return "https://tasty.co/recipe/one-pot-shrimp-and-spinach-pasta";
					
		return topicUrl;
		
		// Minso, Talal, Mohammed, Iman, Nofe, Lara, Ghazal, Lozi, Hashem
		
	}

	public static void main(String[] args) {

		String TastyUrl = "https://tasty.co/feature/breakfast-brunch";

//		TastyTitle(TastyUrl);
//		TastyTime(TastyUrl);
//		TastyServing(TastyUrl);
//		TastyOverview(TastyUrl);
//		TastyRating(TastyUrl);
//		TastyIngredients(TastyUrl);
//		TastyProcedure(TastyUrl);
//		TastyImage(TastyUrl);
		
//		TastyOnePot(TastyUrl);
//		System.out.println(Filter("https://tasty.co/recipe/easy-one-pot-mac-‘n’-cheese"));

	}
}