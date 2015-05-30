package org.croniks.j2048.core;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.croniks.j2048.engine.JavaRoomManager;

@SuppressWarnings("serial")
public class JavaPanel extends JPanel implements Runnable, KeyListener, MouseListener {

	public final static Integer HEIGHT = 630;
	public final static Integer WIDTH = 530;
	public final static Integer FPS = 60;
	
	private Thread t;
	private BufferedImage image;
	private Graphics2D g;
	private JavaRoomManager jrm = new JavaRoomManager();
	
	public JavaPanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		init();
		while (true) {
			update();
			repaint();
			try {
				Thread.sleep(1000/FPS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		addKeyListener(this);
		addMouseListener(this);
		jrm.init();
	}
	
	public void update() {
		jrm.update();
	}
	
	@Override
	public void paint(Graphics g2) {
		jrm.draw(g);
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		jrm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		jrm.keyReleased(e.getKeyCode());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		jrm.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		jrm.mouseReleased(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jrm.mouseClicked(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
