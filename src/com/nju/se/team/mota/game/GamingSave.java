package com.nju.se.team.mota.game;

public class GamingSave {
	private static GamingSave inst = new GamingSave();
	private GamingSave(){}
	
	private Save currSave;
	public static void setSave(Save save){
		inst.currSave = save;
	}
	public static Save currentSave(){
		return inst.currSave;
	}
	public static Save createNewSave(String player) {
		inst.currSave = Save.newSave(player);
		return inst.currSave;
	}
}
