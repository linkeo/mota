package com.nju.se.team.mota.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.UnitStatus;

public class GameMapPanel extends JPanel implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int row,col;
	private Level currLevel;
	private Player player;
	private UnitStatus currStatus;
	public GameMapPanel(Level l,Player p){
		super(null);
		setCurrLevel(l);
		row = l.getSize()[1];
		col = l.getSize()[0];
		this.player = p;
		currStatus = UnitStatus.NORMAL;
		addListener();
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
		g.drawImage(player.getSprites().get(currStatus).currImage()[0][0], oX+player.getPosition()[0]*32, oY+player.getPosition()[1]*32, this);
		player.getSprites().get(currStatus).update();
	}

	public Level getCurrLevel() {
		return currLevel;
	}
	public void setCurrLevel(Level currLevel) {
		this.currLevel = currLevel;
	}
	public void addListener(){
		GameMain.frame.addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			player.walkingLeft();
			currStatus = UnitStatus.WALKING_LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			player.walkingRight();
			currStatus = UnitStatus.WALKING_RIGHT;
			break;
		case KeyEvent.VK_UP:
			player.walkingUp();
			currStatus = UnitStatus.WALKING_UP;
			break;
		case KeyEvent.VK_DOWN:
			player.walkingDown();
			currStatus = UnitStatus.WALKING_DOWN;
			break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
