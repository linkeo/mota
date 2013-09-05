package com.nju.se.team.mota.game;

public class Save {
	private String name;
	private int lv;
	private int hp;
	private int atk;
	private int def;
	private int money;
	private int[] keys;
	private int[] tools;
	private int floor;
	private String savetime;
	private Level[] levels;
	public Save() {
		// TODO Auto-generated constructor stub
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
	public int[] getTools() {
		return tools;
	}
	public void setTools(int[] tools) {
		this.tools = tools;
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
	
}
