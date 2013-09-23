package com.nju.se.team.mota.game.unit;

import java.util.ArrayList;

import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.game.Level;
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
		Creature player = Creature.make("Íæ¼Ò", 0, 0, 1);
		setName(player.getName());
		setLV(1);
		setHP(1000);
		setATK(10);
		setDEF(10);
		setMoney(0);
		setEXP(0);
		setYellowkey(0);
		setBluekey(0);
		setRedkey(0);
		setPosition(player.getPosition());
		setFloor(player.getFloor());
		setSprites(player.getSprites());
		setAction(player.getAction());
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
	public void walkingLeft(){
		if(this.getPosition()[0]>0){
			this.getPosition()[0]--;
		}
	}
	public void walkingRight(){
		int MAX_X = LevelLoader.getLevel(this.getFloor()).getSize()[0]-1;
		if(this.getPosition()[0]<MAX_X){
			this.getPosition()[0]++;
		}
	}
	public void walkingUp(){
		if(this.getPosition()[1]>0){
			this.getPosition()[1]--;
		}
	}
	public void walkingDown(){
		int MAX_Y = LevelLoader.getLevel(this.getFloor()).getSize()[1]-1;
		if(this.getPosition()[1]<MAX_Y){
			this.getPosition()[1]++;
		}
	}
}
