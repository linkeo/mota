package com.nju.se.team.mota.game;

import java.awt.Color;

import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.TransparentButton;

public class LevelSelectElem extends TransparentButton{

	/**
	 * 
	 */
	private final int floor;
	private static final long serialVersionUID = 1L;
	public LevelSelectElem(int floor) {
		super(""+floor,0.5f, 0.75f, 1f);
		this.floor = floor;
		setBackground(Color.DARK_GRAY);
		setFont(Fonts.getYahei(18));
		setForeground(Color.BLACK);
		setSize(30,30);
	}
	public int getFloor() {
		return floor;
	}
}
