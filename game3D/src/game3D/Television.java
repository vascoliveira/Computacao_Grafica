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

public class Television extends Group {
	  
	public Television(Appearance ap) {
		
		Primitive base = new Cylinder(0.35f, 0.1f, Cylinder.GENERATE_NORMALS, 60, 1, ap);
		//this.addChild(base);
		Transform3D tr = new Transform3D();
		tr.setTranslation(new Vector3f(0, 0, 0));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(base);
		this.addChild(tg);
		
		// Body
		Primitive body = new Cylinder(0.1f, 0.4f, Cylinder.GENERATE_NORMALS, ap);
		//this.addChild(base);
		tr = new Transform3D();
		tr.setTranslation(new Vector3f(0.0f, 0.25f, 0.0f));
		tg = new TransformGroup(tr);
		tg.addChild(body);
		this.addChild(tg);
		
		Primitive tvscreen = new Box(2.0f, 1.3f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS, ap);
		tr.setTranslation(new Vector3f(-0.3f, 1.70f, 0.0f));
		tr.setRotation(new AxisAngle4d(0,1 ,0, Math.toRadians(90)));
		tg = new TransformGroup(tr);
		tg.addChild(tvscreen);
		this.addChild(tg);
		
}
	}
