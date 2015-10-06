package org.croniks.j2048.engine;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.croniks.j2048.room.RoomGame;
import org.croniks.j2048.types.JavaRoom;

public class JavaRoomManager {

	public static List<JavaRoom> rooms = new ArrayList<JavaRoom>();

	public final Integer ROOM_GAME = 0;
	
	private Integer currentRoom;
	
	public JavaRoomManager() {
		currentRoom = ROOM_GAME;
		rooms.add(new RoomGame());
	}
	
	public void setCurrentRoom(Integer currentRoom) {
		this.currentRoom = currentRoom;
	}
	
	public void init() {
		rooms.get(currentRoom).init();
	}
	
	public void update() {
		rooms.get(currentRoom).update();
	}
	
	public void draw(Graphics2D g) {
		rooms.get(currentRoom).draw(g);
	}

	public void keyPressed(Integer k) {
		rooms.get(currentRoom).keyPressed(k);
	}

	public void keyReleased(Integer k) {
		rooms.get(currentRoom).keyReleased(k);
	}
	
	public void mouseClicked(MouseEvent e) {
		rooms.get(currentRoom).mouseClicked(e);
	}
	
	public void mousePressed(MouseEvent e) {
		rooms.get(currentRoom).mousePressed(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		rooms.get(currentRoom).mouseReleased(e);
	}
	
}
