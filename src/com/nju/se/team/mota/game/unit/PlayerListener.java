package com.nju.se.team.mota.game.unit;

public interface PlayerListener {

//	setName(player.getName());
//	setLV(1);
//	setHP(1000);
//	setATK(10);
//	setDEF(10);
//	setMoney(0);
//	setEXP(0);
//	setYellowkey(0);
//	setBluekey(0);
//	setRedkey(0);
	public void nameChanged(Player p);
	public void lvChanged(Player p);
	public void hpChanged(Player p);
	public void atkChanged(Player p);
	public void defChanged(Player p);
	public void moneyChanged(Player p);
	public void expChanged(Player p);
	public void keyChanged(Player p);
	public void toolChanged(Player p);
}
