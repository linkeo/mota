package com.nju.se.team.mota.game.unit;

import java.util.ArrayList;
/**
 * Íæ¼Ò
 * @author linkeo
 * @author lzw
 *
 */

public class Player extends Creature{
	private int redkey, yellowkey, bluekey;
	private ArrayList<Tool> tools;
	private int LV;
	public Player() {
		setName("player");
		setLV(1);
		setHP(1000);
		setATK(10);
		setDEF(10);
		setMoney(0);
		setEXP(0);
		setYellowkey(0);
		setBluekey(0);
		setRedkey(0);
		tools = new ArrayList<Tool>();
	}
	public int getRedkey() {
		return redkey;
	}
	public void setRedkey(int redkey) {
		this.redkey = redkey;
	}
	public int getYellowkey() {
		return yellowkey;
	}
	public void setYellowkey(int yellowkey) {
		this.yellowkey = yellowkey;
	}
	public int getBluekey() {
		return bluekey;
	}
	public void setBluekey(int bluekey) {
		this.bluekey = bluekey;
	}
	public ArrayList<Tool> getTools() {
		return tools;
	}
	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
	public int getLV() {
		return LV;
	}
	public void setLV(int lV) {
		LV = lV;
	}
}
