package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;
/**
 * Unit子类型之非生物
 * @author linkeo
 * @author lzw
 *
 */
public class Abiotic extends Unit{
	private boolean canGoThrough;
	/**
	 * 构造方法<br>
	 * 默认不可通过
	 */
	public Abiotic() {
		canGoThrough = false;
		setBuddy("none");
	}
	/**
	 * 从json加载数据
	 * @param json
	 * @return Abiotic
	 */
	public static Abiotic make(JSONObject json){
		Abiotic a = new Abiotic();
		a.load(json);
		return a;
	}
	/**
	 * 默认Abiotic
	 * @return Abiotic
	 */
	public static Abiotic defaultAbiotic(){
		Abiotic a = new Abiotic();
		a.setType("new");
		a.setBuddyType("none");
		return a;
	}
	/**
	 * 从json加载具体对象的数据
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
	 * 获取具体对象的json<br>
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
	 * 获取抽象类型的json
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
	 * 转化为JSONObject对象
	 * @return JSONObject
	 */
	public JSONObject toJSON(){
		return new JSONObject(this);
	}
	/**
	 * 从json加载抽象类型的数据
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
	 * 获取对象克隆
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
	 * 获取是否可穿透
	 * @return boolean
	 */
	public boolean isCanGoThrough() {
		return canGoThrough;
	}
	/**
	 * 设置时候可穿透
	 * @param canGoThrough(boolean)
	 */
	public void setCanGoThrough(boolean canGoThrough) {
		this.canGoThrough = canGoThrough;
	}
	/**
	 * 创造一个具体的Abiotic对象
	 * @param type 具体类型
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param z 楼层
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
