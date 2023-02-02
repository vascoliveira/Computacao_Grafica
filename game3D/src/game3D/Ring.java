package game3D;

import java.awt.Point;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Group;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.geometry.Primitive;

public class Ring extends Group{
	  
	   public Ring(Appearance ap) {
		  
		   Primitive leg = new Cylinder(0.15f, 0.8f, ap);
		   Transform3D tr = new Transform3D();
		   tr.setTranslation(new Vector3f(0.4f, 0.25f, 0.4f));
		   TransformGroup tg = new TransformGroup(tr);
		   tg.addChild(leg);
		   this.addChild(tg);
	  }
}