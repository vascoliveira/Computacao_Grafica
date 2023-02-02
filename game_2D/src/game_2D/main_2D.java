package game_2D;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class main_2D  {
	
	static CardLayout cardlayout;
	static JPanel mainPanel;
	static game gamePanel;
	static menuGame menuPanel;
	static Finish finishPanel;

	public static void main(String[] args) {
		JFrame frame = new JFrame(); 
		
		frame.setTitle("MiniGame");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cardlayout = new CardLayout();
		mainPanel = new JPanel(cardlayout);
		
		menuPanel = new menuGame();
		gamePanel = new game();
		finishPanel= new Finish();
		
		mainPanel.add(menuPanel, "menu");
		mainPanel.add(gamePanel, "game");
		mainPanel.add(finishPanel, "finish");
		
		mainPanel.addMouseListener(menuPanel);
		mainPanel.addKeyListener(gamePanel);
		mainPanel.setFocusable(true);
	
		frame.add(mainPanel);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}
 

	
		
		

 