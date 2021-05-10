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
		TastyCategory,
		FoodNetworkCategory,
		YummlyCategory;
	JComboBox drop_website, drop_category, drop_dish; 
	OutlineLabel lWebsite, lCategory, lDishSearch, 
		lServing, lDuration, lDifficulty, lRating,
		lIngredients, lRecipe, lTitle, overview;
	JLabel lWebsiteLogo, lLogo, lImg, lBackground, lOverview, lInstruction;/**/ 
	TextArea tx_serving, tx_duration, tx_difficulty, tx_rating, 
	tx_recipe, tx_ingredients;
	String website;
	JButton bDish, bRandom, bInstruction, bNext; /**/
	JFrame f;
	ImageIcon imageIconWebsite, imageIconDish, imageIconLogo, imageIconBackground;
	Image IMGBackground, IMGLogo, IMGWebsite; 
	Color grey = new Color(45, 45, 45);
	Color white = new Color(255, 255, 255);
	URL picURL;
	BufferedImage image;
	int clicked = 0;

	@SuppressWarnings("unchecked")
	project6(){
		f = new JFrame("Frying Pan"); 
		imageIconWebsite = new ImageIcon();
		imageIconDish = new ImageIcon();
		imageIconLogo = new ImageIcon();
		
		String Instru = "With the options that will appear shortly,\n 1. choose a 'Website'"
				+ "\n 2. then a 'Category', \n3. and a 'Dish' to cook. \n Click 'Next' to choose.";
		Instru = "<html>" + Instru.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
		lInstruction = new JLabel(Instru);/**/
		lInstruction.setBounds(370,200,500,300);/**/		
		lInstruction.setForeground(white);/**/
		lInstruction.setFont(new Font("Baloo", Font.BOLD, 20));/**/
		lInstruction.setVisible(true);
		
		bNext = new JButton("Next");/**/
		bNext.setBounds(370,440,100,30);/**/
		bNext.isOpaque();/**/
		bNext.addActionListener(this);/**/
		bNext.setVisible(true);/**/

		lWebsite = new OutlineLabel("Choose a Website:");
		lWebsite.setBounds(370,30,200,50);
		lWebsite.setForeground(white);
		lWebsite.setFont(new Font("Futura", Font.BOLD, 16));
		lWebsite.setVisible(false);/**/

		lCategory = new OutlineLabel("Choose a Category:");
		lCategory.setBounds(370,100,200,50);
		lCategory.setForeground(white);
		lCategory.setFont(new Font("Futura", Font.BOLD, 16));
		lCategory.setVisible(false);/**/

		lDishSearch = new OutlineLabel("Choose a Dish:");
		lDishSearch.setBounds(370,170,200,50);
		lDishSearch.setForeground(white);
		lDishSearch.setFont(new Font("Futura", Font.BOLD, 16));
		lDishSearch.setVisible(false); /**/

		websites = new ArrayList<String>();
		websites.add("Tasty");
		websites.add("Food Network");
//		websites.add("Yummly");
		String[] websites2 = new String[websites.size()];
		websites.toArray(websites2);
		drop_website = new JComboBox(websites2);
		drop_website.setBounds(370,70,300,30);
		drop_website.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_website.setForeground(grey);
		drop_website.addActionListener(this);
		drop_website.setVisible(false);/**/

		TastyCategory = new ArrayList<String>();
		TastyCategory.add("Brunch");
		TastyCategory.add("Vegetarian");
		TastyCategory.add("One Pot");
		String[] TastyCategory2 = new String[TastyCategory.size()];
		TastyCategory.toArray(TastyCategory2);
		drop_category = new JComboBox(TastyCategory2);
		drop_category.setBounds(370,140,300,30);
		drop_category.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_category.setForeground(grey);
		drop_category.addActionListener(this);
		drop_category.setVisible(false);/**/

		String TastyUrl = "https://tasty.co/feature/breakfast-brunch";
		String[] recipes = Tasty.TastyBrunch(TastyUrl);
		drop_dish = new JComboBox(recipes);
		drop_dish.setBounds(370,210,300,30);
		drop_dish.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_dish.setForeground(grey);
		drop_dish.addActionListener(this);
		drop_dish.setVisible(false);/**/

		bDish = new JButton("Search");
		bDish.setBounds(370,250,100,30);
		bDish.isOpaque();
		bDish.addActionListener(this);
		bDish.setVisible(false);/**/
		
		bRandom = new JButton("Random Dish"); 
		bRandom.setBounds(695,160,130,60); 
		bRandom.isOpaque(); 
		bRandom.addActionListener(this); 
		bRandom.setVisible(false);/**/
		
		bInstruction = new JButton("Redo Instructions");/**/
		bInstruction.setBounds(695,220,130, 60); 
		bInstruction.isOpaque();/**/
		bInstruction.addActionListener(this);/**/
		bInstruction.setVisible(false);/**/

		////////

		lServing = new OutlineLabel("Servings");
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
		lTitle.setBounds(30,290,1000,40);
		lTitle.setForeground(white);
		lTitle.setFont(new Font("Futura", Font.BOLD, 27));
		lTitle.setHorizontalAlignment(JLabel.LEFT);

		lIngredients = new OutlineLabel("Ingredients");
		lIngredients.setBounds(200,490,200,50);
		lIngredients.setFont(new Font("Futura", Font.BOLD, 20));
		lIngredients.setForeground(white);

		tx_ingredients = new TextArea("");
		tx_ingredients.setBounds(200,540,350,210);
		tx_ingredients.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_ingredients.setForeground(grey);
		tx_ingredients.setEditable(false);

		lRecipe = new OutlineLabel("Recipe/Procedure");
		lRecipe.setBounds(570,490,200,50);
		lRecipe.setFont(new Font("Futura", Font.BOLD, 20));
		lRecipe.setForeground(white);

		tx_recipe = new TextArea("");
		tx_recipe.setBounds(570,540,600,210);
		tx_recipe.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_recipe.setForeground(grey);
		tx_recipe.setEditable(false);

		lOverview = new JLabel("");
		lOverview.setBounds(240,325,750,180);
		lOverview.setFont(new Font("Futura", Font.BOLD, 14));
		lOverview.setForeground(white);
		lOverview.setVerticalTextPosition(JLabel.TOP);

		///////

		lLogo = new JLabel("");
		lLogo.setBounds(30,30,300,240);

		lWebsiteLogo = new JLabel("");
		lWebsiteLogo.setBounds(695,25,130,130);

		lImg = new JLabel("");
		lImg.setBounds(850,25,305,305);
		
		lBackground = new JLabel("");
		lBackground.setBounds(0,0,1200,850);

		// Top Section
		f.add(lInstruction); /**/
		f.add(lWebsite);
		f.add(lDishSearch);
		f.add(lCategory);
		f.add(lWebsiteLogo);
		f.add(drop_website);
		f.add(drop_category);
		f.add(drop_dish);
		f.add(bDish);
		f.add(bInstruction); /**/
		f.add(bNext); /**/
		f.add(bRandom);

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
		
		double ratio = (double)image.getWidth()/lImg.getWidth();
		
		imageIconDish.setImage(image.getScaledInstance(lImg.getWidth(), (int)(image.getHeight()/ratio), Image.SCALE_SMOOTH));

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

		// INSTRUCTIONS
				
		if(e.getSource() == bNext && clicked == 0) {/**/
			bNext.setBounds(370,130,100,30);/**/
			lInstruction.setVisible(false);/**/
			lWebsite.setVisible(true);/**/
			drop_website.setVisible(true);/**/
			clicked++;
		}
		
		else if(e.getSource() == bNext && clicked == 1) {/**/
			bNext.setBounds(370,180,100,30);/**/
			lCategory.setVisible(true);/**/
			drop_category.setVisible(true);/**/
			clicked++;
		}
		
		else if(e.getSource() == bNext && clicked == 2) {/**/
			bNext.setVisible(false);/**/
			bDish.setVisible(true);/**/
			lDishSearch.setVisible(true);/**/
			drop_dish.setVisible(true);/**/
			bInstruction.setVisible(true);/**/
			bRandom.setVisible(true);
			
			String Instru = "Details about the dish you choose will appear after pressing 'Search'"
				+ "\n Hit 'Random Dish' to see what Frying Pan has to offer!";
			Instru = "<html>" + Instru.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>";
			lInstruction.setText(Instru);
			lInstruction.setVisible(true);/**/
			lInstruction.setBounds(250,200,1000,300);/**/	
			
			clicked++;
		}
				
		// RANDOM DISH GENERATOR
		
		if(e.getSource() == bRandom) {
			
			int x = (int)(Math.random() * drop_website.getItemCount());	
			drop_website.setSelectedIndex(x);	
			
			int y = (int)(Math.random() * drop_category.getItemCount());	
			drop_category.setSelectedIndex(y);	
			
			int z = (int)(Math.random() * drop_dish.getItemCount());
			drop_dish.setSelectedIndex(z);	
			
			bDish.doClick();
			
		}
		
		// INSTRUCTIONS BUTTON
		
		if(e.getSource() == bInstruction) {
			new project6();	
			
		}
		
		// WEBSITE DROPDOWN LIST
		
		if(e.getSource() == drop_website) {

			String w = (String) drop_website.getSelectedItem();

			if(w.equals("Tasty")) {
				
				// Layout Update
				setFileImage("Tasty-Logo.png", lWebsiteLogo);
				lDifficulty.setVisible(false);
				tx_difficulty.setVisible(false);
				lRating.setVisible(false);
				tx_rating.setVisible(false);
				lCategory.setText("Choose an Category:");
				
				// Dish update
				website = "https://tasty.co/feature/breakfast-brunch";
				String[] recipes = Tasty.TastyBrunch(website);
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
				
				// Category update
				String[] TastyCategory2 = new String[TastyCategory.size()];
				TastyCategory.toArray(TastyCategory2);
				int num = drop_category.getItemCount();
				for(String s: TastyCategory2)
					drop_category.addItem(s);
				drop_category.removeItemAt(0);
				while(drop_category.getItemAt(0) != "Brunch") 
					drop_category.removeItemAt(0);
			}
				
			if(w.equals("Food Network")) {
				
				// Layout Update
				setFileImage("FoodNetwork-Logo.png", lWebsiteLogo);
				lDifficulty.setVisible(true);
				tx_difficulty.setVisible(true);
				lRating.setVisible(false);
				tx_rating.setVisible(false);
				lCategory.setText("Choose an Category:");

				
				// Dish update
				String[] recipes = FoodNetwork.NetworkBestRecipes();
				drop_dish.removeAllItems();
				for(String s: recipes)
					drop_dish.addItem(s);
				
				// Category Update
				FoodNetworkCategory = new ArrayList<String>();
				FoodNetworkCategory.add("List of Best Recipes");
				String[] FoodNetworkCategory2 = new String[FoodNetworkCategory.size()];
				FoodNetworkCategory.toArray(FoodNetworkCategory2);
				drop_category.removeAllItems();
				for(String s: FoodNetworkCategory2)
					drop_category.addItem(s);
			}
			
//			if(w.equals("Yummly")) {
//
//				// Layout Update
//				setFileImage("Yummly-Logo.png", lWebsiteLogo);				
//				lDifficulty.setVisible(true);
//				tx_difficulty.setVisible(true);
//				lRating.setVisible(false);
//				tx_rating.setVisible(false);
//				lCategory.setText("Choose an Ingredient:");
//				
//				// Dish update
//				String[] recipes = Yummly.ChickenIngred("https://www.yummly.com/recipes?q=best+chicken");
//				drop_dish.removeAllItems();
//				for(String s: recipes)
//					drop_dish.addItem(s);
//				
//				// Category update
//				YummlyCategory = new ArrayList<String>();
//				YummlyCategory.add("Chicken");
//				YummlyCategory.add("Asparugus");
//				String[] YummlyCategory2 = new String[YummlyCategory.size()];
//				YummlyCategory.toArray(YummlyCategory2);
//				while(drop_category.getItemCount() != 0)
//					drop_category.removeItemAt(0);
//				for(String s: YummlyCategory2)
//					drop_category.addItem(s);
//				
//			}
		}
		
		// CATEGORY DROPDOWN LIST
		
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

		// DISH DROPDOWN

		if(e.getSource() == bDish && drop_website.getSelectedItem().equals("Tasty")) {

			String recipe = (String) drop_dish.getSelectedItem();
			
			lInstruction.setVisible(false);/**/

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

			lInstruction.setVisible(false);/**/

			String FoodNetworkUrl = FoodNetwork.NetworkBestRecipesLinks(index);
			
			System.out.println(FoodNetworkUrl);

			lTitle.setText(FoodNetwork.NetworkTitle(FoodNetworkUrl));

			tx_ingredients.setText(FoodNetwork.NetworkIngredients(FoodNetworkUrl));		

			tx_recipe.setText(FoodNetwork.NetworkProcedure(FoodNetworkUrl));		

			tx_duration.setText(FoodNetwork.NetworkTime(FoodNetworkUrl));

			tx_serving.setText(FoodNetwork.NetworkServing(FoodNetworkUrl));

			lOverview.setText("<html>" + FoodNetwork.NetworkOverview(FoodNetworkUrl).replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

			tx_difficulty.setText(FoodNetwork.NetworkDifficulty(FoodNetworkUrl));
			
			setDishImage(FoodNetwork.NetworkImage(FoodNetworkUrl));

		}
		
//		if(e.getSource() == bDish && drop_website.getSelectedItem().equals("Yummly")) {
//
//			int index = drop_dish.getSelectedIndex();
//			String category = (String) drop_category.getSelectedItem();
//
//			lInstruction.setVisible(false);/**/
//
//			String YummlyUrl = Yummly.YummlyRecipeLinks(index, category);
//			
//			System.out.println(YummlyUrl);
//
//			lTitle.setText(Yummly.YummlyTitle(YummlyUrl));
//
//			tx_ingredients.setText(Yummly.YummlyIngredients(YummlyUrl));		
//
//			tx_recipe.setText(Yummly.YummlyProcedure(YummlyUrl));		
//
//			tx_duration.setText(Yummly.YummlyTime(YummlyUrl));
//
//			tx_serving.setText(Yummly.YummlyServing(YummlyUrl));
//
//			lOverview.setText("<html>" + Yummly.YummlyOverview(YummlyUrl).replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
//			
//		}
	}
}
