package com.nju.se.team.mota.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.Condition;

public class GameRuntime {
	private static final GameRuntime inst = new GameRuntime();
	private static Rectangle view = new Rectangle(0,0,1,1);
	private GameRuntime(){
	}
	
	public static void setViewSize(int width, int height){
		view.setSize(width, height);
	}
	private static void adjustMapViewArea(Rectangle player) {
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Rectangle map = currLevel.rectangle();
		Rectangle lightArea = new Rectangle(player.x-2, player.y-2,
				player.width+4, player.height+4);
		if(map.contains(lightArea))//没到边
			if()
	}
	public static void paintMap(Graphics g, JComponent c){
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Player player = getCurrentPlayer();
//		int panelW = c.getSize().width;
//		int panelH = c.getSize().height;
//		int mapW = currLevel.getSize()[0]*32;
//		int mapH = currLevel.getSize()[1]*32;
//		int oX=0, oY=0;
//		int pdmW = panelW - mapW;
//		int pdmH = panelH - mapH;
//		oX = pdmW / 2;
//		oY = pdmH / 2;
////		if(pdmW>=0 && pdmH>=0){
////			oX = pdmW/2;
////			oY = pdmH/2;
////			pdmW = 0;
////			pdmH = 0;
////		}else if(pdmW<0 && pdmH>=0){
////			oX = 0;
////			oY = pdmH/2;
////			pdmH = 0;
////		}else if(pdmH<0 && pdmW>=0){
////			oX = pdmW/2;
////			oY = 0;
////			pdmW = 0;
////		}else{
////			oX = 0;
////			oY = 0;
////		}
		Abiotic floor = Abiotic.make("地面", 0, 0, currLevel.getLevel());
		BufferedImage floorImage = floor.currAnimation().currImage()[0][0];
		for(int i = view.x; i < view.width; i++)
			for(int j = view.y; j < view.height; j++)
				g.drawImage(floorImage, (i-view.x)*32, (j-view.y)*32, c);
		
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
	private static boolean checkNextStep(int x, int y){
		return checkNextStep(new Point(x, y));
	}
	private static boolean checkNextStep(Point p){
		boolean canMove = true;
		Level currLevel = GamingLevels.getCurrentLevelObject();
//		Player player = getCurrentPlayer();
		for(Creature c: currLevel.getCreatures()){
			if(c.rectangle().contains(p)){
				if(c.getAction().get(Condition.CRASH)!=null)
					System.out.println(c.getName()+">>"+c.getAction().get(Condition.CRASH));
				canMove = false;
			}
		}
		for(Abiotic a: currLevel.getAbiotics()){
			if(a.rectangle().contains(p)){
				if(a.getAction().get(Condition.CRASH)!=null)
					System.out.println(a.getName()+">>"+a.getAction().get(Condition.CRASH));
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
//		adjustMapViewArea(player.rectangle());
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
