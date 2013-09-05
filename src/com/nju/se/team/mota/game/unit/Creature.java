package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;

public class Creature extends Unit{
	private int HP;
	private int ATK;
	private int DEF;
	private int Money;
	private int EXP;
	
	public Creature() {
		HP = 10;
		ATK = 1;
		DEF = 1;
		Money = 0;
		EXP = 0;
	}
	
	public static Creature make(JSONObject json){
		Creature instance = new Creature();
		instance.load(json);
		return instance;
	}
	
	public void load(JSONObject json){
		loadType(new JSONObject(DataLoader.getAbioticDefine(json.getString("type"))));
		
		setName(json.getString("name"));
		setPosition(new int[]{json.getJSONArray("postion").getInt(0),json.getJSONArray("postion").getInt(1)});
		setFloor(json.getInt("floor"));
		setBuddy(json.optString("buddy"));

	}
	public void loadType(JSONObject json){
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
		setSize(new int[]{json.getJSONArray("size").getInt(0),json.getJSONArray("size").getInt(1)});
		setBuddyType(json.optString("buddyType"));
		setHP(json.getInt("HP"));
		setATK(json.getInt("ATK"));
		setDEF(json.getInt("DEF"));
		setMoney(json.getInt("Money"));
		setEXP(json.getInt("EXP"));
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getATK() {
		return ATK;
	}
	public void setATK(int aTK) {
		ATK = aTK;
	}
	public int getDEF() {
		return DEF;
	}
	public void setDEF(int dEF) {
		DEF = dEF;
	}
	public int getMoney() {
		return Money;
	}
	public void setMoney(int money) {
		Money = money;
	}
	public int getEXP() {
		return EXP;
	}
	public void setEXP(int eXP) {
		EXP = eXP;
	}
}
