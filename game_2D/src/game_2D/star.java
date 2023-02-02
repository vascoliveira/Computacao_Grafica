package game_2D;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class star  {
	
	private float x;
	private float y;
	
	

	public star(float x,float y) {
		this.x = x;
		this.y= y;
		
		
	}
	
	
		  public void drawStar(Graphics2D g2) {
			  AffineTransform tx = new AffineTransform(); 
			  Point2D.Float point = new Point2D.Float(x, y);                     // store start point
				GeneralPath p = new GeneralPath(GeneralPath.WIND_NON_ZERO);
				p.moveTo(point.x, point.y);
				p.lineTo(point.x + 20.0f, point.y - 5.0f);           // Line from start to A
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x + 5.0f, point.y - 20.0f);           // Line from A to B
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x + 5.0f, point.y + 20.0f);           // Line from B to C
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x + 20.0f, point.y + 5.0f);           // Line from C to D
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x - 20.0f, point.y + 5.0f);           // Line from D to E
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x - 5.0f, point.y + 20.0f);           // Line from E to F
				point = (Point2D.Float)p.getCurrentPoint();
				p.lineTo(point.x - 5.0f, point.y - 20.0f);           // Line from F to g
				p.closePath();
				
				
			
				
				g2.setColor(Color.YELLOW);
				g2.fill(p);
		}
		  
		 	
}
  


