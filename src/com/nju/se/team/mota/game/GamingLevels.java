package com.nju.se.team.mota.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.nju.se.team.mota.data.DataLoader;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;

public class GamingLevels {
	private Set<Integer> visitedLevels = new HashSet<Integer>();
	private Map<Integer, Level> levels = new HashMap<Integer, Level>();
	private Map<String, Abiotic> abiotics = new HashMap<String, Abiotic>();
	private Map<String, Creature> creatures = new HashMap<String, Creature>();
	private int currentLevel;
	
	private GamingLevels() {
		loadLevels();
	}
	private static final GamingLevels instance = new GamingLevels();

	public void toLevel(int floor){
		visitedLevels.add(floor);
		currentLevel = floor;
	}
	
	
	private void loadLevels() {
		levels.clear();
		abiotics.clear();
		creatures.clear();
		visitedLevels.clear();
		for(int i : DataLoader.getLevelFloors()){
			Level l = Level.make(DataLoader.getLevelDefine(i));
			levels.put(i, l);
			for(Abiotic a: l.getAbiotics())
				abiotics.put(a.getName(), a);
			for(Creature c: l.getCreatures())
				creatures.put(c.getName(), c);
		}
		currentLevel = 1;
	}
	private void loadSaveOnly(Save save){
		for(Level i : save.getLevels()){
			for(Abiotic a: getLevel(i.getLevel()).getAbiotics())
				abiotics.remove(a);
			for(Creature c: getLevel(i.getLevel()).getCreatures())
				creatures.put(c.getName(), c);
			
			levels.put(i.getLevel(), i);
			visitedLevels.add(i.getLevel());
			for(Abiotic a: i.getAbiotics())
				abiotics.put(a.getName(), a);
			for(Creature c: i.getCreatures())
				creatures.put(c.getName(), c);
		}
		currentLevel = save.getFloor();
	}
	
	public static void loadSave(Save save){
		instance.loadLevels();
		instance.loadSaveOnly(save);
	}
	
	//MARK: about level
	
	public static Level getLevel(int floor){
		return instance.levels.get(floor).clone();
	}
	public static ArrayList<Integer> floors() {
		ArrayList<Integer> floors = new ArrayList<Integer>(instance.levels.keySet());
		Collections.sort(floors);
		return floors;
	}
	public static void putLevel(Level l){
		instance.levels.put(l.getLevel(), l);
		
	}
	public static void removeLevel(int i){
		instance.levels.remove(i);
	}
	//MARK: about abiotic
	
	public static Abiotic getAbiotic(String name){
		return instance.abiotics.get(name);
	}
	public static Collection<Abiotic> getAllAbiotics(){
		return instance.abiotics.values();
	}
	public static Collection<Abiotic> getAbiotics(String type){
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
		return instance.creatures.get(name);
	}
	public static Collection<Creature> getAllCreatures(){
		return instance.creatures.values();
	}
	public static Collection<Unit> getAllUnits(){
		ArrayList<Unit> result = new ArrayList<Unit>();
		result.addAll(getAllAbiotics());
		result.addAll(getAllCreatures());
		return result;
	}
	public static Collection<Creature> getCreatures(String type){
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

	public static Collection<Unit> searchUnits(String regex){
		ArrayList<Unit> all = new ArrayList<Unit>();
		all.addAll(getAllAbiotics());
		all.addAll(getAllCreatures());
		Set<Unit> r = new HashSet<Unit>();
		for(Unit u : all)
			if(u.getName().matches(regex))
				r.add(u);
		return r;
	}


	public static int getCurrentLevel() {
		return instance.currentLevel;
	}
	public static Level getCurrentLevelObject(){
		return getLevel(getCurrentLevel());
	}
}
