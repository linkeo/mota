package com.nju.se.team.mota.game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Unit;

public class GameRuntime {
	private static final GameRuntime inst = new GameRuntime();
	private GameRuntime(){
	}
	
	public static void paintMap(Graphics g, JComponent c){
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Player player = getCurrentPlayer();
		int panelW = c.getSize().width;
		int panelH = c.getSize().height;
		int mapW = currLevel.getSize()[0]*32;
		int mapH = currLevel.getSize()[1]*32;
		int oX=0, oY=0;
		oX = (panelW-mapW)/2;
		oY = (panelH-mapH)/2;
		Abiotic floor = Abiotic.make("µÿ√Ê", 0, 0, currLevel.getLevel());
		BufferedImage floorImage = floor.currAnimation().currImage()[0][0];
		for(int i = 0; i < mapW; i+=32)
			for(int j = 0; j < mapH; j+=32)
				g.drawImage(floorImage, oX+i, oY+j, c);
		
		for(Unit u : currLevel.units()){
			int unitX = u.getPosition()[0];
			int unitY = u.getPosition()[1];
			int unitW = u.getSize()[0];
			int unitH = u.getSize()[1];
			BufferedImage[][] currImage = u.currAnimation().currImage();
			for(int i=0; i<unitW; ++i)
				for(int j=0; j<unitH; ++j)
					g.drawImage(currImage[i][j], oX+(unitX+i)*32, oY+(unitY+j)*32, c);
		}
		g.drawImage(player.currAnimation().currImage()[0][0], oX+player.getPosition()[0]*32, oY+player.getPosition()[1]*32, c);
	}
	
	
	
	
	
	public static Player getCurrentPlayer() {
		return inst.currentPlayer;
	}

	public static void setCurrentPlayer(Player currentPlayer) {
		inst.currentPlayer = currentPlayer;
	}

	private Player currentPlayer;
	
	public static void keyHandle(KeyEvent e) {
		Player player = getCurrentPlayer();
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			player.walkingLeft();
			break;
		case KeyEvent.VK_RIGHT:
			player.walkingRight();
			break;
		case KeyEvent.VK_UP:
			player.walkingUp();
			break;
		case KeyEvent.VK_DOWN:
			player.walkingDown();
			break;
		}
		GameMain.frame.repaint();
	}
	private KeyListener keyListener = new KeyListener() {
		@Override
		public void keyTyped(KeyEvent e) {}
		@Override
		public void keyReleased(KeyEvent e) {}
		@Override
		public void keyPressed(KeyEvent e) {
			keyHandle(e);
		}
	};
	public static KeyListener getKeyListener() {
		return inst.keyListener;
	}
}
