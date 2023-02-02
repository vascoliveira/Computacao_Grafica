package game3D;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Node;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3f;

public class KeyControl extends Behavior {

	private TransformGroup moveTg = null;
	private Node node = null;
	private WakeupCondition wakeupCondition = null;
	boolean collision = false;
    int lastKey;
    
	public KeyControl(TransformGroup tg, Node n) {
		moveTg = tg;
		node = n;
	}

	@Override
	public void initialize() {
		WakeupCriterion[] events = new WakeupCriterion[4];
		events[0] = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
		events[1] = new WakeupOnAWTEvent(KeyEvent.KEY_RELEASED);
		events[2] = new WakeupOnCollisionEntry(node, WakeupOnCollisionEntry.USE_GEOMETRY);
		events[3] = new WakeupOnCollisionExit(node, WakeupOnCollisionExit.USE_GEOMETRY);

		wakeupCondition = new WakeupOr(events);
		wakeupOn(wakeupCondition);
	}

	@Override
	public void processStimulus(Enumeration criteria) {
		WakeupCriterion wakeupCriterion;
		AWTEvent[] awtEvents;

		while (criteria.hasMoreElements()) {
			wakeupCriterion = (WakeupCriterion) criteria.nextElement();

			if (wakeupCriterion instanceof WakeupOnAWTEvent) {

				awtEvents = ((WakeupOnAWTEvent) wakeupCriterion).getAWTEvent();
				for (int i = 0; i < awtEvents.length; i++) {
					if (awtEvents[i].getID() == KeyEvent.KEY_PRESSED) {
						processKey((KeyEvent) awtEvents[i]);
					} else if (awtEvents[i].getID() == KeyEvent.KEY_RELEASED) {
						// Not implement in this example
					}
				}

			} else if (wakeupCriterion instanceof WakeupOnCollisionEntry) {
				collision = true;
			} else if (wakeupCriterion instanceof WakeupOnCollisionExit) {
				collision = false;
			}
		}
		wakeupOn(wakeupCondition);
	}

	private void processKey(KeyEvent event) {
		int keyCode = event.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			if (!collision || (collision && lastKey != keyCode))
				doRotation(Math.toRadians(-1.0));
			break;
    	case KeyEvent.VK_RIGHT:
    		if (!collision || (collision && lastKey != keyCode))
				doRotation(Math.toRadians(1.0));
			break;
		case KeyEvent.VK_UP:
			if (!collision || (collision && lastKey != keyCode))
				doTranslation(new Vector3f(0f, 0f, -0.01f));
			break;
		case KeyEvent.VK_DOWN:
			if (!collision || (collision && lastKey != keyCode))
				doTranslation(new Vector3f(0f, 0f, 0.01f));
			break;
		}
		
		lastKey = keyCode;
	}

	private void doRotation(double t) {
		Transform3D oldTr = new Transform3D();
		moveTg.getTransform(oldTr);

		Transform3D newTr = new Transform3D();
		newTr.rotY(t);

		oldTr.mul(newTr);

		moveTg.setTransform(oldTr);
	}

	private void doTranslation(Vector3f v) {
		Transform3D oldTr = new Transform3D();
		moveTg.getTransform(oldTr);

		Transform3D newTr = new Transform3D();
		newTr.setTranslation(v);

		oldTr.mul(newTr);

		moveTg.setTransform(oldTr);
		
		Transform3D tr = new Transform3D();
		node.getLocalToVworld(tr);
		Vector3f position = new Vector3f();
		tr.get(position);
		System.out.println(position);
		
	}

}
