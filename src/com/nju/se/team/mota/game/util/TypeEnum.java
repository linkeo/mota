package com.nju.se.team.mota.game.util;
/**
 * Unit����������
 * @author linkeo
 * @author lzw
 *
 */
public enum TypeEnum {
	ABIOTIC("����"),CREATURE("����");
	String str;
	private TypeEnum(String str) {
		this.str = str;
	}
	@Override
	public String toString() {
		return str;
	}
}
