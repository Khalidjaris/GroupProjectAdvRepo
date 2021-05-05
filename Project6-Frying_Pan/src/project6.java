import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

//April 20th
//James and Khalid

public class project6 implements ActionListener {
	ArrayList<String> websites, 
		TastyCategories, TastyRecipes,
		FoodNetworkCategory;
	JComboBox drop_website, drop_category, drop_dish; 
	OutlineLabel lWebsite, lCategory, lDishSearch, 
		lServing, lDuration, lDifficulty, lRating,
		lIngredients, lRecipe, lTitle, overview;
	JLabel lWebsiteLogo, lLogo, lImg, lBackground, lOverview;
	TextArea tx_serving, tx_duration, tx_difficulty, tx_rating, 
	tx_recipe, tx_ingredients, tx_overview;
	String website;
	JButton bDish;
	JFrame f;
	ImageIcon imageIconWebsite, imageIconDish, imageIconLogo, imageIconBackground;
	Image IMGBackground, IMGLogo, IMGWebsite; 
	Color grey = new Color(45, 45, 45);
	Color white = new Color(255, 255, 255);
	URL picURL;
	BufferedImage image;

	@SuppressWarnings("unchecked")
	project6(){
		f = new JFrame("Frying Pan"); 
		imageIconWebsite = new ImageIcon();
		imageIconDish = new ImageIcon();
		imageIconLogo = new ImageIcon();

		lWebsite = new OutlineLabel("Choose a Website:");
		lWebsite.setBounds(370,30,200,50);
		lWebsite.setForeground(white);
		lWebsite.setFont(new Font("Futura", Font.BOLD, 16));

		lCategory = new OutlineLabel("Choose a Category:");
		lCategory.setBounds(370,100,200,50);
		lCategory.setForeground(white);
		lCategory.setFont(new Font("Futura", Font.BOLD, 16));

		lDishSearch = new OutlineLabel("Choose a Dish:");
		lDishSearch.setBounds(370,170,200,50);
		lDishSearch.setForeground(white);
		lDishSearch.setFont(new Font("Futura", Font.BOLD, 16));

		websites = new ArrayList<String>();
		websites.add("Tasty");
		websites.add("Food Network");
		websites.add("Yummly");
		String[] websites2 = new String[3];
		websites.toArray(websites2);
		drop_website = new JComboBox(websites2);
		drop_website.setBounds(370,70,300,30);
		drop_website.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_website.setForeground(grey);
		drop_website.addActionListener(this);

		TastyCategories = new ArrayList<String>();
		TastyCategories.add("Brunch");
		TastyCategories.add("Vegetarian");
		TastyCategories.add("One Pot");
		String[] TastyCategories2 = new String[TastyCategories.size()];
		TastyCategories.toArray(TastyCategories2);
		drop_category = new JComboBox(TastyCategories2);
		drop_category.setBounds(370,140,300,30);
		drop_category.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_category.setForeground(grey);
		drop_category.addActionListener(this);

		String TastyUrl = "https://tasty.co/feature/breakfast-brunch";
		String[] recipes = Tasty.TastyBrunch(TastyUrl);
		drop_dish = new JComboBox(recipes);
		drop_dish.setBounds(370,210,300,30);
		drop_dish.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_dish.setForeground(grey);
		drop_dish.addActionListener(this);

		bDish = new JButton("Search");
		bDish.setBounds(370,250,100,30);
		bDish.isOpaque();
		bDish.addActionListener(this);

		////////

		lServing = new OutlineLabel("Serving");
		lServing.setBounds(30,350,150,50);
		lServing.setFont(new Font("Futura", Font.BOLD, 20));
		lServing.setForeground(white);

		tx_serving = new TextArea("");
		tx_serving.setBounds(30,400,150,50);
		tx_serving.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_serving.setForeground(grey);
		tx_serving.setEditable(false);
		
		lDuration = new OutlineLabel("Duration");
		lDuration.setBounds(30,450,150,50);
		lDuration.setFont(new Font("Futura", Font.BOLD, 20));
		lDuration.setForeground(white);

		tx_duration = new TextArea("");
		tx_duration.setBounds(30,500,150,50);
		tx_duration.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_duration.setForeground(grey);
		tx_duration.setEditable(false);
		
		lDifficulty = new OutlineLabel("Difficulty");
		lDifficulty.setBounds(30,550,150,50);
		lDifficulty.setFont(new Font("Futura", Font.BOLD, 20));
		lDifficulty.setForeground(white);
		lDifficulty.setVisible(false);

		tx_difficulty = new TextArea("");
		tx_difficulty.setBounds(30,600,150,50);
		tx_difficulty.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_difficulty.setForeground(grey);
		tx_difficulty.setEditable(false);
		tx_difficulty.setVisible(false);
		
		lRating = new OutlineLabel("Rating");
		lRating.setBounds(30,650,150,50);
		lRating.setFont(new Font("Futura", Font.BOLD, 20));
		lRating.setForeground(white);
		lRating.setVisible(false);

		tx_rating = new TextArea("");
		tx_rating.setBounds(30,700,150,50);
		tx_rating.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_rating.setForeground(grey);
		tx_rating.setEditable(false);
		tx_rating.setVisible(false);

		/////////

		lTitle = new OutlineLabel("Title");
		lTitle.setBounds(30,290,760,40);
		lTitle.setForeground(white);
		lTitle.setFont(new Font("Futura", Font.BOLD, 30));
		lTitle.setHorizontalAlignment(JLabel.LEFT);

		lIngredients = new OutlineLabel("Ingredients");
		lIngredients.setBounds(200,450,200,50);
		lIngredients.setFont(new Font("Futura", Font.BOLD, 20));
		lIngredients.setForeground(white);

		tx_ingredients = new TextArea("");
		tx_ingredients.setBounds(200,500,350,250);
		tx_ingredients.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_ingredients.setForeground(grey);
		tx_ingredients.setEditable(false);

		lRecipe = new OutlineLabel("Recipe/Procedure");
		lRecipe.setBounds(570,450,200,50);
		lRecipe.setFont(new Font("Futura", Font.BOLD, 20));
		lRecipe.setForeground(white);

		tx_recipe = new TextArea("");
		tx_recipe.setBounds(570,500,600,250);
		tx_recipe.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_recipe.setForeground(grey);
		tx_recipe.setEditable(false);

		lOverview = new JLabel("");
		lOverview.setBounds(250,355,700,100);
		lOverview.setFont(new Font("Futura", Font.BOLD, 17));
		lOverview.setForeground(white);

//		tx_overview = new TextArea(""); 
//		tx_overview.setBounds(940,400,250,200);
//		tx_overview.setFont(new Font("Calibri Light", Font.PLAIN, 13));
//		tx_overview.setForeground(grey);
//		tx_overview.setEditable(false);

		///////

		lLogo = new JLabel("");
		lLogo.setBounds(30,30,300,240);

		lWebsiteLogo = new JLabel("");
		lWebsiteLogo.setBounds(695,30,130,130);

		lImg = new JLabel("");
		lImg.setBounds(850,30,310,310);
		
		lBackground = new JLabel("");
		lBackground.setBounds(0,0,1200,850);

		// Top Section
		f.add(lWebsite);
		f.add(lDishSearch);
		f.add(lCategory);
		f.add(lWebsiteLogo);
		f.add(drop_website);
		f.add(drop_category);
		f.add(drop_dish);
		f.add(bDish);

		// Left Section
		f.add(lServing);
		f.add(lDuration);
		f.add(lDifficulty);
		f.add(lRating);
		f.add(tx_serving);
		f.add(tx_duration);
		f.add(tx_difficulty);
		f.add(tx_rating);

		// Right Section
		f.add(lTitle);
		f.add(lIngredients);
		f.add(tx_recipe);
		f.add(lRecipe);
		f.add(tx_ingredients);
		f.add(lOverview);
//		f.add(tx_overview);
		f.add(lLogo);

		f.add(lImg);
		f.add(lBackground);

		f.setTitle("Frying Pan");
		f.setSize(1200,850);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		setFileImage("CookingBackground3.jpg", lBackground);
		setFileImage("Logo(Background)2.png", lLogo);
		setFileImage("Tasty-Logo.png", lWebsiteLogo);

	}
	
