package game3D;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.AudioDevice;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.ImageComponent;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.MediaContainer;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointLight;
import javax.media.j3d.PointSound;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.ScaleInterpolator;
import javax.media.j3d.Screen3D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Sound;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Text3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.swing.JFileChooser;
import javax.vecmath.AxisAngle4d;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.universe.SimpleUniverse;


public class game3D extends Frame implements MouseListener{
	
	BoundingSphere bounds = new BoundingSphere(new Point3d(2.0,0.0,2.0),5.0);
	
	private Floor floor;
	
	PointLight pLight = null;
	AmbientLight aLight = null ;
	PickCanvas pc = null;
	PointLight pLight2 = null;
	private Canvas3D cv;
	private Canvas3D offScreenCanvas;
	private View view;
	BranchGroup bgView = null; 
	SimpleUniverse su = null;
	PointSound sound = null;

	 

	

	public static void main(String[] args) {
		Frame frame = new game3D();
		frame.setPreferredSize(new Dimension(1200, 800));
		frame.setTitle("Game3D");
		frame.pack();
		frame.setVisible(true);
	}

	public game3D() {
		 
		this.setLayout(new GridLayout(1,1));
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		cv = new Canvas3D(gc);
		add(cv);  
		cv.addMouseListener(this);
		su = new SimpleUniverse(cv);
		Transform3D viewTr = new Transform3D();
		viewTr.lookAt(new Point3d(8, 2, 2), new Point3d(2, 2, 2), new Vector3d(0, 1, 0));
		viewTr.invert();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(viewTr);
		
		OrbitBehavior ob = new OrbitBehavior(cv);
		ob.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(ob);

		
		
		cv = new Canvas3D(gc);
		add(cv);
		
	    Point3d eyePos = new Point3d(2, 13, 2);
	    Point3d coi = new Point3d(2, 4, 2);
	    Vector3d up = new Vector3d(0, 1, 1);
	   
	    bgView = createView(cv, eyePos, coi, up);
	    ob.setSchedulingBounds(bounds);
		su.getViewingPlatform().setViewPlatformBehavior(ob);
	    BranchGroup bg = createSceneGraph();
	    su.addBranchGraph(bgView);
		bg.compile();
		su.addBranchGraph(bg);
		
	
		pc = new PickCanvas(cv, bg);
		pc.setMode(PickTool.GEOMETRY);
		
		AudioDevice audioDev = new JavaSoundMixer(su.getViewer().getPhysicalEnvironment());
		audioDev.initialize();
		
		
		
		view = su.getViewer().getView();
		 offScreenCanvas = new Canvas3D(gc, true);
		 Screen3D sOn = cv.getScreen3D();
		 Screen3D sOff = offScreenCanvas.getScreen3D();
		 Dimension dim = sOn.getSize();
		 sOff.setSize(dim);
		 sOff.setPhysicalScreenWidth(sOn.getPhysicalScreenWidth());
		 sOff.setPhysicalScreenHeight(sOn.getPhysicalScreenHeight());
		 Point loc = cv.getLocationOnScreen();
		 offScreenCanvas.setOffScreenLocation(loc);
		 
		 
		 Button button = new Button("Tirar Foto");
		 add(button);
		 button.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent ev) {
		 BufferedImage bi = capture();
		 save(bi);
		      }
		    });
		
		
		//OrbitBehavior ob = new OrbitBehavior(cv);
		//ob.setSchedulingBounds(bounds);
		//su.getViewingPlatform().setViewPlatformBehavior(ob);

		WindowListener wl = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		};
		addWindowListener(wl);
			  
		
	}
	
	private BranchGroup createView(Canvas3D cv, Point3d eye,
		    Point3d center, Vector3d vup) {
		 	BranchGroup bgView = new BranchGroup();
			View view = new View();
		    view.setProjectionPolicy(View.PERSPECTIVE_PROJECTION);
		    ViewPlatform vp = new ViewPlatform();
		    vp.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE );
		    vp.setCapability( TransformGroup.ALLOW_TRANSFORM_READ );
		    view.addCanvas3D(cv);
		    view.attachViewPlatform(vp);
		    view.setPhysicalBody(new PhysicalBody());
		    view.setPhysicalEnvironment(new PhysicalEnvironment());
		    Transform3D trans = new Transform3D();
		    trans.lookAt(eye, center, vup);
		    trans.invert();
		    TransformGroup tg = new TransformGroup(trans);
		    MouseRotate behavior1 = new MouseRotate(tg);
		    behavior1.setSchedulingBounds(bounds);
		    
		    tg.addChild(vp);
		   
		  
		   
		    bgView.addChild(tg);
		   
		    
		    return bgView;
		  }
			

			
	
		private BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();
		
		
		
		root.addChild(new Axes(new Color3f(Color.RED), 3, 5.0f));
		
		// Floor
		//Appearance app = new TextureAppearance(this, "images/pavimento3.jpg", new Material(), true, false);
		
		//Appearance app = createTextureAppearance();
		Shape3D floor = new Floor(10, 0f, 4.0f, new Color3f(Color.LIGHT_GRAY), new Color3f(Color.WHITE), true);
		root.addChild(floor);	
		
		
		Appearance wallApp3 = new TextureAppearance(this, "images/brick_wall_Texture.jpg", new Material(), true, false);
		
		//------- BACKGROUND ----- //
		URL url1 = getClass().getClassLoader().getResource("images/garden.png");
		BufferedImage bi = null;
        try {
          bi = ImageIO.read(url1);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        ImageComponent2D image = new ImageComponent2D(ImageComponent2D.FORMAT_RGB, bi);
        Background background = new Background(image);
        background.setApplicationBounds(bounds);
        root.addChild(background);
		
		//----PAREDE1----//
		Box wall = new Box(1.0f, 2.0f, 0.01f, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, wallApp3);
		Transform3D tr = new Transform3D();
		tr.setRotation(new AxisAngle4d(0, 0, -1, Math.toRadians(90)));
		tr.setTranslation(new Vector3f(2.0f, 1.0f, 0.0f));
		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(wall);
		root.addChild(tg);
		
		//----PAREDE2----//
		Box wall2 = new Box(1.0f, 2.0f, 0.01f, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, wallApp3);
		
		Transform3D ta = new Transform3D();
		ta.setRotation(new AxisAngle4d(1, 1, 1, Math.toRadians(120)));
		ta.setTranslation(new Vector3f(0.01f, 1.0f, 2.0f));
		TransformGroup tb = new TransformGroup(ta);
		tb.addChild(wall2);
		root.addChild(tb);
		
		//----PAREDE3----//
		Box wall3 = new Box(1.0f, 2.0f, 0.01f, Box.GENERATE_TEXTURE_COORDS | Box.GENERATE_NORMALS, wallApp3);
		
		Transform3D Twall2 = new Transform3D();
		Twall2.setRotation(new AxisAngle4d(0,0 ,-1, Math.toRadians(90)));
		Twall2.setTranslation(new Vector3f(2.0f, 1.0f, 3.98f));
		TransformGroup Twallv2 = new TransformGroup(Twall2);
		Twallv2.addChild(wall3);
		root.addChild(Twallv2);
		
		//----MESA----//
		Material mat1 = new Material();
		mat1.setDiffuseColor(0.4f,0.2368f,0.1036f);
		mat1.setSpecularColor(0.774597f, 0.458561f, 0.200621f);
		mat1.setShininess(20.8f);
		Appearance topApp = new TextureAppearance(this, "images/wood2.jpg", mat1, true, false);
		Table table = new Table(topApp);
		Transform3D Ttable = new Transform3D();
		Ttable.setScale(0.8f);
		Ttable.setTranslation(new Vector3f(2.4f, 0f, 3.4f));
		TransformGroup Ttable2 = new TransformGroup(Ttable);
		root.addChild(Ttable2);
		Ttable2.addChild(table);
		
		Ttable2.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		Ttable2.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		Ttable2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		//CHAIR
		
		Appearance ChairTopApp = new TextureAppearance(this, "images/wood2.jpg", mat1, true, false);
		Appearance ChairlegApp = new TextureAppearance(this, "images/wood2.jpg", mat1, true, false);
		Appearance ChairBackgApp = new TextureAppearance(this, "images/wood2.jpg", mat1, true, false);
		
	
		Chair chair = new Chair(ChairTopApp, ChairlegApp,ChairBackgApp);
		
		Transform3D to = new Transform3D();
		to.setScale(0.5f);
		to.setTranslation(new Vector3f(1.7f, 0.02f, 3.4f));
		to.setRotation(new AxisAngle4d(0,1 ,0, Math.toRadians(45)));
		TransformGroup tu = new TransformGroup(to);
		tu.addChild(chair);
		root.addChild(tu);
		
		//ARMARIO
		
		Armario armario = new Armario(ChairBackgApp);
		
		Transform3D tarmario = new Transform3D();
		tarmario.setScale(0.5f);
		tarmario.setTranslation(new Vector3f(2.6f, 1.1f, 0.3f));
		tarmario.setRotation(new AxisAngle4d(1,0 ,0, Math.toRadians(90)));
		TransformGroup tarmrio2 = new TransformGroup(tarmario);
		tarmrio2.addChild(armario);
		root.addChild(tarmrio2);
		
		//ARMARIO2
		
		Material mat2 = new Material();
		mat2.setDiffuseColor(0.4f,0.2368f,0.1036f);
		mat2.setSpecularColor(0.774597f, 0.458561f, 0.200621f);
		mat2.setShininess(76.8f);
		Appearance ar = new TextureAppearance(this, "images/texture-ama2.jpg", mat2, true, false);
		Appearance ar2 = new TextureAppearance(this, "images/texture-ama2.jpg", mat2, true, false);
	
	

		armario2 armario2 = new armario2(ar,ar2);
		Transform3D tarmario2 = new Transform3D();
		tarmario2.setScale(0.4f);
		tarmario2.setTranslation(new Vector3f(0.4f, 0.20f, 2.0f));
		tarmario2.setRotation(new AxisAngle4d(1,0 ,0, Math.toRadians(90)));
		TransformGroup tarmarioV2 = new TransformGroup(tarmario2);
		tarmarioV2.addChild(armario2);
		root.addChild(tarmarioV2);
		
		//Bola
		
		TransformGroup spinball1 = new TransformGroup();
		spinball1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		spinball1.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		root.addChild(spinball1);
		
		// object
		Appearance ap = createTextureAppearance();
		Sphere bola = new Sphere(0.7f, Primitive.GENERATE_TEXTURE_COORDS, 50, ap);

		Transform3D tr001 = new Transform3D();
		tr001.setScale(0.3f);
		tr001.setTranslation(new Vector3f(2.4f, 0.2f, 1.0f));
		TransformGroup tg001 = new TransformGroup(tr001);
		tg001.addChild(bola);
		
		spinball1.addChild(tg001);

		//============ INTERPOLATOR ============================
	    Alpha alpha1 = new Alpha(-1, 6000);
	    alpha1.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha1.setDecreasingAlphaDuration(6000);
	    PositionInterpolator translator = new PositionInterpolator(alpha1, spinball1);
	    translator.setSchedulingBounds(bounds);
	    translator.setEnable(true);
	    root.addChild(translator);
		
		
	    //obj1
		
		Appearance cp = new Appearance(); 
		int tMode = TransparencyAttributes.NICEST;
		float tValue = 0.3f;
		TransparencyAttributes ta1 = new TransparencyAttributes(tMode,tValue );
		cp.setTransparencyAttributes(ta1);
		
		obj1 obj1 = new obj1(cp);
		Transform3D trobj1 = new Transform3D();
		trobj1.setScale(0.3f);
		trobj1.setTranslation(new Vector3f(3.1f, 0.25f, 2.8f));
		TransformGroup Tobj1 = new TransformGroup(trobj1);
		Tobj1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Tobj1.addChild(obj1);
		root.addChild(Tobj1);
		
		
		
		
		//Televisao
		
		Appearance dp = new Appearance();
		Color3f col = new Color3f(0f, 0f, 0f);
		ColoringAttributes ca = new ColoringAttributes(col, ColoringAttributes.NICEST);
		dp.setColoringAttributes(ca);
		Television television = new Television(dp);
		Transform3D trtv = new Transform3D();
		trtv.setScale(0.3f);
		trtv.setTranslation(new Vector3f(0.45f, 0.45f, 2.0f));
		TransformGroup Ttv = new TransformGroup(trtv);
		Ttv.addChild(television);
		root.addChild(Ttv);
		
		
		//Painel
		Material Gold = new Material();
		Gold.setDiffuseColor(0.34615f,0.3143f,0.0903f);
		Gold.setSpecularColor(0.797457f, 0.723991f, 0.208006f);
		Gold.setAmbientColor(0.24725f,0.2245f,0.0645f);
		Gold.setShininess(83.2f);
		Appearance gold = new TextureAppearance(this, "images/metal-textura.jpg", Gold, true, false);
		Ring ring = new Ring(gold);
		Transform3D TRring = new Transform3D();
		TRring.setScale(1.0f);
		TRring.setTranslation(new Vector3f(3.3f, 0.20f, 3.0f));
		TransformGroup TRing = new TransformGroup(TRring);
		TRing.addChild(ring);
		root.addChild(TRing);

		
		//FloorLamp
		
		Appearance lampShadeApp = new TextureAppearance(this, "images/lampshade-texture.jpg", Gold, true, false);
		Appearance bodyApp = new Appearance();
		bodyApp.setMaterial(Gold);
		FloorLamp floorLamp = new FloorLamp(lampShadeApp, bodyApp);
		Transform3D trFloor = new Transform3D();
		trFloor.setScale(0.9f);
		trFloor.setTranslation(new Vector3f(3.7f, 0, 0.7f));
		trFloor.setRotation(new AxisAngle4d(0,1 ,0, Math.toRadians(90)));
		TransformGroup tgFloor = new TransformGroup(trFloor);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tgFloor.addChild(floorLamp);
		root.addChild(tgFloor);
		
		//Robot
		Appearance objApp = createTextureAppearance3();
		Robot robot = new Robot(objApp);
		Transform3D movetr = new Transform3D();
		movetr.setScale(0.9f);
		movetr.setTranslation(new Vector3f(2f, 0, 2f));
		TransformGroup moveTg = new TransformGroup(movetr);
		moveTg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		moveTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		moveTg.addChild(robot);
		root.addChild(moveTg);
		
		// Behavior to move the object
		KeyControl kc = new KeyControl(moveTg, robot);
		kc.setSchedulingBounds(bounds);
		moveTg.addChild(kc);
		
		
		
		AmbientLight aLight = new AmbientLight(true, new Color3f(Color.WHITE));
		aLight.setInfluencingBounds(bounds);
		root.addChild(aLight);

		
		pLight = new PointLight(new Color3f(Color.YELLOW), new Point3f(4f,4f, 2f), new Point3f(1f, 0, 0));
		pLight.setCapability(PointLight.ALLOW_STATE_READ);
		pLight.setCapability(PointLight.ALLOW_STATE_WRITE);
		pLight.setInfluencingBounds(bounds);
		root.addChild(pLight);
		
		SpotLight sLight = new SpotLight(new Color3f(Color.BLUE), new Point3f(3.5f, 4f, 2.0f), new Point3f(0f, 1f, 0), new Vector3f(0, -1f, 0), (float)(Math.PI/6), 0f);
		sLight.setCapability(PointLight.ALLOW_STATE_READ);
		sLight.setCapability(PointLight.ALLOW_STATE_WRITE);
		sLight.setInfluencingBounds(bounds);
		root.addChild(sLight);
		
		root.addChild(new ViewLocation(new Vector3d(3.5, 4, 3.5), 0.05f, new Color3f(Color.BLUE)));
		
		DirectionalLight myLight = new DirectionalLight();
		myLight.setColor(new Color3f(Color.RED));
		myLight.setCapability(PointLight.ALLOW_STATE_READ);
		myLight.setCapability(PointLight.ALLOW_STATE_WRITE);
		myLight.setInfluencingBounds(bounds);
		root.addChild(myLight);
		
		root.addChild(new ViewLocation(new Vector3d(2, 2.0, 3.0), 0.05f, new Color3f(Color.RED)));
		
		// Background
		Background bk = new Background(new Color3f(Color.LIGHT_GRAY));
		bk.setApplicationBounds(bounds);
		root.addChild(bk);
		
		sound = new PointSound();
	    URL url = this.getClass().getClassLoader().getResource("images/bird.wav");
	    MediaContainer mc = new MediaContainer(url);
	    sound.setSoundData(mc);
	    sound.setLoop(Sound.INFINITE_LOOPS);
	    sound.setInitialGain(1f);
	    sound.setEnable(true);
	    BoundingSphere soundBounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
	    sound.setSchedulingBounds(soundBounds);
	    root.addChild(sound);
		
	
		Appearance atext = new Appearance();
		Font3D f3d = new Font3D(new Font("TestFont", Font.PLAIN, 1), new FontExtrusion());
		Text3D text3D = new Text3D(f3d, new String("MY HOME"), new Point3f(2.0f,3.7f, 2.0f));
		Shape3D s3D1 = new Shape3D();
		s3D1.setGeometry(text3D);
		s3D1.setAppearance(atext);
		Transform3D text = new Transform3D();
		text.setScale(0.5f);
		text.set(new Vector3f(4.0f, 5.0f, 2.3f));
		
		TransformGroup spinTg1 = new TransformGroup(text);
		
		Alpha alpha = new Alpha(-1, 10000);
	    RotationInterpolator rotator = new RotationInterpolator(alpha, spinTg1);
	    rotator.setSchedulingBounds(bounds);
	   
	    ScaleInterpolator scale = new ScaleInterpolator(alpha, spinTg1);
	    scale.setSchedulingBounds(bounds);
	    scale.setEnable(true);
	    spinTg1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    spinTg1.addChild(rotator);
	    spinTg1.addChild(scale);
	    spinTg1.addChild(s3D1);
	    root.addChild(spinTg1);
	    
		return root;

	}
		public BufferedImage capture() {
		    Dimension dim = cv.getSize();
		    view.stopView();
		    view.addCanvas3D(offScreenCanvas);
		    BufferedImage bImage =
		    new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);
		    ImageComponent2D buffer =
		    new ImageComponent2D(ImageComponent.FORMAT_RGB, bImage);
		    offScreenCanvas.setOffScreenBuffer(buffer);
		    view.startView();
		    offScreenCanvas.renderOffScreenBuffer();
		    offScreenCanvas.waitForOffScreenRendering();
		    bImage = offScreenCanvas.getOffScreenBuffer().getImage();
		    view.removeCanvas3D(offScreenCanvas);
		    return bImage;
		  }
		  
		  public void save(BufferedImage bImage) {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("."));
		    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
		      File oFile = chooser.getSelectedFile();
		      try {
		        ImageIO.write(bImage, "jpeg", oFile);
		      } catch (IOException ex) {
		        ex.printStackTrace();
		      }
		      
		    }
		    }
	
	
	
	Appearance createTextureAppearance(){    
	    Appearance app = new Appearance();
	    URL filename = 
	        getClass().getClassLoader().getResource("images/bolaFutebol.jpg");
	    TextureLoader loader = new TextureLoader(filename, this);
	    ImageComponent2D image = loader.getImage();
	    if(image == null) {
	      System.out.println("can't find texture file.");
	    }
	   
	    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
	    image.getWidth(), image.getHeight());
	    texture.setImage(0, image);
	    texture.setEnable(true);
	    texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
	    texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
	    app.setTexture(texture);
	    return app;
	}
	
	Appearance createTextureAppearance2(){    
	    Appearance app = new Appearance();
	    URL filename = 
	        getClass().getClassLoader().getResource("images/glass-texture.jpg");
	    TextureLoader loader = new TextureLoader(filename, this);
	    ImageComponent2D image = loader.getImage();
	    if(image == null) {
	      System.out.println("can't find texture file.");
	    }
	   
	    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
	    image.getWidth(), image.getHeight());
	    texture.setImage(0, image);
	    texture.setEnable(true);
	    texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
	    texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
	    app.setTexture(texture);
	    return app;
	}
	
	Appearance createTextureAppearance3(){    
	    Appearance app = new Appearance();
	    URL filename = 
	        getClass().getClassLoader().getResource("images/textura-metal-preto.jpg");
	    TextureLoader loader = new TextureLoader(filename, this);
	    ImageComponent2D image = loader.getImage();
	    if(image == null) {
	      System.out.println("can't find texture file.");
	    }
	   
	    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
	    image.getWidth(), image.getHeight());
	    texture.setImage(0, image);
	    texture.setEnable(true);
	    texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
	    texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
	    app.setTexture(texture);
	    return app;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		pc.setShapeLocation(e);
		PickResult result = pc.pickClosest();
		if (result != null) {

			TransformGroup nodeTg = (TransformGroup) result.getNode(PickResult.TRANSFORM_GROUP);
			//TransformGroup nodeS = (TransformGroup) result.getNode(PickResult.TRANSFORM_GROUP);
			if (nodeTg != null) {

				if(pLight.getEnable())
					pLight.setEnable(false);
				else 
					pLight.setEnable(true);
				
				
			}
				
			
			}
			
				}
			
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
