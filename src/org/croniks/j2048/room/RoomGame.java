package org.croniks.j2048.room;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import org.croniks.j2048.core.JavaPanel;
import org.croniks.j2048.engine.JavaObjectManager;
import org.croniks.j2048.object.ObjectTile;
import org.croniks.j2048.object.ObjectTileController;
import org.croniks.j2048.types.JavaFile;
import org.croniks.j2048.types.JavaRoom;

public class RoomGame extends JavaRoom {

	private JavaObjectManager jom = new JavaObjectManager();
	private static Integer score = 0;
	private static Integer best = 0;
	private static ObjectTileController otc = new ObjectTileController();
	private static File file = new JavaFile("data.dat");

	public RoomGame() {
		jom.registerObject(otc);
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter writer = new PrintWriter(file);
				writer.println(0);
				writer.close();
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			best = new Integer(reader.readLine());
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ObjectTile.settupPos();
	}

	public static void addScore(Integer score) {
		RoomGame.score += score;
	}

	public static void gameOver() {
		RoomGame.otc.reset();
		Integer random = new SecureRandom().nextInt(2) + 2;
		for (int i = 0; i < random; i++) {
			otc.addMore();
		}
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.println(best);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		score = 0;
	}

	@Override
	public void init() {
		jom.init();
		Integer random = new SecureRandom().nextInt(2) + 2;
		for (int i = 0; i < random; i++) {
			otc.addMore();
		}
	}

	@Override
	public void update() {
		if (score > best) {
			best = score;
		}
		jom.update();
	}

	@Override
	public void draw(Graphics2D g) {
		if (g instanceof Graphics2D) {
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//Background
			g.setColor(new Color(250, 248, 239));
			g.fillRect(0, 0, JavaPanel.WIDTH, JavaPanel.HEIGHT);
			//Title String
			g.setColor(new Color(119, 110, 101));
			g.setFont(new Font("Clear Sans", Font.BOLD, 80));
			g.drawString("2048", 15, 80);
			//Score Boxes
			g.setColor(new Color(187,173,160));
			g.fillRoundRect(390, 24, 110, 55, 6, 6);
			g.fillRoundRect(275, 24, 110, 55, 6, 6);
			//Score Labels
			g.setFont(new Font("Clear Sans", Font.BOLD, 13));
			g.setColor(new Color(238,228,218));
			g.drawString("SCORE", 308, 45);
			g.drawString("BEST", 425, 45);
			//Score Numbers
			g.setFont(new Font("Clear Sans", Font.BOLD, 25));
			g.setColor(new Color(255, 255, 255));
			FontMetrics fontMetrics = g.getFontMetrics(new Font("Clear Sans", Font.BOLD, 25));
			g.drawString(score.toString(), 331 - (fontMetrics.stringWidth(score.toString())/2), 70);
			g.drawString(best.toString(), 442 - (fontMetrics.stringWidth(best.toString())/2), 70);
			//Grid Background
			g.setFont(new Font("Clear Sans", Font.BOLD, 18));
			g.setColor(new Color(187,173,160));
			g.fillRoundRect(15, 115, 500, 500, 12, 12);
			//Grid Tiles
			g.setColor(new Color(238, 228, 218, 65));
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					g.fillRoundRect(30 + (121 * i), 130 + (121 * j), 106, 106, 6, 6);
				}
			}
		}
		jom.draw(g);
	}

	@Override
	public void keyPressed(Integer k) {
		jom.keyPressed(k);
	}

	@Override
	public void keyReleased(Integer k) {
		jom.keyReleased(k);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jom.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		jom.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		jom.mouseReleased(e);
	}

}
