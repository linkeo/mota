package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;
/**
 * Unit子类型之生物
 * @author linkeo
 * @author lzw
 *
 */
public class Creature extends Unit{
	private int HP;
	private int ATK;
	private int DEF;
	private int Money;
	private int EXP;
	/**
	 * 构造方法<br>
	 * 设置默认属性
	 */
	public Creature() {
		HP = 10;
		ATK = 1;
		DEF = 1;
		Money = 0;
		EXP = 0;
		setBuddy("none");
	}
	/**
	 * 从json加载数据
	 * @param json
	 * @return Creature
	 */
	public static Creature make(JSONObject json){
		Creature instance = new Creature();
		instance.load(json);
		return instance;
	}
	/**
	 * 默认Creature
	 * @return Creature
	 */
	public static Creature defaultCreature() {
		Creature c = new Creature();
		c.setType("new");
		c.setBuddyType("none");
		return c;
	}
	/**
	 * 从json加载具体对象的数据
	 * @param json
	 */
	public void load(JSONObject json){
		loadType(DataLoader.getCreatureDefine(json.getString("type")));
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
		json.remove("sprites");
		json.remove("action");
		json.remove("size");
		json.remove("buddyType");
		json.remove("HP");
		json.remove("ATK");
		json.remove("DEF");
		json.remove("money");
		json.remove("EXP");
		if(getBuddy()==null||getBuddy().isEmpty()||getBuddy().equalsIgnoreCase("none"))
			json.remove("buddy");
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
		HashMap<Condition, String> action = new HashMap<Condition, String>();
		JSONObject oAction = json.getJSONObject("action");
		for(Object a : oAction.keySet()){
			action.put(Condition.load((String)a), oAction.getString((String)a));
		}
		setAction(action);
		setSize(json.getJSONArray("size").getInt(0),json.getJSONArray("size").getInt(1));
		setBuddyType(json.optString("buddyType"));
		setHP(json.getInt("HP"));
		setATK(json.getInt("ATK"));
		setDEF(json.getInt("DEF"));
		setMoney(json.getInt("money"));
		setEXP(json.getInt("EXP"));
	}	
	/**
	 * 获取对象克隆
	 * @return Creature
	 */
	public Creature clone(){
		Creature c = new Creature();
		c.setName(getName());
		c.setPosition(getPosition().clone());
		c.setFloor(getFloor());
		c.setBuddy(getBuddy());
		c.setType(getType());
		c.setSprites(new HashMap<UnitStatus, Animation> (getSprites()));
		c.setAction(new HashMap<Condition, String>( getAction()));
		c.setSize(getSize().clone());
		c.setBuddyType(getBuddyType());
		c.setHP(getHP());
		c.setATK(getATK());
		c.setDEF(getDEF());
		c.setMoney(getMoney());
		c.setEXP(getEXP());
		return c;
	}
	/**
	 * 获取生命值
	 * @return HP
	 */
	public int getHP() {
		return HP;
	}
	/**
	 * 设置生命值
	 */
	public void setHP(int hP) {
		HP = hP;
	}
	/**
	 * 获取攻击力
	 * @return ATK
	 */
	public int getATK() {
		return ATK;
	}
	/**
	 * 设置攻击力
	 * @return ATK
	 */
	public void setATK(int aTK) {
		ATK = aTK;
	}
	/**
	 * 获取防御力
	 * @return DEF
	 */
	public int getDEF() {
		return DEF;
	}
	/**
	 * 设置防御力
	 */
	public void setDEF(int dEF) {
		DEF = dEF;
	}
	/**
	 * 获取金钱
	 * @return Money
	 */
	public int getMoney() {
		return Money;
	}
	/**
	 * 设置金钱
	 */
	public void setMoney(int money) {
		Money = money;
	}
	/**
	 * 获取经验值
	 * @return EXP
	 */
	public int getEXP() {
		return EXP;
	}
	/**
	 * 设置经验值
	 */
	public void setEXP(int eXP) {
		EXP = eXP;
	}/**
	 * 创造一个具体的Creature对象
	 * @param type 具体类型
	 * @param x 横坐标
	 * @param y 纵坐标
	 * @param z 楼层
	 * @return Creature
	 */
	public static Creature make(String type, int x, int y, int z) {
		Creature c = new Creature();

		c.loadType(DataLoader.getCreatureDefine(type));
		c.setName(type);
		c.setPosition(x, y);
		c.setFloor(z);
		c.setBuddy("none");
		
		return c;
	}
}
