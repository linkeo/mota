package com.nju.se.team.mota.game.util;
/**
 * Unit对象子类型
 * @author linkeo
 * @author lzw
 *
 */
public enum TypeEnum {
	ABIOTIC("静物"),CREATURE("生物");
	String str;
	private TypeEnum(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return str;
	}
}
