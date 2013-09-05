package com.nju.se.team.mota.game.unit;

import java.util.ArrayList;

import com.nju.se.team.mota.game.util.Tool;

public class Player {
	private int redkey, yellowkey, bluekey;
	private ArrayList<Tool> tools;
	private int LV;
	public Player() {
		// TODO Auto-generated constructor stub
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
