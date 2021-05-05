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

	// P A R S E D  D A T A

	// Title

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

	// Overview

	@SuppressWarnings("unlikely-arg-type")
	public static String TastyOverview(String topicUrl) {


		try {

			Document link = Jsoup.connect(topicUrl).get();

			Elements overview = link.getElementsByClass("description xs-text-4 md-text-3 lg-text-2 xs-mb2 lg-mb2 lg-pb05");
			String over = overview.text();

			String temp = over;

			for(int i = 1; temp.indexOf(" ") != -1; i++) {
				if(i % 12 != 0) 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				else {
					over = over.substring(0, temp.indexOf(" ")) + "\n" + over.substring(temp.indexOf(" ") + 1); 
					temp = temp.substring(0, temp.indexOf(" ")) + "!" + temp.substring(temp.indexOf(" ") + 1); 
				}
			}

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
		
		if(topicUrl.equals("https://tasty.co/recipe/breakfast-sausage-egg-cups"))
			return "https://tasty.co/recipe/breakfast-sausage-egg-cup";
					
		else
			return topicUrl;
		
	}

	public static void main(String[] args) {

		String TastyUrl = "https://tasty.co/topic/one-pot";

//		TastyTitle(TastyUrl);
//		TastyTime(TastyUrl);
//		TastyServing(TastyUrl);
//		TastyOverview(TastyUrl);
//		TastyIngredients(TastyUrl);
//		TastyProcedure(TastyUrl);
//		TastyImage(TastyUrl);
		
		TastyOnePot(TastyUrl);


	}
}