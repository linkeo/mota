package com.nju.se.team.mota.game.unit;

import java.util.HashMap;

import org.json.JSONObject;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.util.Animation;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.game.util.UnitStatus;
/**
 * Unit������֮����
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
	 * ���췽��<br>
	 * ����Ĭ������
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
	 * ��json��������
	 * @param json
	 * @return Creature
	 */
	public static Creature make(JSONObject json){
		Creature instance = new Creature();
		instance.load(json);
		return instance;
	}
	/**
	 * Ĭ��Creature
	 * @return Creature
	 */
	public static Creature defaultCreature() {
		Creature c = new Creature();
		c.setType("new");
		c.setBuddyType("none");
		return c;
	}
	/**
	 * ��json���ؾ�����������
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
	 * ��ȡ��������json<br>
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
	 * ��ȡ�����¡
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
	 * ��ȡ����ֵ
	 * @return HP
	 */
	public int getHP() {
		return HP;
	}
	/**
	 * ��������ֵ
	 */
	public void setHP(int hP) {
		HP = hP;
	}
	/**
	 * ��ȡ������
	 * @return ATK
	 */
	public int getATK() {
		return ATK;
	}
	/**
	 * ���ù�����
	 * @return ATK
	 */
	public void setATK(int aTK) {
		ATK = aTK;
	}
	/**
	 * ��ȡ������
	 * @return DEF
	 */
	public int getDEF() {
		return DEF;
	}
	/**
	 * ���÷�����
	 */
	public void setDEF(int dEF) {
		DEF = dEF;
	}
	/**
	 * ��ȡ��Ǯ
	 * @return Money
	 */
	public int getMoney() {
		return Money;
	}
	/**
	 * ���ý�Ǯ
	 */
	public void setMoney(int money) {
		Money = money;
	}
	/**
	 * ��ȡ����ֵ
	 * @return EXP
	 */
	public int getEXP() {
		return EXP;
	}
	/**
	 * ���þ���ֵ
	 */
	public void setEXP(int eXP) {
		EXP = eXP;
	}/**
	 * ����һ�������Creature����
	 * @param type ��������
	 * @param x ������
	 * @param y ������
	 * @param z ¥��
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
