package com.nju.se.team.mota.game.util;
/**
 * 条件
 * @author linkeo
 * @author lzw
 *
 */
public enum Condition {
	CRASH("crash"),
	DEAD("dead"),
	USE("use"),
	CLOSETO("closeto"),
	UNDEFINED("undefined");
	String str;
	private Condition(String str){
		this.str = str;
	}
	/**
	 * 加载条件
	 * @param str
	 * @return Condition
	 */
	public static Condition load(String str){
		for(Condition c : Condition.values())
			if(c.toString().equalsIgnoreCase(str))
				return c;
		return UNDEFINED;
	}
	@Override
	public String toString() {
		return str;
	}
}
