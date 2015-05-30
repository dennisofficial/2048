package org.croniks.j2048.object;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.croniks.j2048.core.JavaPanel;
import org.croniks.j2048.types.JavaObject;

public class ObjectTile extends JavaObject {

	public Integer LABEL;
	private Integer X;
	private Integer Y;
	private Integer OX;
	private Integer OY;
	private Integer DX = 0;
	private Integer DY = 0;
	private Double SCALE = 1.0;
	private static List<Integer> positions = new ArrayList<Integer>();

	//Animation Properties
	private Integer time = 0;
	private Integer timeEnd = JavaPanel.FPS / 8;
	private Integer direction;
	private Integer count;
	private Boolean morph = false;
	private Boolean expand = false;
	private Boolean move = false;

	public ObjectTile(Integer x, Integer y, Integer label, Boolean expand) {
		X = x;
		Y = y;
		OX = x;
		OY = y;
		for (int i = 0; i < positions.size(); i++) {
			if (label == positions.get(i)) {
				label = i;
			}
		}
		LABEL = label;
		this.expand = expand;
	}

	public void setMorph(Integer count, Integer direction) {
		morph = true;
		this.direction = direction;
		this.count = count;
		DX = 0;
		DY = 0;
		if (direction == 0) {
			Y += count;
		}
		else if (direction == 1) {
			X -= count;
		}
		else if (direction == 2) {
			Y -= count;
		}
		else if (direction == 3) {
			X += count;
		}
	}

	public void move(Integer count, Integer direction) {
		move = true;
		this.direction = direction;
		this.count = count;
		if (direction == 0) {
			Y += count;
		}
		else if (direction == 1) {
			X -= count;
		}
		else if (direction == 2) {
			Y -= count;
		}
		else if (direction == 3) {
			X += count;
		}
	}
	
	public Boolean isMorph() {
		return morph;
	}
	
	public static List<Integer> getPositions() {
		return positions;
	}
	
	public static void settupPos() {
		positions.add(0);
		positions.add(2);
		positions.add(4);
		positions.add(8);
		positions.add(16);
		positions.add(32);
		positions.add(64);
		positions.add(128);
		positions.add(256);
		positions.add(512);
		positions.add(1024);
		positions.add(2048);
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (morph || move) {
			if (time < timeEnd) {
				time++;
				if (direction == 0) {
					if (DY > -(121 * count) + ((121 * count)/timeEnd)) {
						DY -= (121 * count)/timeEnd;
					}
				}
				else if (direction == 1) {
					if (DX < (121 * count) - ((121 * count)/timeEnd)) {
						DX += (121 * count)/timeEnd;
					}
				}
				else if (direction == 2) {
					if (DY < (121 * count) - ((121 * count)/timeEnd)) {
						DY += (121 * count)/timeEnd;
					}
				}
				else if (direction == 3) {
					if (DX > -(121 * count) + ((121 * count)/timeEnd)) {
						DX -= (121 * count)/timeEnd;
					}
				}
			}
			else {
				if (direction == 0) {
					DY = -(121 * count);
				}
				else if (direction == 1) {
					DX = +(121 * count);
				}
				else if (direction == 2) {
					DY = +(121 * count);
				}
				else if (direction == 3) {
					DX = -(121 * count);
				}
				if (morph) {
					expand = true;
					morph = false;
				}
				time = 0;
			}
		}
		if (expand) {
			if (time < timeEnd) {
				time++;
				if (time < timeEnd/2) {
					SCALE += 0.05;
				}
				else {
					SCALE -= 0.05;
				}
			}
			else {
				time = 0;
				expand = false;
				SCALE = 1.0;
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Integer fontSize = null;
		if (positions.get(LABEL) == 2) { fontSize = 53; }
		if (positions.get(LABEL) == 4) { fontSize = 53; }
		if (positions.get(LABEL) == 8) { fontSize = 53; }
		if (positions.get(LABEL) == 16) { fontSize = 53; }
		if (positions.get(LABEL) == 32) { fontSize = 53; }
		if (positions.get(LABEL) == 64) { fontSize = 53; }
		if (positions.get(LABEL) == 128) { fontSize = 43; }
		if (positions.get(LABEL) == 256) { fontSize = 43; }
		if (positions.get(LABEL) == 512) { fontSize = 43; }
		if (positions.get(LABEL) == 1024) { fontSize = 33; }
		if (positions.get(LABEL) == 2048) { fontSize = 33; }
		g.setFont(new Font("Clear Sans", Font.BOLD, fontSize));
		FontMetrics fontMetrics = g.getFontMetrics(new Font("Clear Sans", Font.BOLD, fontSize));
		Integer label = null;
		if (morph) {
			label = positions.get(LABEL - 1);
		}
		else {
			label = positions.get(LABEL);
		}
		if (label == 2) { g.setColor(new Color(238,228,218)); }
		if (label == 4) { g.setColor(new Color(237,224,200)); }
		if (label == 8) { g.setColor(new Color(242,177,121)); }
		if (label == 16) { g.setColor(new Color(245,149,99)); }
		if (label == 32) { g.setColor(new Color(246,124,95)); }
		if (label == 64) { g.setColor(new Color(246,94,59)); }
		if (label == 128) { g.setColor(new Color(237,207,114)); }
		if (label == 256) { g.setColor(new Color(237,204,97)); }
		if (label == 512) { g.setColor(new Color(237,200,80)); }
		if (label == 1024) { g.setColor(new Color(237,200,80)); }
		if (label == 2048) { g.setColor(new Color(237,200,80)); }
		if (morph) {
			g.fillRoundRect(30 + (121 * OX) - (int) (((106 * SCALE) - 106) / 2), 130 + (121 * OY) - (int) (((106 * SCALE) - 106) / 2), (int) (106 * SCALE), (int) (106 * SCALE), 6, 6);
		}
		g.fillRoundRect(30 + (121 * X) - (int) (((106 * SCALE) - 106) / 2) + DX, 130 + (121 * Y) - (int) (((106 * SCALE) - 106) / 2) + DY, (int) (106 * SCALE), (int) (106 * SCALE), 6, 6);
		if (label < 5) {
			g.setColor(new Color(119,110,101));
		}
		else {
			g.setColor(new Color(255, 255, 255));
		}
		Integer offset = 0;
		if (fontSize == 43) {
			offset = 4;
		}
		else if (fontSize == 33) {
			offset = 7;
		}
		if (morph) {
			g.drawString(label.toString(), 82 + (121 * OX) - (fontMetrics.stringWidth(label.toString().trim())/2), 202 + (121 * OY) - offset);
		}
		g.drawString(label.toString(), 82 + (121 * X) - (fontMetrics.stringWidth(label.toString().trim())/2) + DX, 202 + (121 * Y) + DY - offset);
	}

	@Override
	public void keyPressed(Integer k) {
	}

	@Override
	public void keyReleased(Integer k) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
