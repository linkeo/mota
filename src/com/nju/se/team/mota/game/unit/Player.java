package com.nju.se.team.mota.game.unit;

import java.util.ArrayList;

import com.nju.se.team.mota.game.util.UnitStatus;
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
	public void walkLeft(){
		turnLeft();
		this.getPosition()[0]--;
	}
	public void walkRight(){
		turnRight();
		this.getPosition()[0]++;
		
	}
	public void walkUp(){
		turnUp();
		this.getPosition()[1]--;
	}
	public void walkDown(){
		turnDown();
		this.getPosition()[1]++;
	}
	public void turnLeft() {
		if(getCurrStatus()!=UnitStatus.WALKING_LEFT)
			setCurrStatus(UnitStatus.WALKING_LEFT);
		else
			currAnimation().update();
	}
	public void turnRight(){
		if(getCurrStatus()!=UnitStatus.WALKING_RIGHT)
			setCurrStatus(UnitStatus.WALKING_RIGHT);
		else
			currAnimation().update();
		
	}
	public void turnUp(){
		if(getCurrStatus()!=UnitStatus.WALKING_UP)
			setCurrStatus(UnitStatus.WALKING_UP);
		else
			currAnimation().update();
	}
	public void turnDown(){
		if(getCurrStatus()!=UnitStatus.WALKING_DOWN)
			setCurrStatus(UnitStatus.WALKING_DOWN);
		else
			currAnimation().update();
	}
}
