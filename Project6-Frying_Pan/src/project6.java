import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class project6 implements ActionListener {
	JComboBox drop_website, drop_category, drop_dish; 
	TextArea tx_recipe, tx_ingredients, tx_overview, tx_rating, tx_duration, tx_difficulty;
	JButton bDish;
	JFrame f;
	String imgDish = "";
	String imgLogo = "";
	URL urlDish, urlLogo, urlBackground;
	JLabel lCategory, lDishSearch, lRating, lDuration, lDifficulty, lOverview, lLogo, lImg, lBackground, lTitle;
	ImageIcon imageIconDish, imageIconLogo, imageIconBackground;
	String playingImageDish, playingImageLogo, playingImageBackground; 
	Image IMGBackground, IMGLogo; 
	Color grey = new Color(45, 45, 45);
	Color white = new Color(255, 255, 255);
	ArrayList<String> websites, TastyCategories, TastyRecipes;
	String website;

	@SuppressWarnings("unchecked")
	project6(){
		f = new JFrame("Frying Pan"); 
		imageIconDish = new ImageIcon();
		imageIconLogo = new ImageIcon();
		//imageIconBackground = new ImageIcon();

//		websites = new ArrayList<String>();
//		websites.add("Tasty");
//		websites.add("Food Network");
//		websites.add("Yummly");
//		String[] websites2 = new String[TastyCategories.size()];
//		TastyCategories.toArray(websites2);
//		drop_website = new JComboBox(websites2);
//		drop_website.setBounds(400,70,300,30);
//		drop_website.setFont(new Font("Calibri", Font.PLAIN, 14));
//		drop_website.setForeground(grey);
//		drop_website.addActionListener(this);

		TastyCategories = new ArrayList<String>();
		TastyCategories.add("Brunch");
		TastyCategories.add("Vegetarian");
		TastyCategories.add("One Pot");
		String[] TastyCategories2 = new String[TastyCategories.size()];
		TastyCategories.toArray(TastyCategories2);
		drop_category = new JComboBox(TastyCategories2);
		drop_category.setBounds(400,70,300,30);
		drop_category.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_category.setForeground(grey);
		drop_category.addActionListener(this);

		String TastyUrl = "https://tasty.co/feature/breakfast-brunch";
		ListOfRecipes r = new ListOfRecipes();
		String[] recipes = r.TastyBrunch(TastyUrl);
		drop_dish = new JComboBox(recipes);
		drop_dish.setBounds(400,140,300,30);
		drop_dish.setFont(new Font("Calibri", Font.PLAIN, 14));
		drop_dish.setForeground(grey);
		drop_dish.addActionListener(this);

		bDish = new JButton("Search");
		bDish.setBounds(400,180,100,30);
		bDish.isOpaque();
		bDish.addActionListener(this);

		lRating = new JLabel("Rating");
		lRating.setBounds(30,350,150,50);
		lRating.setFont(new Font("Futura", Font.BOLD, 20));
		lRating.setForeground(white);

		lDuration = new JLabel("Duration");
		lDuration.setBounds(30,470,150,50);
		lDuration.setFont(new Font("Futura", Font.BOLD, 20));
		lDuration.setForeground(white);
		
		lDifficulty = new JLabel("Difficulty");
		lDifficulty.setBounds(30,590,150,50);
		lDifficulty.setFont(new Font("Futura", Font.BOLD, 20));
		lDifficulty.setForeground(white);
		
		lOverview = new JLabel("Difficulty");
		lOverview.setBounds(940,590,150,50);
		lOverview.setFont(new Font("Futura", Font.BOLD, 20));
		lOverview.setForeground(white);
		
		tx_rating = new TextArea("");
		tx_rating.setBounds(30,400,150,50);
		tx_rating.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_rating.setForeground(grey);
		tx_rating.setEditable(false);
		
		tx_duration = new TextArea("");
		tx_duration.setBounds(30,520,150,50);
		tx_duration.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_duration.setForeground(grey);
		tx_duration.setEditable(false);

		tx_difficulty = new TextArea("");
		tx_difficulty.setBounds(30,640,150,50);
		tx_difficulty.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_difficulty.setForeground(grey);
		tx_difficulty.setEditable(false);

		tx_ingredients = new TextArea("Ingredients");
		tx_ingredients.setBounds(200,350,350,400);
		tx_ingredients.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_ingredients.setForeground(grey);
		tx_ingredients.setEditable(false);
		
		tx_recipe = new TextArea("Recipe");
		tx_recipe.setBounds(570,350,350,400);
		tx_recipe.setFont(new Font("Calibri Light", Font.PLAIN, 12));
		tx_recipe.setForeground(grey);
		tx_recipe.setEditable(false);
		
		tx_overview = new TextArea(""); 
		tx_overview.setBounds(940,400,250,200);
		tx_overview.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		tx_overview.setForeground(grey);
		tx_overview.setEditable(false);

		lCategory = new JLabel("Choose a Category:");
		lCategory.setBounds(400,30,200,50);
		lCategory.setForeground(white);
		lCategory.setFont(new Font("Futura", Font.BOLD, 16));

		lDishSearch = new JLabel("Choose a Dish:");
		lDishSearch.setBounds(400,100,200,50);
		lDishSearch.setForeground(white);
		lDishSearch.setFont(new Font("Futura", Font.BOLD, 16));

		lLogo = new JLabel("");
		lLogo.setBounds(30,30,500,500);
		lLogo.setForeground(white);
		lLogo.setFont(new Font("Futura", Font.BOLD, 16));

		lTitle = new JLabel("Title");
		lTitle.setBounds(30,290,760,40);
		lTitle.setForeground(white);
		lTitle.setFont(new Font("Futura", Font.BOLD, 30));
		lTitle.setHorizontalAlignment(JLabel.LEFT);

		lImg = new JLabel("");
		lImg.setBounds(800,70,300,250);
		lImg.setForeground(white);
		lImg.setFont(new Font("Futura", Font.BOLD, 16));

		lBackground = new JLabel("");
		lBackground.setBounds(0,0,1200,850);
		lBackground.setForeground(white);
		lBackground.setFont(new Font("Futura", Font.BOLD, 16));

		f.add(drop_category);
		f.add(drop_dish);
		f.add(lRating);
		f.add(lDuration);
		f.add(lDifficulty);
		f.add(tx_rating);
		f.add(tx_duration);
		f.add(tx_difficulty);
		f.add(tx_recipe);
		f.add(tx_ingredients);
		f.add(tx_overview);
		f.add(lTitle);
		f.add(bDish);
		f.add(lDishSearch);
		f.add(lCategory);
		f.add(lLogo);
		f.add(lImg);
		f.add(lBackground);

		f.setTitle("Frying Pan");
		f.setSize(1200,850);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		try {
			IMGBackground = ImageIO.read(new File("CookingBackground3.jpg"));
			IMGBackground = IMGBackground.getScaledInstance(lBackground.getWidth(), lBackground.getHeight(), Image.SCALE_SMOOTH);
			imageIconBackground = new ImageIcon(IMGBackground);
			lBackground.setIcon(imageIconBackground);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			IMGLogo = ImageIO.read(new File("Logo(Background)2.png"));
			IMGLogo = IMGLogo.getScaledInstance(IMGLogo.getWidth(lLogo) - 620, IMGLogo.getHeight(lLogo) - 450, Image.SCALE_SMOOTH);
			imageIconLogo = new ImageIcon(IMGLogo);
			lLogo.setHorizontalAlignment(JLabel.LEFT);
			lLogo.setVerticalAlignment(JLabel.NORTH);
			lLogo.setIcon(imageIconLogo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new project6();	
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {

		String TastyUrl = "";

		if(e.getSource() == drop_category) {

			String category = (String) drop_category.getSelectedItem();
			ListOfRecipes r = new ListOfRecipes();

			if(category.equals("Brunch")) {
				website = "https://tasty.co/feature/breakfast-brunch";
				String[] recipes = ListOfRecipes.TastyBrunch(website);
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

//		if(e.getSource() == bDish && drop_website.getSelectedItem().equals("Tasty")) {
		if(e.getSource() == bDish) {

			
			String recipe = (String) drop_dish.getSelectedItem();

			while(recipe.indexOf(" ") != -1) {
				recipe = recipe.substring(0,recipe.indexOf(" ")) + "-" + recipe.substring(recipe.indexOf(" ") + 1);
			}

			recipe = recipe.toLowerCase();
			TastyUrl = "https://tasty.co/recipe/" + recipe;
			System.out.println(TastyUrl);

			lTitle.setText(RecipeWebsites.TastyTitle(TastyUrl));
			
			tx_ingredients.setText(RecipeWebsites.TastyIngredients(TastyUrl));		
						
			tx_recipe.setText(RecipeWebsites.TastyProcedure(TastyUrl));		
			
			tx_duration.setText(RecipeWebsites.TastyTime(TastyUrl));
			
			tx_rating.setText(RecipeWebsites.TastyServing(TastyUrl));
			
			tx_overview.setText(RecipeWebsites.TastyOverview(TastyUrl));
			
			tx_difficulty.setText("–––Unavailable–––");

		}
	}
}
