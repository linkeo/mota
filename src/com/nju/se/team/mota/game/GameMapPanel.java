package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.editor.MapDropEvent;
import com.nju.se.team.mota.editor.uielem.MapElem;
import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.UnitStatus;

public class GameMapPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row,col;
	private Level currLevel;
	public GameMapPanel(Level l){
		super(null);
		setCurrLevel(l);
		row = l.getSize()[1];
		col = l.getSize()[0];
	}

	@Override
	protected void paintComponent(Graphics g) {
		int mapW = currLevel.getSize()[0]*32;
		int mapH = currLevel.getSize()[1]*32;
		int panelW = getSize().width;
		int panelH = getSize().height;
		int oX=0, oY=0;
		oX = (panelW-mapW)/2;
		oY = (panelH-mapH)/2;
		System.out.println(oX+", "+oY);
		Abiotic floor = Abiotic.make("µÿ√Ê", 0, 0, currLevel.getLevel());
		BufferedImage floorImage = floor.getSprites().get(UnitStatus.NORMAL).currImage()[0][0];
		for(int i = 0; i < mapW; i+=32)
			for(int j = 0; j < mapH; j+=32)
				g.drawImage(floorImage, oX+i, oY+j, this);
		
		for(Unit u : currLevel.units()){
			int unitX = u.getPosition()[0];
			int unitY = u.getPosition()[1];
			int unitW = u.getSize()[0];
			int unitH = u.getSize()[1];
			BufferedImage[][] currImage = u.getSprites().get(UnitStatus.NORMAL).currImage();
			for(int i=0; i<unitW; ++i)
				for(int j=0; j<unitH; ++j)
					g.drawImage(currImage[i][j], oX+(unitX+i)*32, oY+(unitY+j)*32, this);
		}
	}

	public Level getCurrLevel() {
		return currLevel;
	}
	public void setCurrLevel(Level currLevel) {
		this.currLevel = currLevel;
	}
}
