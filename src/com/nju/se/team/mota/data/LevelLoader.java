package com.nju.se.team.mota.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.nju.se.team.mota.game.Level;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;

/**
 * 
 * 读取并维护所有游戏楼层<br>
 * 包括楼层地图和对象(生物和非生物)定义<br>
 * P.S.使用 <b>单件模式</b><br>
 * @author Linkeo
 *
 */
public class LevelLoader {
	private Map<Integer, Level> levels = new HashMap<Integer, Level>();
	private Map<String, Abiotic> abiotics = new HashMap<String, Abiotic>();
	private Map<String, Creature> creatures = new HashMap<String, Creature>();
	private LevelLoader() {
	}
	private static LevelLoader instance;

	private static void load(){
		if(instance==null)
			instance = new LevelLoader();
		instance.loadMe();
	}
	private void loadMe() {
		for(int i : DataLoader.getLevelFloors()){
			Level l = Level.make(DataLoader.getLevelDefine(i));
			levels.put(i, l);
			for(Abiotic a: l.getAbiotics())
				abiotics.put(a.getName(), a);
			for(Creature c: l.getCreatures())
				creatures.put(c.getName(), c);
		}
	}
	
	//MARK: about level
	
	public static Level getLevel(int floor){
		if(instance==null) load();
		return instance.levels.get(floor);
	}
	public static ArrayList<Integer> floors() {
		if(instance==null) load();
		ArrayList<Integer> floors = new ArrayList<Integer>(instance.levels.keySet());
		Collections.sort(floors);
		return floors;
	}
	public static void putLevel(Level l){
		if(instance==null) load();
		instance.levels.put(l.getLevel(), l);
	}
	public static void removeLevel(Level l){
		if(instance==null) load();
		instance.levels.remove(l.getLevel());
	}
	
	//MARK: about abiotic
	
	public static Abiotic getAbiotic(String name){
		if(instance==null) load();
		return instance.abiotics.get(name);
	}
	public static Collection<Abiotic> getAllAbiotics(){
		if(instance==null) load();
		return instance.abiotics.values();
	}
	public static Collection<Abiotic> getAbiotics(String type){
		if(instance==null) load();
		Collection<Abiotic> all =  instance.abiotics.values();
		ArrayList<Abiotic> result = new ArrayList<Abiotic>();
		if(type!=null&&!type.equalsIgnoreCase("none")&&!type.equalsIgnoreCase("null"))
			for(Abiotic a: all)
				if(a.getType().equals(type))
					result.add(a);
		Collections.sort(result);
		return result;
	}
	
	//MARK: about creature
	
	public static Creature getCreature(String name){
		if(instance==null) load();
		return instance.creatures.get(name);
	}
	public static Collection<Creature> getAllCreatures(){
		if(instance==null) load();
		return instance.creatures.values();
	}
	public static Collection<Creature> getCreatures(String type){
		if(instance==null) load();
		Collection<Creature> all =  instance.creatures.values();
		ArrayList<Creature> result = new ArrayList<Creature>();
		if(type!=null&&!type.equalsIgnoreCase("none")&&!type.equalsIgnoreCase("null"))
			for(Creature c: all)
				if(c.getType().equals(type))
					result.add(c);
		Collections.sort(result);
		return result;
	}
	
	//MARK: about unit
	
	public static Collection<Unit> getUnits(String type){
		ArrayList<Unit> result = new ArrayList<Unit>();
		result.addAll(getAbiotics(type));
		result.addAll(getCreatures(type));
		return result;
	}
	
}
