import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
///asdhakdshcakjsdcasdcjkasdjkc
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.html.parser.Element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
//april 12th
//James
public class project6 implements ActionListener {
	TextField tx_dishsearch, tx_difficulty1; 
	TextArea tx_rating, tx_duration, tx_difficulty2, tx_categories1, tx_categories2, tx_categories3, tx_recipe;
	Button bDish;
	JFrame f;
	String imgDish = "";
	String imgLogo = "";
	URL urlDish, urlLogo, urlBackground;
	JLabel ldishsearch, ldiffsearch, lLogo, limg, lbackground;
	ImageIcon imageIconDish, imageIconLogo, imageIconBackground;
	String playingImageDish, playingImageLogo, playingImageBackground; 
	Image IMGBackground, IMGLogo; 
	Color grey = new Color(45, 45, 45);
	Color white = new Color(255, 255, 255);
	
	project6(){
		f = new JFrame("Frying Pan");
		imageIconDish = new ImageIcon();
		imageIconLogo = new ImageIcon();
		//imageIconBackground = new ImageIcon();

		tx_dishsearch = new TextField("Input");
		tx_dishsearch.setBounds(400,70,100,30);
		tx_dishsearch.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_dishsearch.setForeground(grey);
		
		tx_difficulty1 = new TextField("Input");
		tx_difficulty1.setBounds(400,140,100,30);
		tx_difficulty1.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_difficulty1.setForeground(grey);
		
		tx_rating = new TextArea("Rating");
		tx_rating.setBounds(30,350,150,50);
		tx_rating.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_rating.setForeground(grey);
		
		tx_duration = new TextArea("Duration");
		tx_duration.setBounds(30,450,150,50);
		tx_duration.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_duration.setForeground(grey);
		
		tx_difficulty2 = new TextArea("Difficulty");
		tx_difficulty2.setBounds(30,550,150,50);
		tx_difficulty2.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_difficulty2.setForeground(grey);
		
		tx_categories1 = new TextArea("categories1");
		tx_categories1.setBounds(900,350,150,70);
		tx_categories1.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_categories1.setForeground(grey);
		
		tx_categories2 = new TextArea("categories2");
		tx_categories2.setBounds(900,450,150,70);
		tx_categories2.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_categories2.setForeground(grey);
		
		tx_categories3 = new TextArea("categories3");	
		tx_categories3.setBounds(900,550,150,70);
		tx_categories3.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_categories3.setForeground(grey);
		
		tx_recipe = new TextArea("Recipe");
		tx_recipe.setBounds(190,330,700,400);
		tx_recipe.setFont(new Font("Calibri", Font.PLAIN, 14));
		tx_recipe.setForeground(grey);
		
		bDish = new Button("Search");
		bDish.setBounds(420,200,100,30);
		
		ldishsearch = new JLabel("Search for Dish");
		ldishsearch.setBounds(400,30,200,50);
		ldishsearch.setForeground(white);
		ldishsearch.setFont(new Font("Futura", Font.BOLD, 16));
		
		ldiffsearch = new JLabel("Search for Difficulty");
		ldiffsearch.setBounds(400,100,200,50);
		ldiffsearch.setForeground(white);
		ldiffsearch.setFont(new Font("Futura", Font.BOLD, 16));
		
		lLogo = new JLabel("");
		lLogo.setBounds(0,0,500,500);
		lLogo.setForeground(white);
		lLogo.setFont(new Font("Futura", Font.BOLD, 16));
		
		limg = new JLabel("Dish Pic");
		limg.setBounds(800,70,300,250);
		limg.setForeground(white);
		limg.setFont(new Font("Futura", Font.BOLD, 16));
		
		lbackground = new JLabel("Background");
		lbackground.setBounds(0,0,1200,850);
		lbackground.setForeground(white);
		lbackground.setFont(new Font("Futura", Font.BOLD, 16));
		
		f.add(tx_dishsearch);
		f.add(tx_difficulty1);
		f.add(tx_rating);
		f.add(tx_duration);
		f.add(tx_difficulty2);
		f.add(tx_categories1);
		f.add(tx_categories2);
		f.add(tx_categories3);
		f.add(tx_recipe);
		f.add(bDish);
		f.add(ldishsearch);
		f.add(ldiffsearch);
		f.add(lLogo);
		f.add(limg);
		f.add(lbackground);
		
		f.setTitle("Frying Pan");
		f.setSize(1200,850);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		bDish.addActionListener(this);	
		
		try {
			IMGBackground = ImageIO.read(new File("cooking background.jpeg"));
			IMGBackground = IMGBackground.getScaledInstance(lbackground.getWidth(), lbackground.getHeight(), Image.SCALE_SMOOTH);
			imageIconBackground = new ImageIcon(IMGBackground);
			lbackground.setIcon(imageIconBackground);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			IMGLogo = ImageIO.read(new File("Frying Pan Logo.png"));
			IMGLogo = IMGLogo.getScaledInstance(IMGLogo.getWidth(lLogo) - 500, IMGLogo.getHeight(lLogo) - 420, Image.SCALE_SMOOTH);
			imageIconLogo = new ImageIcon(IMGLogo);
			lLogo.setHorizontalAlignment(JLabel.LEFT);
			lLogo.setVerticalAlignment(JLabel.NORTH);
			lLogo.setIcon(imageIconLogo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		
		String TastyUrl = "";
		
		if(e.getSource() == bDish) {
			String recipe = tx_dishsearch.getText();
			
			while(recipe.indexOf(" ")!=-1) {
				recipe = recipe.substring(0,recipe.indexOf(" "))+"-"+recipe.substring(recipe.indexOf(" ")+1);
			}
			
			TastyUrl = "https://tasty.co/recipe/" + recipe;
			String s = "";
			
//			try {
//
//				Document link = Jsoup.connect(TastyUrl).get();
//				Elements title = link.select("h1");
//				title.text();
//				
//				for(Element e1: title) {
//					s = e1.wholeText();
//					System.out.println(e1.text());
//				}
//				return s;
//			}
//			catch (IOException e) {
//				return "Not found.";
//			
//		
//		}
	}
	
	public static void main(String[] args) {
		//create an object of your class and run it
		new project6();	
	}
}
