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

public class ListOfRecipes {

	// T A S T Y

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
	// F O O D  N E T W O R K

	public static String NetworkEasyBaking(String topicUrl) {

		String s = "";

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements dishes = link.getElementsByClass("m-MediaBlock__a-Headline");

			dishes.text();

			for(Element e: dishes) {
				s = e.wholeText();
				System.out.println(e.text());
			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}

	}

	// Y U M M L Y

	public static String YummlyTitle(String topicUrl) {
		String s = "";

		try {

			Document link = Jsoup.connect(topicUrl).get();
			Elements title = link.select("h1");

			title.text();

			for(Element e: title) {
				s = e.wholeText();
				System.out.println(e.text());
			}
			return s;
		}
		catch (IOException e) {
			return "Not found.";
		}
	}

	//////////
	//////////

	public static void main(String[] args) {

		String TastyUrl = "https://tasty.co/topic/one-pot";
		
			TastyOnePot(TastyUrl);

	}
}