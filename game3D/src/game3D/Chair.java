package game3D;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class Chair extends Group {
	   public Chair(Appearance topApp, Appearance legApp,Appearance backTop) {
		  
		   
		   Primitive topback = new Box(0.38f, 0.02f, 0.5f, Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING | Box.GENERATE_TEXTURE_COORDS, backTop);
		   //this.addChild(top);
		   Transform3D ta = new Transform3D();
		   ta.setTranslation(new Vector3f(-0.3f, 0.85f , 0f));
		   ta.setRotation(new AxisAngle4d(0,0 ,1, Math.toRadians(90)));
		   TransformGroup tb = new TransformGroup(ta);
		   tb.addChild(topback);
		   this.addChild(tb);
		
		   
		   // Top
		   Primitive top = new Box(0.38f, 0.05f, 0.5f, Box.GENERATE_NORMALS | Box.ENABLE_GEOMETRY_PICKING | Box.GENERATE_TEXTURE_COORDS, topApp);
		   //this.addChild(top);
		   Transform3D tr = new Transform3D();
		   tr.setTranslation(new Vector3f(0f, 0.05f + 0.5f, 0f));
		   TransformGroup tg = new TransformGroup(tr);
		   tg.addChild(top);
		   this.addChild(tg);
		   
		   // Legs
		   Primitive leg = new Cylinder(0.05f, 0.5f, legApp);
		   //this.addChild(leg);
		   tr.setTranslation(new Vector3f(0.4f, 0.25f, 0.4f));
		   tg = new TransformGroup(tr);
		   tg.addChild(leg);
		   this.addChild(tg);
		  
		   leg = new Cylinder(0.05f, 0.5f, legApp);
		   //this.addChild(leg);
		   tr.setTranslation(new Vector3f(-0.4f, 0.25f, 0.4f));
		   tg = new TransformGroup(tr);
		   tg.addChild(leg);
		   this.addChild(tg);
		  
		   leg = new Cylinder(0.05f, 0.5f, legApp);
		   //this.addChild(leg);
		   tr.setTranslation(new Vector3f(0.4f, 0.25f, -0.4f));
		   tg = new TransformGroup(tr);
		   tg.addChild(leg);
		   this.addChild(tg);
		  
		   leg = new Cylinder(0.05f, 0.5f, legApp);
		   //this.addChild(leg);
		   tr.setTranslation(new Vector3f(-0.4f, 0.25f, -0.4f));
		   tg = new TransformGroup(tr);
		   tg.addChild(leg);
		   this.addChild(tg);
	   }
	}
