package com.nju.se.team.mota.game.util;

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
