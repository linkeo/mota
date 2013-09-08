package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;

public class Abiotic extends Unit{
	private boolean canGoThrough;
	public Abiotic() {
		canGoThrough = false;
		setBuddy("none");
	}
	public static Abiotic make(JSONObject json){
		Abiotic a = new Abiotic();
		a.load(json);
		return a;
	}
	public static Abiotic defaultAbiotic(){
		Abiotic a = new Abiotic();
		a.setName("new");
		a.setSize(new int[]{1,1});
		a.setBuddyType("none");
		return a;
	}
	public void load(JSONObject json){
		loadType(new JSONObject(DataLoader.getAbioticDefine(json.getString("type"))));
		setName(json.getString("name"));
		setPosition(new int[]{json.getJSONArray("position").getInt(0),json.getJSONArray("position").getInt(1)});
		setFloor(json.getInt("floor"));
		setBuddy(json.optString("buddy"));
	}
	public JSONObject parseEntityJSON(){
		JSONObject json = toJSON();
		json.remove("type");
		json.remove("sprites");
		json.remove("canGoThrough");
		json.remove("action");
		json.remove("size");
		json.remove("buddyType");
		return json;
	}
	public JSONObject parseTypeJSON(){
		JSONObject json = toJSON();
		json.remove("name");
		json.remove("position");
		json.remove("floor");
		json.remove("buddy");
		return json;
	}
	public JSONObject toJSON(){
		return new JSONObject(this);
	}
	public void loadType(JSONObject json){
		setType(json.getString("type"));
		HashMap<UnitStatus, Animation> sprite = new HashMap<UnitStatus, Animation>();
		JSONObject oSprite = json.getJSONObject("sprites");
		for(Object o : oSprite.keySet()){
			sprite.put(UnitStatus.load((String)o), Animation.make(oSprite.getJSONObject((String)o)));
		}
		setSprites(sprite);
		setCanGoThrough(json.getBoolean("canGoThrough"));
		HashMap<Condition, String> action = new HashMap<Condition, String>();
		JSONObject oAction = json.getJSONObject("action");
		for(Object a : oAction.keySet()){
			action.put(Condition.load((String)a), oAction.getString((String)a));
		}
		setAction(action);
		setSize(new int[]{json.getJSONArray("size").getInt(0),json.getJSONArray("size").getInt(1)});
		setBuddyType(json.optString("buddyType"));
	}
	@SuppressWarnings("unchecked")
	public Abiotic copy(){
		Abiotic a = new Abiotic();
		a.setName(getName());
		a.setPosition(getPosition().clone());
		a.setFloor(getFloor());
		a.setBuddy(getBuddy());
		a.setType(getType());
		a.setSprites((HashMap<UnitStatus, Animation>) getSprites().clone());
		a.setCanGoThrough(isCanGoThrough());
		a.setAction((HashMap<Condition, String>) getAction().clone());
		a.setSize(getSize().clone());
		a.setBuddyType(getBuddyType());
		return a;
	}
	public boolean isCanGoThrough() {
		return canGoThrough;
	}
	public void setCanGoThrough(boolean canGoThrough) {
		this.canGoThrough = canGoThrough;
	}
}
