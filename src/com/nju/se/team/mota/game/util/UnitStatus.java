package com.nju.se.team.mota.game.util;
/**
 * Unit对象的状态
 * @author linkeo
 * @author lzw
 *
 */
public enum UnitStatus {
	NORMAL("normal"),
	WALKING_UP("walking_up"),
	WALKING_DOWN("walking_down"),
	WALKING_LEFT("walking_left"),
	WALKING_RIGHT("walking_right"),
	DYING("dying"),
	UNDEFINED("undefined")
	;
	private String str;
	private UnitStatus(String str) {
		this.str = str;
	}
	/**
	 * 加载状态
	 * @param str
	 * @return Condition
	 */
	public static UnitStatus load(String str){
		for(UnitStatus s : UnitStatus.values())
			if(s.toString().equalsIgnoreCase(str))
				return s;
		return UNDEFINED;
	}
	@Override
	public String toString() {
		return str;
	}
}
