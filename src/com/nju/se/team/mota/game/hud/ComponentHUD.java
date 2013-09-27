package com.nju.se.team.mota.game.hud;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ComponentHUD extends HUD{
	private Component component;
	public ComponentHUD(Component comp) {
		this.component = comp;
	}
	@Override
	public void setHUDSize(int width, int height) {
		component.setSize(width, height);
	}

	@Override
	public void setHUDLocation(int x, int y) {
		component.setLocation(x, y);
	}

	@Override
	public Rectangle getHUDBounds() {
		return component.getBounds();
	}
	@Override
	public void paint(Graphics g) {
	}
	public Component getComponent() {
		return component;
	}
	
}
