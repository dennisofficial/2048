package org.croniks.j2048.types;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class JavaObject {

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void keyPressed(Integer k);
	public abstract void keyReleased(Integer k);
	public abstract void mouseClicked(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	
}
