package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JComponent;
import javax.swing.Timer;

import com.nju.se.team.mota.game.unit.Abiotic;
import com.nju.se.team.mota.game.unit.Creature;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.Tool;
import com.nju.se.team.mota.game.unit.Unit;
import com.nju.se.team.mota.game.util.Condition;
import com.nju.se.team.mota.script.MotaScript;
public class GameRuntime{
	private static final ExecutorService exec = Executors.newSingleThreadExecutor();
	private static final ExecutorService loopexec = Executors.newSingleThreadExecutor();
	private static boolean looping = false;
	private Timer gameUpdateTimer;
	private static Rectangle view = new Rectangle(0,0,1,1);
	private Player currentPlayer;
	private boolean fighting = false;
	private static Runnable gameUpdate = new Runnable() {
		@Override
		public void run() {
			Level currLevel = GamingLevels.getCurrentLevelObject();
			if(currLevel == null) return;
			HashSet<Unit> toRemove = new HashSet<Unit>();
			for(Unit u : currLevel.units()){
				u.currAnimation().update();
				if(u.isDead()) toRemove.add(u);
			}
			for(Unit u : toRemove){
				currLevel.removeUnit(u);
				GamingLevels.removeUnit(u);
			}
			
		}
	};
	private static Runnable repaintLoop = new Runnable() {
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
	};
	private GameRuntime(){
		gameUpdateTimer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exec.execute(gameUpdate);
			}
		});
		loopexec.execute(repaintLoop);
		gameUpdateTimer.start();
	}
	public static void startPaintLoop(){
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
	public static void setViewSize(int width, int height){
		view.setSize(width, height);
	}
	private static void adjustMapViewArea(Rectangle player) {
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Rectangle map = currLevel.rectangle();
		Rectangle lightArea = new Rectangle(player.x-2, player.y-2,
				player.width+4, player.height+4);
		Rectangle intersection = map.intersection(lightArea);
		if(view.contains(intersection))
			return;//没到边
		else{
			if(intersection.x<view.x && intersection.y>=view.y)
				view.setLocation(view.x - 1, view.y);
			else if(intersection.x>=view.x && intersection.y<view.y)
				view.setLocation(view.x, view.y - 1);
			else if(intersection.x+intersection.width>view.x+view.width && intersection.y>=view.y)
				view.setLocation(view.x + 1, view.y);
			else if(intersection.x>=view.x && intersection.y+intersection.height>view.y+view.height)
				view.setLocation(view.x, view.y + 1);
		}
	}
	public static void paintMap(Graphics g, JComponent c){
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Player player = getCurrentPlayer();
		int panelW = c.getSize().width;
		int panelH = c.getSize().height;
		view.setSize(panelW/32, panelH/32);
		int oX=0, oY=0;
		Rectangle map = currLevel.rectangle();
		adjustMapViewArea(player.rectangle());
		Abiotic floor = Abiotic.make("地面", 0, 0, currLevel.getLevel());
		BufferedImage floorImage = floor.currAnimation().currImage()[0][0];
		if(map.contains(view)){
			for(int i = 0; i < view.width; i++)
				for(int j = 0; j < view.height; j++){
					g.drawImage(floorImage, oX+i*32, oX+j*32, c);
				}
		}else{
			Rectangle rect = map.intersection(view);
			oX = (view.width - rect.width)/2;
			oY = (view.height - rect.height)/2;
			for(int i = 0; i < rect.width; i++)
				for(int j = 0; j < rect.height; j++){
					g.drawImage(floorImage, (oX+i)*32, (oY+j)*32, c);
				}
		}


		for(Unit u : currLevel.units()){
			int unitX = u.getPosition()[0];
			int unitY = u.getPosition()[1];
			int unitW = u.getSize()[0];
			int unitH = u.getSize()[1];
			BufferedImage[][] currImage = u.currAnimation().currImage();
			for(int i=0; i<unitW; ++i)
				for(int j=0; j<unitH; ++j){
					if(u.rectangle().intersects(view))
						g.drawImage(currImage[i][j], (oX+unitX+i-view.x)*32, (oY+unitY+j-view.y)*32, c);
				}
		}
		g.drawImage(player.currAnimation().currImage()[0][0], (oX+player.getPosition()[0]-view.x)*32, (oY+player.getPosition()[1]-view.y)*32, c);
		paintMessages(g,c);
	}
	private static void paintMessages(Graphics g, JComponent c) {
		synchronized (inst) {
			int height = g.getFontMetrics().getHeight();
			for(int i=0; i<msgs.size(); ++i){
				String msg = msgs.get(i);
				Color temp = g.getColor();
				long time = System.currentTimeMillis() - msgstime.get(i);
				if(time<4000)
					g.setColor(Color.WHITE);
				else if(time<8000)
					g.setColor(new Color(255, 255, 255, (int) ((8000-time)/4000.0*255)));
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
				if(time<4000)
					g.setColor(Color.BLACK);
				else if(time<8000)
					g.setColor(new Color(0, 0, 0, (int) ((8000-time)/4000.0*255)));
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
		for(Creature c: new HashSet<Creature>(currLevel.getCreatures())){
			if(c.rectangle().contains(p)){
				if(c.getAction().get(Condition.CRASH)!=null){
					inst.fighting = true;
					MotaScript.put("player", player);
					MotaScript.put("enemy", c);
					MotaScript.put("source", c);
					MotaScript.eval(c.getAction().get(Condition.CRASH));
				}
				canMove = false;
			}
		}
		for(Abiotic a: new HashSet<Abiotic>(currLevel.getAbiotics())){
			if(a.rectangle().contains(p)){
				if(a.getAction().get(Condition.CRASH)!=null){
					MotaScript.put("player", player);
					MotaScript.put("source", a);
					MotaScript.put("level", currLevel);
					MotaScript.eval(a.getAction().get(Condition.CRASH));
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
		if(inst.currentPlayer != null && GameMain.frame.gamePanel!=null && 
				GameMain.frame.getContentPane().isAncestorOf(GameMain.frame.gamePanel))
			inst.currentPlayer.removeListener(GameMain.frame.gamePanel);
		
		inst.currentPlayer = currentPlayer;
		
		if(GameMain.frame.gamePanel!=null && 
				GameMain.frame.getContentPane().isAncestorOf(GameMain.frame.gamePanel))
			inst.currentPlayer.addListener(GameMain.frame.gamePanel);
	}
	
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
		public void keyTyped(KeyEvent e) {
			if(inst.fighting){
				if(e.getKeyChar() == 'p'){
					MotaScript.eval("playerSurrender()");
				}
			}
		}
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
	private static final GameRuntime inst = new GameRuntime();
	public static void use(Tool tool) {
		Level currLevel = GamingLevels.getCurrentLevelObject();
		Player player = getCurrentPlayer();
		MotaScript.put("player", player);
		MotaScript.put("source", tool);
		MotaScript.put("level", currLevel);
		MotaScript.eval(tool.getAction().get(Condition.USE));
		if(tool.isDead())
			player.removeTool(tool);
	}
}
