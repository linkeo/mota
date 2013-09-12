package com.nju.se.team.mota.game;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
/**
 * ¥��
 * @author linkeo
 * @author lzw
 *
 */
public class Level {
	private int level;
	private int size[];
	private Set<Abiotic> abiotics;
	private Set<Creature> creatures;
	/**
	 * ���캯��<br>
	 * default:19x19
	 */
	public Level() {//default
		size=new int[]{19,19};
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
	 * ����һ��¥�����
	 * @param json
	 * @return Level
	 */
	public static Level make(JSONObject json){
		Level l = new Level();
		l.load(json);
		return l;
	}
	/**
	 * ��json��������
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
	/**
	 * ��ȡ¥��
	 * @return level(int)
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * ����¥��
	 * @param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * ��ȡ¥��ߴ�
	 * @return size(int[])
	 */
	public int[] getSize() {
		return size;
	}
	/**
	 * ����¥��ߴ�
	 * @param size(int[])
	 */
	public void setSize(int[] size) {
		this.size = size;
	}
	/**
	 * ��ȡ���з��������
	 * @return abiotics(Set)
	 */
	public Set<Abiotic> getAbiotics() {
		return abiotics;
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
	public void addUnit(Unit u){}
	public void removeUnit(Unit u){}
}
