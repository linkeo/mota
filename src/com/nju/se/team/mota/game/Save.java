package com.nju.se.team.mota.game;

import java.util.ArrayList;

import org.json.JSONObject;

import com.nju.se.team.mota.data.SaveLoader;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Tool;
/**
 * 存档
 * @author linkeo
 * @author lzw
 *
 */
public class Save {
	private String name;
	private int lv;
	private int hp;
	private int atk;
	private int def;
	private int money;
	private int exp;
	private int[] keys;
	private ArrayList<Tool> tools = new ArrayList<Tool>();
	private int floor;
	private int[] position;
	private String savetime;
	private Level[] levels;
	private static final int RED_KEY=0, YELLOW_KEY=1, BLUE_KEY=2;
	/**
	 * 构造函数
	 */
	public Save() {
		// TODO Auto-generated constructor stub
	}
	public static Save newSave(String name){
		Save s = SaveLoader.getDefaultSave();
		s.setName(name);
		return s;
	}
	/**
	 * 创造一个存档对象
	 * @param json
	 * @return Level
	 */
	public static Save make(JSONObject json){
		Save s = new Save();
		s.load(json);;
		return s;
	}
	/**
	 * 从json加载数据
	 * @param json
	 */
	public void load(JSONObject json){
		setName(json.getString("name"));
		setLv(json.getInt("lv"));
		setHp(json.getInt("hp"));
		setAtk(json.getInt("atk"));
		setDef(json.getInt("def"));
		setMoney(json.getInt("money"));
		setExp(json.getInt("exp"));
		setKeys(new int[]{json.getJSONArray("keys").getInt(0),json.getJSONArray("keys").getInt(1),json.getJSONArray("keys").getInt(2)});
		tools.clear();
		for(int i=0;i<json.getJSONArray("tools").length();++i){
			tools.add(Tool.parse(Abiotic.make(json.getJSONArray("tools").getJSONObject(i))));
		}
		setFloor(json.getInt("floor"));

		setPosition(new int[]{json.getJSONArray("position").getInt(0),json.getJSONArray("position").getInt(1)});
		setSavetime(json.getString("savetime"));
		Level[] levels = new Level[json.getJSONArray("levels").length()];
		for(int i=0;i<json.getJSONArray("tools").length();++i){
			levels[i] = Level.make(json.getJSONArray("levels").getJSONObject(i));
		}
		setLevels(levels);
	}
	public Player player(){
		Player player = new Player();
		player.setName(getName());
		player.setLV(getLv());
		player.setHP(getHp());
		player.setATK(getAtk());
		player.setDEF(getDef());
		player.setMoney(getMoney());
		player.setEXP(getExp());
		player.setRedkey(getKeys()[RED_KEY]);
		player.setYellowkey(getKeys()[YELLOW_KEY]);
		player.setBluekey(getKeys()[BLUE_KEY]);
		player.setFloor(getFloor());
		for(Tool tool: getTools())
			player.getTools().add(tool);
		player.setPosition(getPosition().clone());
		return player;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int[] getKeys() {
		return keys;
	}
	public void setKeys(int[] keys) {
		this.keys = keys;
	}
	public ArrayList<Tool> getTools() {
		return tools;
	}
	public void setTools(ArrayList<Tool> tools) {
		this.tools.clear();
		this.tools.addAll(tools);
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getSavetime() {
		return savetime;
	}
	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}
	public Level[] getLevels() {
		return levels;
	}
	public void setLevels(Level[] levels) {
		this.levels = levels;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	
}
