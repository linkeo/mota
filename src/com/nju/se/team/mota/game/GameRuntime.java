package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JComponent;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.script.MotaScript;

public class GameRuntime {
	private static final GameRuntime inst = new GameRuntime();
	private GameRuntime(){
	}
	private static ArrayList<String> msgs = new ArrayList<String>();
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
		paintMessages(g,c);
	}
	private static void paintMessages(Graphics g, JComponent c) {
		int height = g.getFontMetrics().getHeight();
		for(int i=0; i<16; ++i){
			if(i<msgs.size()){
				Color temp = g.getColor();
				g.setColor(Color.WHITE);
				g.drawString(msgs.get(i), 10+1, (16-i)*height+1);
				g.drawString(msgs.get(i), 10+1, (16-i)*height);
				g.drawString(msgs.get(i), 10+1, (16-i)*height-1);
				g.drawString(msgs.get(i), 10-1, (16-i)*height+1);
				g.drawString(msgs.get(i), 10-1, (16-i)*height);
				g.drawString(msgs.get(i), 10-1, (16-i)*height-1);
				g.drawString(msgs.get(i), 10, (16-i)*height+1);
				g.drawString(msgs.get(i), 10, (16-i)*height-1);
				g.setColor(Color.BLACK);
				g.drawString(msgs.get(i), 10, (16-i)*height);
				g.setColor(temp);
			}
		}
	}
	public static void println(String message){
		while(msgs.size()>=16)
			msgs.remove(msgs.size()-1);
		msgs.add(0, message);
		GameMain.frame.repaint();
	}
	private static boolean checkNextStep(int x, int y){
		return checkNextStep(new Point(x, y));
	}
	private static boolean checkNextStep(Point p){
		boolean canMove = true;
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Player player = getCurrentPlayer();
		for(Creature c: currLevel.getCreatures()){
			if(c.rectangle().contains(p)){
				if(c.getAction().get(Condition.CRASH)!=null){
					MotaScript.put("player", player);
					MotaScript.put("enemy", c);
					MotaScript.put("source", c);
					MotaScript.call(c.getAction().get(Condition.CRASH));
//					System.out.println(c.getName()+">>"+c.getAction().get(Condition.CRASH));
				}
				canMove = false;
			}
		}
		for(Abiotic a: currLevel.getAbiotics()){
			if(a.rectangle().contains(p)){
				if(a.getAction().get(Condition.CRASH)!=null){
					MotaScript.put("player", player);
					MotaScript.put("source", a);
					MotaScript.call(a.getAction().get(Condition.CRASH));
//					System.out.println(a.getName()+">>"+a.getAction().get(Condition.CRASH));
				}
				if(!a.isCanGoThrough())
					canMove = false;
			}
		}
		if(!currLevel.rectangle().contains(p))
			canMove = false;
		return canMove;
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
		int x = player.getPosition()[0], y = player.getPosition()[1];
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			if(checkNextStep(x-1, y))
				player.walkLeft();
			else
				player.turnLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if(checkNextStep(x+1, y))
				player.walkRight();
			else
				player.turnRight();
			break;
		case KeyEvent.VK_UP:
			if(checkNextStep(x, y-1))
				player.walkUp();
			else
				player.turnUp();
			break;
		case KeyEvent.VK_DOWN:
			if(checkNextStep(x, y+1))
				player.walkDown();
			else
				player.turnDown();
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
