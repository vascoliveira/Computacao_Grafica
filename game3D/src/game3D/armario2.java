package game3D;

import com.sun.j3d.utils.geometry.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class armario2 extends Group {
	

	public armario2(Appearance toparm,Appearance backarm) {
		

	
	   	Box frame = new Box(1.0f, 1.5f, 0.5f, Primitive.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
	    Transform3D t3d = new Transform3D();
	    t3d.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
	    TransformGroup tg = new TransformGroup(t3d);
	    tg.addChild(frame);
	    this.addChild(tg);
	    

	    // Create the drawers
	    Box drawer = new Box(0.9f, 1.3f, 0.2f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,backarm);
	    t3d = new Transform3D();
	    t3d.setTranslation(new Vector3f(0.30f, 0.0f, 0.0f));
	    tg = new TransformGroup(t3d);
	    tg.addChild(drawer);
	    this.addChild(tg);
	    
	    Sphere sphere = new Sphere(0.08f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,backarm);
	    t3d = new Transform3D();
	    t3d.setTranslation(new Vector3f(1.20f, 0.8f, 0.0f));
	    tg = new TransformGroup(t3d);
	    tg.addChild(sphere);
	    this.addChild(tg);
	    
	    Sphere sphere2 = new Sphere(0.08f, Sphere.GENERATE_NORMALS | Sphere.GENERATE_TEXTURE_COORDS,backarm);
	    t3d = new Transform3D();
	    t3d.setTranslation(new Vector3f(1.20f, -0.8f, 0.00f));
	    tg = new TransformGroup(t3d);
	    tg.addChild(sphere2);
	    this.addChild(tg);

	    /*Box drawer2 = new Box(0.9f, 0.4f, 0.4f, Primitive.GENERATE_NORMALS, toparm);
	    t3d = new Transform3D();
	    t3d.setTranslation(new Vector3f(0.0f, -1.2f, 0.0f));
	    tg = new TransformGroup(t3d);
	    tg.addChild(drawer2);
	    this.addChild(tg);*/

	}
}

