package com.nju.se.team.mota.game;

import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;

public class Level {
	private int level;
	private int size[];
	private Set<Abiotic> abiotics;
	private Set<Creature> creatures; 
	public Level() {//default
		size=new int[]{19,19};
		abiotics = new HashSet<Abiotic>();
		creatures = new HashSet<Creature>();
	}
	public static Level make(JSONObject json){
		Level l = new Level();
		l.load(json);
		return l;
	}
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int[] getSize() {
		return size;
	}
	public void setSize(int[] size) {
		this.size = size;
	}
	public Set<Abiotic> getAbiotics() {
		return abiotics;
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
