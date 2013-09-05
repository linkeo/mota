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
		// TODO Auto-generated constructor stub
	}
	public void load(JSONObject json){
		loadType(new JSONObject(DataLoader.getAbioticDefine(json.getString("type"))));
		
		setName(json.getString("name"));
		setPosition(new int[]{json.getJSONArray("postion").getInt(0),json.getJSONArray("postion").getInt(1)});
		setFloor(json.getInt("floor"));
		setBuddy(json.getString("buddy"));

	}
	public void loadType(JSONObject json){
		setType(json.getString("type"));
		HashMap<UnitStatus, Animation> sprite = new HashMap<UnitStatus, Animation>();
		JSONObject oSprite = json.getJSONObject("sprites");
		for(Object o : oSprite.keySet()){
			sprite.put(UnitStatus.load((String)o), Animation.create(oSprite.getJSONArray((String)o)));
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
		setBuddyType(json.getString("buddyType"));
	}
	public boolean isCanGoThrough() {
		return canGoThrough;
	}
	public void setCanGoThrough(boolean canGoThrough) {
		this.canGoThrough = canGoThrough;
	}
}
