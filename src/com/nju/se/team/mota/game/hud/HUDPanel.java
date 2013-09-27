package com.nju.se.team.mota.game.hud;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class HUDPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HUDManager hudManager = new HUDManager();
	HUD rootHUD = new HUD() {
		@Override
		public void setHUDSize(int width, int height) {
		}
		
		@Override
		public void setHUDLocation(int x, int y) {
		}
		
		@Override
		public void paint(Graphics g) {
		}
		
		@Override
		public Rectangle getHUDBounds() {
			return new Rectangle(getSize());
		}
	};
	public HUDPanel() {
		super.setLayout(null);
		super.setOpaque(false);
		hudManager.addHUD(rootHUD);
	}
	
	public void addHUD(ComponentHUD hud){
		add(hud.getComponent());
		addHUD(hud);
	}
	public void addHUD(HUD hud) {
		hudManager.addHUD(hud);
	}

	public void removeHUD(ComponentHUD hud) {
		remove(hud.getComponent());
		removeHUD(hud);
	}
	public void removeHUD(HUD hud) {
		hudManager.removeHUD(hud);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		hudManager.paintAll(g);
	}
	public HUD getRootHUD(){
		return rootHUD;
	}

	public void adjustHUD() {
		hudManager.adjustAll();
	}
}
