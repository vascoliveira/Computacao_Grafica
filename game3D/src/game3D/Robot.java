package game3D;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

public class Robot extends Group {
	   public Robot(Appearance ap) {
	
		
	   Primitive Robot = new Cylinder(0.30f, 0.20f, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS, ap);
	   Transform3D tr = new Transform3D();
	   tr.setTranslation(new Vector3f(0.4f, 0.25f, 0.4f));
	   TransformGroup tg = new TransformGroup(tr);
	   tg.addChild(Robot);
	   this.addChild(tg);
	   
	   Primitive cilindro2 = new Cylinder(0.10f, 0.2f, Cylinder.GENERATE_NORMALS | Cylinder.GENERATE_TEXTURE_COORDS, ap);
	   tr = new Transform3D();
	   tr.setTranslation(new Vector3f(0.35f, 0.40f, 0.2f));
	   tg = new TransformGroup(tr);
	   tg.addChild(cilindro2);
	   this.addChild(tg);
	   
	   Sphere sphere = new Sphere(0.05f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,ap);
	   tr = new Transform3D();
	   tr.setTranslation(new Vector3f(0.5f, 0.14f, 0.25f));
	   tg = new TransformGroup(tr);
	   tg.addChild(sphere);
	   this.addChild(tg);
	   
	   Sphere sphere2 = new Sphere(0.05f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,ap);
	   tr = new Transform3D();
	   tr.setTranslation(new Vector3f(0.5f, 0.14f, 0.50f));
	   tg = new TransformGroup(tr);
	   tg.addChild(sphere2);
	   this.addChild(tg);

}
}
