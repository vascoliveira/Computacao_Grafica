package game3D;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

import javax.media.j3d.Appearance;
import javax.media.j3d.Group;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;

public class Armario extends Group {
	  public Armario(Appearance toparm) {
		
		  Box armoireBack = new Box(1.0f, 1.8f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		 
		  //Box armoireSide = new Box(1.0f, 2.0f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  

		  TransformGroup tgArmoire = new TransformGroup();
		  Transform3D transformArmoire = new Transform3D();
		  transformArmoire.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		  transformArmoire.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.PI / 2));
		  tgArmoire.setTransform(transformArmoire);
		  tgArmoire.addChild(armoireBack);
		  this.addChild(tgArmoire);

		   // Create a Box to represent the Top Armoire
		  
		  Box armoireTop = new Box(1.0f, 0.4f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgArmoireTop = new TransformGroup();
		  Transform3D transformArmoireTop = new Transform3D();
		  transformArmoireTop.setTranslation(new Vector3f(0.0f, 0.3f, -1.8f));
		  tgArmoireTop.setTransform(transformArmoireTop);
		  tgArmoireTop.addChild(armoireTop);
		  this.addChild(tgArmoireTop);
		  
		  // Create a Box to represent the Armoire Supp
		  
		  Box armoireSupp = new Box(1.0f, 0.4f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgArmoireSup = new TransformGroup();
		  Transform3D transformArmoireSup = new Transform3D();
		  transformArmoireSup.setTranslation(new Vector3f(0.0f, 0.3f, 1.8f));
		  tgArmoireSup.setTransform(transformArmoireSup);
		  tgArmoireSup.addChild(armoireSupp);
		  this.addChild(tgArmoireSup);
		  
		  Box armoireSide = new Box(1.9f, 0.4f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgArmoireSide = new TransformGroup();
		  Transform3D transformArmoireSide = new Transform3D();
		  transformArmoireSide.setTranslation(new Vector3f(1.0f, 0.3f, 0.f));
		  transformArmoireSide.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.PI/2 ));
		  tgArmoireSide.setTransform(transformArmoireSide);
		  tgArmoireSide.addChild(armoireSide);
		  this.addChild(tgArmoireSide);
		  
		  Box armoireSide2 = new Box(1.9f, 0.4f, 0.1f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgArmoireSide2 = new TransformGroup();
		  Transform3D transformArmoireSide2 = new Transform3D();
		  transformArmoireSide2.setTranslation(new Vector3f(-1.0f, 0.3f, 0.f));
		  transformArmoireSide2.setRotation(new AxisAngle4f(0.0f, 1.0f, 0.0f, (float) Math.PI/2 ));
		  tgArmoireSide2.setTransform(transformArmoireSide2);
		  tgArmoireSide2.addChild(armoireSide2);
		  this.addChild(tgArmoireSide2);
		  
		        
		        
		  Box shelf1 = new Box(1.0f, 0.1f, 0.36f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgShelf1 = new TransformGroup();
		  Transform3D transformShelf1 = new Transform3D();
		  transformShelf1.setTranslation(new Vector3f(0.0f, 0.3f, -1.0f));
		  transformShelf1.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.PI / 2));
		  tgShelf1.setTransform(transformShelf1);
		  tgShelf1.addChild(shelf1);
		  this.addChild(tgShelf1);
		        
		  Box shelf2 = new Box(1.0f, 0.1f, 0.36f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgShelf2 = new TransformGroup();
		  Transform3D transformShelf2 = new Transform3D();
		  transformShelf2.setTranslation(new Vector3f(0.0f, 0.3f, 0.0f));
		  transformShelf2.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.PI / 2));
		  tgShelf2.setTransform(transformShelf2);
		  tgShelf2.addChild(shelf2);
		  this.addChild(tgShelf2);
		        
		   
		  Box shelf3 = new Box(1.0f, 0.1f, 0.36f, Box.GENERATE_NORMALS | Box.GENERATE_TEXTURE_COORDS,toparm);
		  TransformGroup tgShelf3 = new TransformGroup();
		  Transform3D transformShelf3 = new Transform3D();
		  transformShelf3.setTranslation(new Vector3f(0.0f, 0.3f, 1.0f));
		  transformShelf3.setRotation(new AxisAngle4f(1.0f, 0.0f, 0.0f, (float) Math.PI / 2));
		  tgShelf3.setTransform(transformShelf3);
		  tgShelf3.addChild(shelf3);
		  this.addChild(tgShelf3);
		  
		 
		  
		 
	}
		  
}

