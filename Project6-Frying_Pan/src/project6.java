import java.awt.Button;
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
	
	project6(){
		f = new JFrame("Frying Pan");
		tx_dishsearch = new TextField("Input");
		tx_difficulty1 = new TextField("Input");
		tx_rating = new TextArea("Rating");
		tx_duration = new TextArea("Duration");
		tx_difficulty2 = new TextArea("Difficulty");
		tx_categories1 = new TextArea("categories1");
		tx_categories2 = new TextArea("categories2");
		tx_categories3 = new TextArea("categories3");	
		tx_recipe = new TextArea("Recipe");
		bDish = new Button("Search");
		ldishsearch = new JLabel("Search for Dish");
		ldiffsearch = new JLabel("Search for Difficulty");
		lbackground = new JLabel("Background");
		lLogo = new JLabel("LOGO");
		limg = new JLabel("Dish Pic");
		imageIconDish = new ImageIcon();
		imageIconLogo = new ImageIcon();
		//imageIconBackground = new ImageIcon();

		tx_dishsearch.setBounds(400,70,100,30);
		tx_difficulty1.setBounds(400,140,100,30);
		tx_rating.setBounds(30,350,150,50);
		tx_duration.setBounds(30,450,150,50);
		tx_difficulty2.setBounds(30,550,150,50);
		tx_categories1.setBounds(900,350,150,70);
		tx_categories2.setBounds(900,450,150,70);
		tx_categories3.setBounds(900,550,150,70);
		tx_recipe.setBounds(190,330,700,400);
		bDish.setBounds(420,200,100,30);
		ldishsearch.setBounds(400,30,200,50);
		ldiffsearch.setBounds(400,100,200,50);
		lLogo.setBounds(30,70,320,180);
		limg.setBounds(800,70,300,250);
		lbackground.setBounds(0,0,1200,850);
		
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
			IMGLogo = ImageIO.read(new File("FryingPan.png"));
			IMGLogo = IMGLogo.getScaledInstance(lLogo.getWidth()+100, lLogo.getHeight()+250, Image.SCALE_SMOOTH);
			imageIconLogo = new ImageIcon(IMGLogo);
			lLogo.setIcon(imageIconLogo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void actionPerformed(ActionEvent e) {
		//if(e.getSource() == bDish) {
		//}
	}
	
	public static void main(String[] args) {
		//create an object of your class and run it
		new project6();	
	}
}