	//////////
	//////////
	
	// PICTURES 
	
	public void setFileImage(String fileName, JLabel label) {

		try {
			IMGLogo = ImageIO.read(new File(fileName));
			IMGLogo = IMGLogo.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
			imageIconLogo = new ImageIcon(IMGLogo);
			label.setHorizontalAlignment(JLabel.LEFT);
			label.setVerticalAlignment(JLabel.NORTH);
			label.setIcon(imageIconLogo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setDishImage(String src) {
		
		try {
			picURL = new URL(src); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.err.println("Image Link Error");
		}

		try {
			HttpURLConnection connection = (HttpURLConnection) picURL.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0");
			image = ImageIO.read(connection.getInputStream());
		}
		catch (IOException e1) {
			System.err.println(e1.getMessage());
		}

		imageIconDish.setImage(image.getScaledInstance(lImg.getWidth(), lImg.getHeight(), Image.SCALE_SMOOTH));

		imageIconDish.getImage().flush();
		
		lImg.removeAll();
		
		lImg.setIcon(imageIconDish);
		
		f.repaint();

	}

	public static void main(String[] args) {
		new project6();	
	}
	
	/////////
	/////////

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {

		String TastyUrl = "";

		if(e.getSource() == drop_website) {

			String category = (String) drop_website.getSelectedItem();

			if(category.equals("Tasty")) {
				
				website = "https://tasty.co/feature/breakfast-brunch";
				String[] recipes = Tasty.TastyBrunch(website);
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
				setFileImage("Tasty-Logo.png", lWebsiteLogo);
				
				lDifficulty.setVisible(false);
				tx_difficulty.setVisible(false);
				lRating.setVisible(false);
				tx_rating.setVisible(false);
				
				f.repaint();
			}

			if(category.equals("Food Network")) {

				String[] recipes = FoodNetwork.NetworkBestRecipes();
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
				setFileImage("FoodNetwork-Logo.png", lWebsiteLogo);
				
				lDifficulty.setVisible(true);
				tx_difficulty.setVisible(true);
				lRating.setVisible(true);
				tx_rating.setVisible(true);
				
				FoodNetworkCategory = new ArrayList<String>();
				FoodNetworkCategory.add("List of Best Recipes");
				String[] FoodNetworkCategory2 = new String[FoodNetworkCategory.size()];
				
				FoodNetworkCategory.toArray(FoodNetworkCategory2);
				drop_category.removeAllItems();
				for(String s: FoodNetworkCategory2)
					drop_category.addItem(s);
				
			}
		}

		if(e.getSource() == drop_category && drop_website.getSelectedItem().equals("Tasty")) {

			String category = (String) drop_category.getSelectedItem();
			Tasty r = new Tasty();

			if(category.equals("Brunch")) {
				website = "https://tasty.co/feature/breakfast-brunch";
				String[] recipes = Tasty.TastyBrunch(website);
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
			}

			else if(category.equals("Vegetarian")) {
				website = "https://tasty.co/topic/best-vegetarian";
				String[] recipes = r.TastyVegetarian(website);
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
			}

			else if(category.equals("One Pot")) {
				website = "https://tasty.co/topic/one-pot";
				String[] recipes = r.TastyOnePot(website);
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
			}
		}

		if(e.getSource() == bDish && drop_website.getSelectedItem().equals("Tasty")) {

			String recipe = (String) drop_dish.getSelectedItem();

			while(recipe.indexOf(" ") != -1) {
				recipe = recipe.substring(0,recipe.indexOf(" ")) + "-" + recipe.substring(recipe.indexOf(" ") + 1);
			}

			recipe = recipe.toLowerCase();
			TastyUrl = "https://tasty.co/recipe/" + recipe;
			TastyUrl = Tasty.Filter(TastyUrl);
			System.out.println(TastyUrl);

			lTitle.setText(Tasty.TastyTitle(TastyUrl));

			tx_ingredients.setText(Tasty.TastyIngredients(TastyUrl));		

			tx_recipe.setText(Tasty.TastyProcedure(TastyUrl));		

			tx_duration.setText(Tasty.TastyTime(TastyUrl));

			tx_serving.setText(Tasty.TastyServing(TastyUrl));

			lOverview.setText("<html>" + Tasty.TastyOverview(TastyUrl).replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
			
			setDishImage(Tasty.TastyImage(TastyUrl));


		}
		
		if(e.getSource() == bDish && drop_website.getSelectedItem().equals("Food Network")) {

			int index = drop_dish.getSelectedIndex();

			String FoodNetworkUrl = FoodNetwork.NetworkBestRecipesLinks(index);
			
			System.out.println(FoodNetworkUrl);

			lTitle.setText(FoodNetwork.NetworkTitle(FoodNetworkUrl));

			tx_ingredients.setText(FoodNetwork.NetworkIngredients(FoodNetworkUrl));		

			tx_recipe.setText(FoodNetwork.NetworkProcedure(FoodNetworkUrl));		

			tx_duration.setText(FoodNetwork.NetworkTime(FoodNetworkUrl));

			tx_serving.setText(FoodNetwork.NetworkServing(FoodNetworkUrl));

			lOverview.setText("<html>" + FoodNetwork.NetworkOverview(FoodNetworkUrl).replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

			tx_difficulty.setText(FoodNetwork.NetworkDifficulty(FoodNetworkUrl));
			
		}
	}
}
