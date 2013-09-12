package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;
/**
 * Unit������֮������
 * @author linkeo
 * @author lzw
 *
 */
public class Abiotic extends Unit{
	private boolean canGoThrough;
	/**
	 * ���췽��<br>
	 * Ĭ�ϲ���ͨ��
	 */
	public Abiotic() {
		canGoThrough = false;
		setBuddy("none");
	}
	/**
	 * ��json��������
	 * @param json
	 * @return Abiotic
	 */
	public static Abiotic make(JSONObject json){
		Abiotic a = new Abiotic();
		a.load(json);
		return a;
	}
	/**
	 * Ĭ��Abiotic
	 * @return Abiotic
	 */
	public static Abiotic defaultAbiotic(){
		Abiotic a = new Abiotic();
		a.setType("new");
		a.setBuddyType("none");
		return a;
	}
	/**
	 * ��json���ؾ�����������
	 * @param json
	 */
	public void load(JSONObject json){
		loadType(DataLoader.getAbioticDefine(json.getString("type")));
		setName(json.getString("name"));
		setPosition(json.getJSONArray("position").getInt(0),json.getJSONArray("position").getInt(1));
		setFloor(json.getInt("floor"));
		setBuddy(json.optString("buddy"));
	}
	/**
	 * ��ȡ��������json<br>
	 * @return json
	 */
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
	/**
	 * ��ȡ�������͵�json
	 * @return json
	 */
	public JSONObject parseTypeJSON(){
		JSONObject json = toJSON();
		json.remove("name");
		json.remove("position");
		json.remove("floor");
		json.remove("buddy");
		return json;
	}
	/**
	 * ת��ΪJSONObject����
	 * @return JSONObject
	 */
	public JSONObject toJSON(){
		return new JSONObject(this);
	}
	/**
	 * ��json���س������͵�����
	 * @param json
	 */
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
		setSize(json.getJSONArray("size").getInt(0),json.getJSONArray("size").getInt(1));
		setBuddyType(json.optString("buddyType"));
	}
	/**
	 * ��ȡ�����¡
	 * @return Abiotic
	 */
	public Abiotic clone(){
		Abiotic a = new Abiotic();
		a.setName(getName());
		a.setPosition(getPosition().clone());
		a.setFloor(getFloor());
		a.setBuddy(getBuddy());
		a.setType(getType());
		a.setSprites(new HashMap<UnitStatus, Animation> (getSprites()));
		a.setCanGoThrough(isCanGoThrough());
		a.setAction(new HashMap<Condition, String>( getAction()));
		a.setSize(getSize().clone());
		a.setBuddyType(getBuddyType());
		return a;
	}
	/**
	 * ��ȡ�Ƿ�ɴ�͸
	 * @return boolean
	 */
	public boolean isCanGoThrough() {
		return canGoThrough;
	}
	/**
	 * ����ʱ��ɴ�͸
	 * @param canGoThrough(boolean)
	 */
	public void setCanGoThrough(boolean canGoThrough) {
		this.canGoThrough = canGoThrough;
	}
	/**
	 * ����һ�������Abiotic����
	 * @param type ��������
	 * @param x ������
	 * @param y ������
	 * @param z ¥��
	 * @return Abiotic
	 */
	public static Abiotic make(String type, int x, int y, int z) {
		Abiotic a = new Abiotic();
		a.loadType(DataLoader.getAbioticDefine(type));
		a.setName(type);
		a.setPosition(x, y);
		a.setFloor(z);
		a.setBuddy("none");
		
		return a;
	}
}
