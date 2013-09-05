package com.nju.se.team.mota.game;

import java.util.Set;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;

public class Level {
	private int level;
	private int size[];
	private Set<Abiotic> abiotics;
	private Set<Creature> creatures; 
	public Level() {
		// TODO Auto-generated constructor stub
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
	public void setAbiotics(Set<Abiotic> abiotics) {
		this.abiotics = abiotics;
	}
	public Set<Creature> getCreatures() {
		return creatures;
	}
	public void setCreatures(Set<Creature> creatures) {
		this.creatures = creatures;
	}
}
