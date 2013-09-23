package com.nju.se.team.mota.game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;

public class GameMapPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row,col;
	private Level currLevel;
	public GameMapPanel(){
		super(null);
		GameMain.frame.addKeyListener(GameRuntime.getKeyListener());
		GameMain.frame.requestFocus();
	}

	@Override
	protected void paintComponent(Graphics g) {
		if(currLevel==null){
			((Graphics2D)g).setRenderingHints(ImageHandler.getHQRenderingHints());
			g.setFont(Fonts.getYahei(72));
			g.drawString("MOTA 2013", 100, 100);
			return;
		}
		GameRuntime.paintMap(g, this);
	}

	public Level getCurrLevel() {
		return currLevel;
	}
	public void setCurrLevel(int level) {
		Level l = GamingLevels.getLevel(level);
		row = l.getSize()[1];
		col = l.getSize()[0];
		this.currLevel = l;
	}
}
