package game_2D;

import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class menuGame extends JPanel implements MouseListener {
	

	
	int wFrame =640;
	int hFrame = 480;
	Image backGroundImage;
	
	Shape m1 = null; 
	Shape m2 = null;
	
	boolean selected = false;
	int firstX = 0;
	int firstY = 0;
	
	
	public menuGame() {
			setPreferredSize(new Dimension(wFrame, hFrame));
			setBackground(Color.white);
			
			backGroundImage = new ImageIcon("resources/neve1.jpg").getImage();
		
		
	  addMouseListener(this);
				
			}

	public void paintComponent(Graphics g) {
			super.paintComponent (g);
			Graphics2D g2 = (Graphics2D)g;
			
			g2.drawImage(backGroundImage, 0, 0 , null);	
			
			//g2.translate(wFrame/2, hFrame/2);
			Stroke stroke = new BasicStroke(5);
			Stroke stroke2 = new BasicStroke(9,BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
			
			
			m1 = new Rectangle2D.Double(250,157 , 150, 50);
			m2 = new Rectangle2D.Double(250, 257, 150, 50);
			
			g2.setColor(Color.WHITE);
			g2.drawOval(200, 100, 50, 50);
			g2.drawOval(400,300,50,50);
			
			g2.setColor(Color.WHITE);
			g2.setStroke(stroke2);
			g2.setFont(new Font("SERIF", Font.PLAIN, 27));
			g2.drawString("EXIT",285,290);
			g2.drawString("JOGAR",275,190);
			
			g2.setFont(new Font("SERIF", Font.ITALIC, 57));
			g2.drawString("Santa Claus Game",140,50);
			
			g2.setStroke(stroke);
			g2.draw(m1);
			g2.draw(m2);
			
			
			
			
	}



	@Override
	public void mouseClicked(MouseEvent e) {
	
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
		if (m1.contains(e.getPoint())) {
			main_2D.cardlayout.show(main_2D.mainPanel, "game");
			selected = true;
			
		} else if(m2.contains(e.getPoint())) {
			System.exit(0);
			
			
		}else{
				selected = false;
			}
	}
	
		


	@Override
	public void mouseReleased(MouseEvent e) {
	
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

		
