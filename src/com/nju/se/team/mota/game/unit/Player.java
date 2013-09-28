package com.nju.se.team.mota.game.unit;

import java.util.ArrayList;
import java.util.HashSet;

import com.nju.se.team.mota.game.util.UnitStatus;
import com.nju.se.team.mota.script.MotaScript;
/**
 * Íæ¼Ò
 * @author linkeo
 * @author lzw
 *
 */

public class Player extends Creature{
	private HashSet<PlayerListener> listeners;
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
		if(this.redkey == redkey) return;
		this.redkey = redkey;
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.keyChanged(this);
	}
	public int getYellowkey() {
		return yellowkey;
	}
	public void setYellowkey(int yellowkey) {
		if(this.yellowkey == yellowkey) return;
		this.yellowkey = yellowkey;
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.keyChanged(this);
	}
	public int getBluekey() {
		return bluekey;
	}
	public void setBluekey(int bluekey) {
		if(this.bluekey == bluekey) return;
		this.bluekey = bluekey;
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.keyChanged(this);
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
		if(LV == lV) return;
		LV = lV;
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.lvChanged(this);
	}
	@Override
	public void setName(String name) {
		if(getName().equals(name)) return;
		super.setName(name);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.nameChanged(this);
	}
	@Override
	public void setATK(int aTK) {
		if(getATK()==aTK) return;
		super.setATK(aTK);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.atkChanged(this);
	}
	@Override
	public void setDEF(int dEF) {
		if(getDEF()==dEF) return;
		super.setDEF(dEF);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.defChanged(this);
	}
	@Override
	public void setHP(int hP) {
		if(getHP()==hP) return;
		super.setHP(hP);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.hpChanged(this);
	}
	@Override
	public void setMoney(int money) {
		if(getMoney()==money) return;
		super.setMoney(money);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.moneyChanged(this);
	}
	@Override
	public void setEXP(int eXP) {
		if(getEXP()==eXP) return;
		super.setEXP(eXP);
		if(listeners != null)
			for(PlayerListener l : listeners)
				l.expChanged(this);
		while(eXP>=getEXP(getLV())){
			levelup();
			System.out.println("LVUP:"+getLV());
		}
	}
	private void levelup() {
		setLV(getLV()+1);
		MotaScript.put("player", this);
		MotaScript.eval("levelup("+getLV()+")");
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
	
	
	public void addListener(PlayerListener l){
		if(listeners == null) listeners = new HashSet<PlayerListener>();
		listeners.add(l);
	}
	
	public void removeListener(PlayerListener l){
		if(listeners == null) return;
		listeners.remove(l);
		if(listeners.isEmpty())
			listeners = null;
	}
	public static int getEXP(int i) {
		Object result = MotaScript.eval("getEXP("+i+");");
		return (int)(double) result;
	}
	
}
