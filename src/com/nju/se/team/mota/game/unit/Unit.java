package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

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
		name = "floor";
		type = "floor";
		sprites = new HashMap<UnitStatus, Animation>();
		action = new HashMap<Condition, String>();
		position = new int[]{1,1};
		size = new int[]{1,1};
	}
	public static Unit make(JSONObject json){
		Unit instance = new Unit();
		instance.load(json);
		return instance;
	}
	
	public void load(JSONObject json){
		setName(json.getString("name"));
		setType(json.getString("type"));
		HashMap<UnitStatus, Animation> sprite = new HashMap<UnitStatus, Animation>();
		JSONObject oSprite = json.getJSONObject("sprites");
		for(Object o : oSprite.keySet()){
			sprite.put(UnitStatus.load((String)o), Animation.make(oSprite.getJSONArray((String)o)));
		}
		setSprites(sprite);
		HashMap<Condition, String> action = new HashMap<Condition, String>();
		JSONObject oAction = json.getJSONObject("action");
		for(Object a : oAction.keySet()){
			action.put(Condition.load((String)a), oAction.getString((String)a));
		}
		setAction(action);
		setPosition(new int[]{json.getJSONArray("postion").getInt(0),json.getJSONArray("postion").getInt(1)});
		setSize(new int[]{json.getJSONArray("size").getInt(0),json.getJSONArray("size").getInt(1)});
		setFloor(json.getInt("floor"));
		setBuddy(json.optString("buddy"));
		setBuddyType(json.optString("buddyType"));
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
	
}
