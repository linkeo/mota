package com.nju.se.team.mota.editor;

import java.util.EventObject;

import com.nju.se.team.mota.game.unit.Unit;

public class MapDropEvent extends EventObject {
	int x, y;
	public MapDropEvent(Unit sourse, int x, int y) {
		super(sourse);
		this.x = x;
		this.y = y;
	}
	public Unit getSource(){
		return (Unit) source;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
