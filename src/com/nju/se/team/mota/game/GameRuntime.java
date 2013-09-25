package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JComponent;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.script.MotaScript;

public class GameRuntime implements Runnable{
	private static final ExecutorService exec = Executors.newSingleThreadExecutor();
	private static final ExecutorService loopexec = Executors.newSingleThreadExecutor();
	private static boolean looping = false;
	private GameRuntime(){
		loopexec.execute(this);
	}
	public static void startPaintLoop(){
		System.out.println("START LOOP TIMER");
		looping = true;
	}
	public static void stopPaintLoop(){
		looping = false;
	}
	public static void exitGame(){
		stopPaintLoop();
		GameMain.frame.dispose();
		System.exit(0);
	}
	private static ArrayList<String> msgs = new ArrayList<String>();
	private static ArrayList<Long> msgstime = new ArrayList<Long>();
	public static void execute(Runnable r){
		exec.execute(r);
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
		paintMessages(g,c);
	}
	private static void paintMessages(Graphics g, JComponent c) {
		synchronized (inst) {
			int height = g.getFontMetrics().getHeight();
			for(int i=0; i<msgs.size(); ++i){
				String msg = msgs.get(i);
				Color temp = g.getColor();
				long time = System.currentTimeMillis() - msgstime.get(i);
				if(time<5000)
					g.setColor(Color.WHITE);
				else if(time<7000)
					g.setColor(new Color(255, 255, 255, (int) ((7000-time)/2000.0*255)));
				else
					continue;
				g.drawString(msg, 10+1, (16-i)*height+1);
				g.drawString(msg, 10+1, (16-i)*height);
				g.drawString(msg, 10+1, (16-i)*height-1);
				g.drawString(msg, 10-1, (16-i)*height+1);
				g.drawString(msg, 10-1, (16-i)*height);
				g.drawString(msg, 10-1, (16-i)*height-1);
				g.drawString(msg, 10, (16-i)*height+1);
				g.drawString(msg, 10, (16-i)*height-1);
				if(time<5000)
					g.setColor(Color.BLACK);
				else if(time<7000)
					g.setColor(new Color(0, 0, 0, (int) ((7000-time)/2000.0*255)));
				g.drawString(msg, 10, (16-i)*height);
				g.setColor(temp);
			}
		}
	}
	public static void println(String message){
		synchronized (inst) {
			while(msgs.size()>=16){
				msgstime.remove(msgs.size()-1);
				msgs.remove(msgs.size()-1);
			}
			msgs.add(0, message);
			msgstime.add(0, System.currentTimeMillis());
			GameMain.frame.repaint();
		}
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
	
	private static boolean keyMask = false;
	public static void keyHandle(final KeyEvent e) {
		if(keyMask) return;
		keyMask = true;
		execute(new Runnable() {
			@Override
			public void run() {
				Player player = getCurrentPlayer();
				int x = player.getPosition()[0], y = player.getPosition()[1];
				switch(e.getKeyCode()){
				case KeyEvent.VK_LEFT:
					player.turnLeft();
					if(checkNextStep(x-1, y))
						player.walkLeft();
					break;
				case KeyEvent.VK_RIGHT:
					player.turnRight();
					if(checkNextStep(x+1, y))
						player.walkRight();
					break;
				case KeyEvent.VK_UP:
					player.turnUp();
					if(checkNextStep(x, y-1))
						player.walkUp();
					break;
				case KeyEvent.VK_DOWN:
					player.turnDown();
					if(checkNextStep(x, y+1))
						player.walkDown();
					break;
				}
				GameMain.frame.repaint();
				keyMask = false;
			}
		});
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
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				if(looping)
					GameMain.frame.repaint();
			}
		}
	}
	private static final GameRuntime inst = new GameRuntime();
}
