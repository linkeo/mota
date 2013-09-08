package com.nju.se.team.mota.game.util;

public enum UnitStatus {
	NORMAL("normal"),
	WALKING_UP("walking_up"),
	WALKING_DOWN("walking_down"),
	WALKING_LEFT("walking_left"),
	WALKING_RIGHT("walking_right");
	private String str;
	private UnitStatus(String str) {
		this.str = str;
	}
	public static UnitStatus load(String str){
		switch(str){
		case "normal":return NORMAL;
		case "walking_up":return WALKING_UP;
		case "walking_down":return WALKING_DOWN;
		case "walking_left":return WALKING_LEFT;
		case "walking_right":return WALKING_RIGHT;
		default : return null;
		}
	}
	@Override
	public String toString() {
		return str;
	}
}
