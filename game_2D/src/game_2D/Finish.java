package game_2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Finish extends JPanel {

	public Finish() {
		setPreferredSize(new Dimension(640, 480));
		setBackground(Color.BLACK);
		
	
}
	public void paintComponent(Graphics g) {
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D)g;
		
		
		FontRenderContext frc = g2.getFontRenderContext();
		AffineTransform transform = new AffineTransform();
		
		Font f = new Font("Helvetica", 35, 35);
		String s = new String("CHEGOU AO NIVEL 2");
		TextLayout textTl = new TextLayout(s, f, frc);
		
		Shape outline = textTl.getOutline(null);
		Rectangle r = outline.getBounds();
		transform = g2.getTransform();
		transform.translate(20,150);
		g2.transform(transform);
		g2.setColor(Color.green);
		g2.draw(outline);   
}}