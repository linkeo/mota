package com.nju.se.team.mota.game;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
/**
 * 楼层
 * @author linkeo
 * @author lzw
 *
 */
public class Level {
	private int level;
	private int size[];
	private Set<Abiotic> abiotics;
	private Set<Creature> creatures;
	Rectangle rect;
	/**
	 * 构造函数<br>
	 * default:19x19
	 */
	public Level() {//default
		size=new int[]{18,18};
		rect = new Rectangle(getSize()[0], getSize()[1]);
		abiotics = new HashSet<Abiotic>();
		creatures = new HashSet<Creature>();
	}
	public Level clone(){
		Level l = new Level();
		l.setLevel(getLevel());
		l.setSize(getSize().clone());
		l.setAbiotics(new HashSet<Abiotic>(getAbiotics()));
		l.setCreatures(new HashSet<Creature>(getCreatures()));
		return l;
	}
	/**
	 * 创造一个楼层对象
	 * @param json
	 * @return Level
	 */
	public static Level make(JSONObject json){
		Level l = new Level();
		l.load(json);
		return l;
	}
	/**
	 * 从json加载数据
	 * @param json
	 */
	public void load(JSONObject json){
		setLevel(json.getInt("level"));
		JSONArray sizej = json.getJSONArray("size");
		int[] size = new int[sizej.length()];
		for(int i=0;i<size.length;++i)
			size[i]=sizej.getInt(i);
		setSize(size);
		JSONArray as = json.getJSONArray("abiotics");
		for(int i=0;i<as.length();++i)
			abiotics.add(Abiotic.make(as.getJSONObject(i)));
		JSONArray cs = json.getJSONArray("creatures");
		for(int i=0;i<cs.length();++i)
			creatures.add(Creature.make(cs.getJSONObject(i)));
		
	}
	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject(this);
		JSONArray as = new JSONArray();
		JSONArray cs = new JSONArray();
		for(Abiotic a : abiotics)
			as.put(a.parseEntityJSON());
		for(Creature c : creatures)
			cs.put(c.parseEntityJSON());
		json.put("abiotics", as);
		json.put("creatures", cs);
		return json;
	}
	/**
	 * 获取楼层
	 * @return level(int)
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * 设置楼层
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
		for(Unit u : units())
			u.setFloor(level);
	}
	/**
	 * 获取楼层尺寸
	 * @return size(int[])
	 */
	public int[] getSize() {
		return size;
	}
	/**
	 * 设置楼层尺寸
	 * @param size(int[])
	 */
	public void setSize(int[] size) {
		this.size = size;
	}
	/**
	 * 获取所有非生物对象
	 * @return abiotics(Set)
	 */
	public Set<Abiotic> getAbiotics() {
		return abiotics;
	}
	public Collection<Unit> units(){
		ArrayList<Unit> result = new ArrayList<Unit>();
		result.addAll(getAbiotics());
		result.addAll(getCreatures());
		return result;
	}
	public void setAbiotics(Set<Abiotic> abiotics) {
		this.abiotics = abiotics;
	}
	public void setCreatures(Set<Creature> creatures) {
		this.creatures = creatures;
	}
	
	public void addUnit(Abiotic a){
		abiotics.add(a);
	}
	public void removeUnit(Abiotic a){
		abiotics.remove(a);
	}
	public Set<Creature> getCreatures() {
		return creatures;
	}
	public void addUnit(Creature c){
		creatures.add(c);
	}
	public void removeUnit(Creature c){
		creatures.remove(c);
	}
	public void addUnit(Unit u){
		if(u instanceof Abiotic)
			addUnit((Abiotic)u);
		if(u instanceof Creature)
			addUnit((Creature)u);
	}
	public void removeUnit(Unit u){
		if(u instanceof Abiotic)
			removeUnit((Abiotic)u);
		if(u instanceof Creature)
			removeUnit((Creature)u);
	}
	public static Level newLevel(int i) {
		Level level = new Level();
		level.setLevel(i);
		return level;
	}
	public boolean allUnitNameNotEmpty(){
		for(Abiotic a : abiotics) if(a.getName().equals("")) return false;
		for(Creature c : creatures) if(c.getName().equals("")) return false;
		return true;
	}
	public Rectangle rectangle() {
		rect.setSize(getSize()[0], getSize()[1]);
		return rect;
	}
}
