package game3D;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class obj1 extends Group {
	  
	public obj1(Appearance ap) {
		
		Primitive base = new Cylinder(0.35f, 0.1f, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS , 60, 1, ap);
		//this.addChild(base);
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3f(2, 2, 2));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(base);
		this.addChild(tg);
		
		// Body
		Primitive body = new Cylinder(0.1f, 0.4f, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS, ap);
		//this.addChild(base);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(2.0f, 2.25f, 2.0f));
		tg = new TransformGroup(tr);
		tg.addChild(body);
		this.addChild(tg);
		
		Primitive tv = new Box(0.5f, 0.05f, 0.5f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS | Box.ENABLE_GEOMETRY_PICKING, ap);
		tr.setTranslation(new Vector3f(2, 2.4f, 2.0f));
		tg = new TransformGroup(tr);
		tg.addChild(tv);
		this.addChild(tg);
	

}
	}
