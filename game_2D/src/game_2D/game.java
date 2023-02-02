package game_2D;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


			
public class game extends JPanel implements Runnable,KeyListener,ActionListener {
	
	
	float angle = 0f;
	int wFrame =640;
	int hFrame = 480;
	
	Image backGroundImage;
	BufferedImage image = null;
	BufferedImage image1 = null;
	BufferedImage image2 = null;
	BufferedImage image3 = null;
	
	
	Shape natal = null;
	Shape pinheiro = null;
	
	Shape bota = null;
	Shape bota2 = null;
	
	boolean selected = false;
	private star star; 
	private star star1; 
	private star star2; 
	private star star3; 
	int natalx = -200;
 	int nataly = 70;
 	int natalL =60;
 	
	int kXVelo = 0;
	int kYVelo = 0;
	

	JButton jbutton1;
	JButton jbutton;
	
	

	
	
	
	public game() {
			setPreferredSize(new Dimension(wFrame, hFrame));
			setBackground(Color.white);
			
			
			backGroundImage = new ImageIcon("resources/background1.jpg").getImage();
			 
			
			
			jbutton = new JButton("Menu");
			jbutton.addActionListener(this);
			add(jbutton);
			
			jbutton1 = new JButton("Reset");
			jbutton1.addActionListener(this);
			add(jbutton1);
			
			Thread thread = new Thread(this); 
		    thread.start();
		    
		    
		    star = new star(-210,-170);
		    star1 = new star(-150,-80);
		    star2 = new star(210,-190);
		    star3 = new star(200,80);
		   
		    
		    URL url = getClass().getClassLoader().getResource("papai_noel.jpg");
			try { 
			  image = ImageIO.read(url); 
			} catch (IOException ex) { 
			  ex.printStackTrace(); 
			}		
			
			  URL url1 = getClass().getClassLoader().getResource("bota.jpg");
				try { 
				  image1 = ImageIO.read(url1); 
				} catch (IOException ex) { 
				  ex.printStackTrace(); 
				}	
				
				 URL url2 = getClass().getClassLoader().getResource("bota.jpg");
					try { 
					  image2 = ImageIO.read(url2); 
					} catch (IOException ex) { 
					  ex.printStackTrace(); 
					}	
					
					 
				 URL url3 = getClass().getClassLoader().getResource("natal.png");
				 	try { 
					  image3 = ImageIO.read(url3); 
					} catch (IOException ex) { 
					  ex.printStackTrace(); 
					}		
	}
	@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == jbutton) {
				menu();
			}else if(e.getSource() == jbutton1) {
				reset();
			}
}
	
	
	
	

		public void paintComponent(Graphics g) {
			super.paintComponent (g);
			Graphics2D g2 = (Graphics2D)g;
			
			
			
			g2.drawImage(backGroundImage, 0, 0 , null);			
			
			Stroke stroke = new BasicStroke(10,BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			Stroke stroke2 = new BasicStroke(7);
		
			GradientPaint gp = new GradientPaint(-50,-50,Color.WHITE,50,50,Color.BLUE,false);
			
			
			AffineTransform oldtranform = new AffineTransform();
			oldtranform = g2.getTransform();
			
			g2.translate(320,240);
			
			
			
			
			g2.setStroke(stroke2);
			g2.setPaint(gp);
			g2.drawOval(-100,-100, 200, 200);
			
			star.drawStar(g2);
			star1.drawStar(g2);
			star2.drawStar(g2);
			star3.drawStar(g2);
			
		
			
			natal = new Ellipse2D.Double(natalx,nataly,natalL,natalL);
			
			bota = new Ellipse2D.Double(-30, -30, 60, 60);
			bota2 = new Ellipse2D.Double(-30, -45, 60, 60);
			pinheiro = new Ellipse2D.Double(0,0, 45, 45);
			
			
			AffineTransform tx = new AffineTransform(); 
			AffineTransform tx1 = new AffineTransform(); 
			AffineTransform at = new AffineTransform();
			
			 tx.rotate(Math.toRadians(angle));
			 tx1.rotate(Math.toRadians(angle));
			 tx.translate(100, 0);
			 tx1.translate(-100, 0);
			 
			 bota = tx.createTransformedShape(bota); 
			 bota2 = tx1.createTransformedShape(bota2); 
			 
			 
			
			TexturePaint botaShape = new TexturePaint(image1, bota.getBounds2D());
			TexturePaint botaShape2 = new TexturePaint(image2, bota2.getBounds2D());
			TexturePaint pinheiroShape3 = new TexturePaint(image3, pinheiro.getBounds2D());
			
			g2.setPaint(botaShape);
			g2.fill(bota); 
			 
			 
			 g2.setPaint(botaShape2);
			 g2.fill(bota2); 
			 
			
			 g2.setPaint(pinheiroShape3);
			 g2.fill(pinheiro); 
			 
			
			
			TexturePaint natalPaint = new TexturePaint(image, natal.getBounds2D());
					
			g2.setPaint(natalPaint);
			g2.fill(natal);
			 
			if (kXVelo != 0 || kYVelo != 0) {
				natalx = natalx + kXVelo;
				nataly = nataly + kYVelo;
			} 
			 
			
			at.setToTranslation(natalx, nataly);
			natal = at.createTransformedShape(natal);
			
			
			
			 g2.setStroke(stroke);
			 g2.setColor(Color.RED);
			 g2.setFont(new Font("Monospaced", Font.ITALIC, 60));
			 g2.drawString("Santa Claus",-200,-160);
			
			
			
			 g2.setTransform(oldtranform);
			 checkCollision();
			 checkFinish();
			
			
			
			 			
			 
}
		private void checkCollision() {
			
			 
			Point2D p = new Point2D.Double(natalx-1, nataly-1);
				if (bota.contains(p) || bota2.contains(p)) {
				natalx = -180;
				nataly = 50;
				selected = false;
				
			}
		}
		private void checkFinish() {
			if (pinheiro.contains(natalx, nataly)) {
				
				
				main_2D.cardlayout.show(main_2D.mainPanel, "finish");
				
				selected = true;
			}
		}	
		
	
		
		public void reset() {
			//main_2D.cardlayout.show(main_2D.mainPanel, "game");
			
			
		}
		
		public void menu() {
				main_2D.cardlayout.show(main_2D.mainPanel, "menu");
			}
		
		public void run() { 
			while (true) { 
				angle = (angle + 1) % 360;  
				repaint(); 
			try { 
				 Thread.sleep(40); 
		} catch (InterruptedException e) { 
				 e.printStackTrace(); 
		}
			} 
	} 

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
			switch (keyCode) {
			case KeyEvent.VK_LEFT:
				kXVelo = 3;
				kYVelo = 0;
				break;
			case KeyEvent.VK_RIGHT:
				kXVelo = 3;
				kYVelo = 0;
				break;
			case KeyEvent.VK_UP:
				kXVelo = 0;
				kYVelo = -3;
				break;
			case KeyEvent.VK_DOWN:
				kXVelo = 0;
				kYVelo = 3;
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			kXVelo = 0;
			kYVelo = 0;
			
		}

	
			}
			
