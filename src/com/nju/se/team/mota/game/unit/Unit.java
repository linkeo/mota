package com.nju.se.team.mota.game.unit;

import java.awt.Rectangle;
import java.util.HashMap;

import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;

public class Unit {
	private String name;
	private String type;
	private HashMap<UnitStatus, Animation> sprites;
	private HashMap<Condition, String> action;
	
	private int position[];
	private int size[];
	private int floor;
	private String buddy;
	private String buddyType;
	
	public Unit() {
		name = "new";
		sprites = new HashMap<UnitStatus, Animation>();
		action = new HashMap<Condition, String>();
		position = new int[]{1,1};
		size = new int[]{1,1};
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public HashMap<UnitStatus, Animation> getSprites() {
		return sprites;
	}
	public void setSprites(HashMap<UnitStatus, Animation> sprites) {
		this.sprites = sprites;
	}
	public HashMap<Condition, String> getAction() {
		return action;
	}
	public void setAction(HashMap<Condition, String> action) {
		this.action = action;
	}
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	public int[] getSize() {
		return size;
	}
	public void setSize(int[] size) {
		this.size = size;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getBuddy() {
		return buddy;
	}
	public void setBuddy(String buddy) {
		this.buddy = buddy;
	}
	public String getBuddyType() {
		return buddyType;
	}
	public void setBuddyType(String buddyType) {
		this.buddyType = buddyType;
	}
	public Rectangle getRect(){
		return new Rectangle(position[0], position[1], size[0], size[1]);
	}
	public void setPosition(int x, int y) {
		position[0] = x;
		position[1] = y;
	}
	public void setSize(int w, int h) {
		size[0] = w;
		size[1] = h;
	}
}
