package org.croniks.j2048.object;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.croniks.j2048.core.JavaPanel;
import org.croniks.j2048.room.RoomGame;
import org.croniks.j2048.types.JavaObject;

public class ObjectTileController extends JavaObject {

	private static List<List<ObjectTile>> tiles = new ArrayList<List<ObjectTile>>(); 

	public Boolean isEmpty(Integer x, Integer y) {
		Boolean output = null;
		if (getTile(x, y) == null) {
			output = true;
		}
		else {
			output = false;
		}
		return output;
	}

	public ObjectTile setTile(Integer x, Integer y, Integer label, Boolean expand) {
		ObjectTile tile = new ObjectTile(x, y, ObjectTile.getPositions().get(label), expand);
		tiles.get(x).set(y, tile);
		return tile;
	}

	public static ObjectTile getTile(Integer x, Integer y) {
		return tiles.get(x).get(y);
	}

	public Integer getLabel(Integer x, Integer y) {
		return getTile(x, y).LABEL;
	}

	public void clearTile(Integer x, Integer y) {
		tiles.get(x).set(y, null);
	}
	
	public void reset() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				clearTile(x, y);
			}
		}
	}

	public void addMore() {
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep((1000/JavaPanel.FPS) * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Integer tileX;
				Integer tileY;
				Integer tileLabel;
				do {
					tileX = (new SecureRandom().nextInt(4));
					tileY = (new SecureRandom().nextInt(4));
					tileLabel = (1 + new SecureRandom().nextInt(2));
				} while (!isEmpty(tileX, tileY));
				setTile(tileX, tileY, tileLabel, true);
			}
		});
		thread.start();
	}

	private void moveLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 1; x < 4; x++) {
				if (getTile(x, y) != null) {
					Integer count = 0;
					Boolean morph = false;
					for (int i = 1; i < x + 1; i++) {
						if (getTile(x - i, y) == null) {
							count++;
						}
						else if (getTile(x - i, y).LABEL == getTile(x, y).LABEL && !getTile(x - i, y).isMorph()) {
							morph = true;
							count++;
							break;
						}
						else {
							break;
						}
					}
					if (morph) {
						ObjectTile tile = setTile(x - count, y, getTile(x, y).LABEL + 1, true);
						RoomGame.addScore(ObjectTile.getPositions().get(getTile(x, y).LABEL) * 2);
						tile.setMorph(count, 3);
						clearTile(x, y);
					}
					else {
						Integer label = getTile(x, y).LABEL;
						clearTile(x, y);
						ObjectTile tile = setTile(x - count, y, label, false);
						tile.move(count, 3);
					}
				}
			}
		}
	}

	private void moveRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 2; x > -1; x--) {
				if (getTile(x, y) != null) {
					Integer count = 0;
					Boolean morph = false;
					for (int i = 1; i < 4 - x; i++) {
						if (getTile(x + i, y) == null) {
							count++;
						}
						else if (getTile(x + i, y).LABEL == getTile(x, y).LABEL && !getTile(x + i, y).isMorph()) {
							morph = true;
							count++;
							break;
						}
						else {
							break;
						}
					}
					if (morph) {
						ObjectTile tile = setTile(x + count, y, getTile(x, y).LABEL + 1, true);
						RoomGame.addScore(ObjectTile.getPositions().get(getTile(x, y).LABEL) * 2);
						tile.setMorph(count, 1);
						clearTile(x, y);
					}
					else {
						Integer label = getTile(x, y).LABEL;
						clearTile(x, y);
						ObjectTile tile = setTile(x + count, y, label, false);
						tile.move(count, 1);
					}
				}
			}
		}
	}

	private void moveUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 1; y < 4; y++) {
				if (getTile(x, y) != null) {
					Integer count = 0;
					Boolean morph = false;
					for (int i = 1; i < y + 1; i++) {
						if (getTile(x, y - i) == null) {
							count++;
						}
						else if (getTile(x, y - i).LABEL == getTile(x, y).LABEL && !getTile(x, y - i).isMorph()) {
							morph = true;
							count++;
							break;
						}
						else {
							break;
						}
					}
					if (morph) {
						ObjectTile tile = setTile(x, y - count, getTile(x, y).LABEL + 1, true);
						RoomGame.addScore(ObjectTile.getPositions().get(getTile(x, y).LABEL) * 2);
						tile.setMorph(count, 0);
						clearTile(x, y);
					}
					else {
						Integer label = getTile(x, y).LABEL;
						clearTile(x, y);
						ObjectTile tile = setTile(x, y - count, label, false);
						tile.move(count, 0);
					}
				}
			}
		}
	}

	private void moveDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 2; y > -1; y--) {
				if (getTile(x, y) != null) {
					Integer count = 0;
					Boolean morph = false;
					for (int i = 1; i < 4 - y; i++) {
						if (getTile(x, y + i) == null) {
							count++;
						}
						else if (getTile(x, y + i).LABEL == getTile(x, y).LABEL && !getTile(x, y + i).isMorph()) {
							morph = true;
							count++;
							break;
						}
						else {
							break;
						}
					}
					if (morph) {
						ObjectTile tile = setTile(x, y + count, getTile(x, y).LABEL + 1, true);
						RoomGame.addScore(ObjectTile.getPositions().get(getTile(x, y).LABEL) * 2);
						tile.setMorph(count, 2);
						clearTile(x, y);
					}
					else {
						Integer label = getTile(x, y).LABEL;
						clearTile(x, y);
						ObjectTile tile = setTile(x, y + count, label, false);
						tile.move(count, 2);
					}
				}
			}
		}
	}

	@Override
	public void init() {
		for (int i = 0; i < 4; i++) {
			List<ObjectTile> temp = new ArrayList<ObjectTile>();
			for (int j = 0; j < 4; j++) {
				temp.add(null);
			}
			tiles.add(temp);
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getTile(x, y) != null) {
					getTile(x, y).init();
				}
			}
		}
	}

	@Override
	public void update() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getTile(x, y) != null) {
					getTile(x, y).update();
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getTile(x, y) != null) {
					getTile(x, y).draw(g);
				}
			}
		}
	}

	@Override
	public void keyPressed(Integer k) {
		List<Integer> labels = new ArrayList<Integer>();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getTile(x, y) != null) {
					labels.add(getTile(x, y).LABEL);
				}
				else {
					labels.add(0);
				}
			}
		}
		if (k == KeyEvent.VK_LEFT) {
			moveLeft();
		}
		if (k == KeyEvent.VK_RIGHT) {
			moveRight();
		}
		if (k == KeyEvent.VK_UP) {
			moveUp();
		}
		if (k == KeyEvent.VK_DOWN) {
			moveDown();
		}
		Boolean space = false;
		Boolean moved = false;
		Integer index = 0;
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getTile(x, y) == null) {
					space = true;
					if (labels.get(index) != 0) {
						moved = true;
					}
				}
				else if (getTile(x, y).LABEL != labels.get(index)) {
					moved = true;
				}
				index++;
			}
		}
		Boolean reset = false;
		if (moved) {
			addMore();
		}
		if (!space) {
			reset = true;
			for (int x = 0; x < 3; x++) {
				for (int y = 0; y < 3; y++) {
					if (getTile(x, y) != null) {
						if (getTile(x + 1, y).LABEL == getTile(x, y).LABEL) {
							reset = false;
						}
						else if (getTile(x, y + 1).LABEL == getTile(x, y).LABEL) {
							reset = false;
						}
					}
				}
			}
		}
		if (reset) {
			RoomGame.gameOver();
		}
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
