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
	private static List<Color> colors = new ArrayList<Color>();

	// Animation Properties
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
		} else if (direction == 1) {
			X -= count;
		} else if (direction == 2) {
			Y -= count;
		} else if (direction == 3) {
			X += count;
		}
	}

	public void move(Integer count, Integer direction) {
		move = true;
		this.direction = direction;
		this.count = count;
		switch (direction) {
		case 0:
			Y += count;
			break;
		case 1:
			X -= count;
			break;
		case 2:
			Y -= count;
			break;
		case 3:
			X += count;
			break;
		default:
			break;
		}
	}

	public Boolean isMorph() {
		return morph;
	}

	public static void setupColors() {
		colors.add(new Color(238, 228, 218));
		colors.add(new Color(237, 224, 200));
		colors.add(new Color(242, 177, 121));
		colors.add(new Color(245, 149, 99));
		colors.add(new Color(246, 124, 95));
		colors.add(new Color(246, 94, 59));
		colors.add(new Color(237, 207, 114));
		colors.add(new Color(237, 204, 97));
		colors.add(new Color(237, 200, 80));
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
					if (DY > -(121 * count) + ((121 * count) / timeEnd)) {
						DY -= (121 * count) / timeEnd;
					}
				} else if (direction == 1) {
					if (DX < (121 * count) - ((121 * count) / timeEnd)) {
						DX += (121 * count) / timeEnd;
					}
				} else if (direction == 2) {
					if (DY < (121 * count) - ((121 * count) / timeEnd)) {
						DY += (121 * count) / timeEnd;
					}
				} else if (direction == 3) {
					if (DX > -(121 * count) + ((121 * count) / timeEnd)) {
						DX -= (121 * count) / timeEnd;
					}
				}
			} else {
				if (direction == 0) {
					DY = -(121 * count);
				} else if (direction == 1) {
					DX = +(121 * count);
				} else if (direction == 2) {
					DY = +(121 * count);
				} else if (direction == 3) {
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
				if (time < timeEnd / 2) {
					SCALE += 0.05;
				} else {
					SCALE -= 0.05;
				}
			} else {
				time = 0;
				expand = false;
				SCALE = 1.0;
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		Integer fontSize = null;
		if (LABEL > 0) {
			fontSize = 53;
		}
		if (LABEL >= 7) {
			fontSize = 43;
		}
		if (LABEL >= 10) {
			fontSize = 33;
		}
		g.setFont(new Font("Clear Sans", Font.BOLD, fontSize));
		FontMetrics fontMetrics = g.getFontMetrics(new Font("Clear Sans", Font.BOLD, fontSize));
		Integer label = LABEL;
		if (morph) {
			label = LABEL - 1;
		}
		try {
			g.setColor(colors.get(label - 1));
		} catch (IndexOutOfBoundsException ex) {
			g.setColor(new Color(237, 200, 80));
		}
		if (morph) {
			g.fillRoundRect(30 + (121 * OX) - (int) (((106 * SCALE) - 106) / 2),
					130 + (121 * OY) - (int) (((106 * SCALE) - 106) / 2), (int) (106 * SCALE), (int) (106 * SCALE), 6,
					6);
		}
		g.fillRoundRect(30 + (121 * X) - (int) (((106 * SCALE) - 106) / 2) + DX,
				130 + (121 * Y) - (int) (((106 * SCALE) - 106) / 2) + DY, (int) (106 * SCALE), (int) (106 * SCALE), 6,
				6);
		if (label < 3) {
			g.setColor(new Color(119, 110, 101));
		} else {
			g.setColor(new Color(255, 255, 255));
		}
		Integer offset = 0;
		if (fontSize == 43) {
			offset = 4;
		} else if (fontSize == 33) {
			offset = 7;
		}
		String str = "" + (int) Math.pow(2, label);
		if (morph) {
			g.drawString(str, 82 + (121 * OX) - (fontMetrics.stringWidth(str) / 2), 202 + (121 * OY) - offset);
		}
		g.drawString(str, 82 + (121 * X) - (fontMetrics.stringWidth(str) / 2) + DX, 202 + (121 * Y) + DY - offset);
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
