package com.nju.se.team.mota.game.hud;

import java.awt.Component;
import java.awt.Graphics;
import java.util.HashSet;

public class HUDManager {
	private HashSet<HUD> huds = new HashSet<HUD>();
	Component canvas;
	public HUDManager() {}
	public HUDManager(Component canvas) {
		this.canvas = canvas;
	}
	public void addHUD(HUD hud){
		huds.add(hud);
		hud.setCanvas(getCanvas());
	}
	public void removeHUD(HUD hud){
		huds.remove(hud);
		hud.setCanvas(null);
	}
	public void adjustAll(){
		for(HUD hud : huds)
			hud.setAdjusted(false);
		for(HUD hud : huds)
			hud.adjust();
	}
	public void paintAll(Graphics g){
		for(HUD hud : huds)
			hud.paint(g);
	}
	public Component getCanvas() {
		return canvas;
	}
	public void setCanvas(Component canvas) {
		this.canvas = canvas;
	}
}
