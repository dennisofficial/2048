package org.croniks.j2048.types;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public interface JavaRoom {

	public void init();
	public void update();
	public void draw(Graphics2D g);
	public void keyPressed(Integer k);
	public void keyReleased(Integer k);
	public void mouseClicked(MouseEvent e);
	public void mousePressed(MouseEvent e);
	public void mouseReleased(MouseEvent e);
	
}
