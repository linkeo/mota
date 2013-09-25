package com.nju.se.team.mota.game;

import com.nju.se.team.mota.util.MotaUtils;

public class GameMain {
	static GameFrame frame;
	public static void main(String[] args) {
		MotaUtils.setLookandFeel();
		frame = new GameFrame();
		GameRuntime.startPaintLoop();
	}
}
