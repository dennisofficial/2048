package org.croniks.j2048.engine;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.croniks.j2048.types.JavaObject;

public class JavaObjectManager {

	private List<JavaObject> objects = new ArrayList<JavaObject>();
	
	public void registerObject(JavaObject jo) {
		objects.add(jo);
	}

	public void init() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).init();
		}
	}
	
	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).update();
		}
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).draw(g);
		}
	}

	public void keyPressed(Integer k) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).keyPressed(k);
		}
	}

	public void keyReleased(Integer k) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).keyReleased(k);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).mouseClicked(e);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).mousePressed(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).mouseReleased(e);
		}
	}
	
}
