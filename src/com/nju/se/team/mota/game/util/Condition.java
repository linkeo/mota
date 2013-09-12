package com.nju.se.team.mota.game.util;
/**
 * ����
 * @author linkeo
 * @author lzw
 *
 */
public enum Condition {
	CRASH("crash"),
	DEAD("dead"),
	CLOSETO("closeto");
	String str;
	private Condition(String str){
		this.str = str;
	}
	/**
	 * ��������
	 * @param str
	 * @return Condition
	 */
	public static Condition load(String str){
		switch(str){
		case "crash":return CRASH;
		case "dead":return DEAD;
		case "closeto":return CLOSETO;
		default: return null;
		}
	}
	@Override
	public String toString() {
		return str;
	}
}
