package com.nju.se.team.mota.game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 960, FRAME_HEIGHT = 640;
	LogoPanel logoPanel;
	GamePanel gamePanel;
	SavePanel savePanel;
	NewSavePanel nameInput;
	public GameFrame() {
		setTitle("Ä§Ëþ2013");
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel cp = (JPanel) getContentPane();
		cp.setLayout(null);
		
		logoPanel = new LogoPanel();
		cp.add(logoPanel);
		logoPanel.setLocation(0, 0);
		
		setVisible(true);
		setSize(FRAME_WIDTH*2-cp.getWidth(), FRAME_HEIGHT*2-cp.getHeight());
		setLocationRelativeTo(null);
	}
	public void newPlayer() {
//		player = new Player();
//		loadLevel(1);
		nameInput = new NewSavePanel();
		nameInput.setLocation(0, 0);
		JPanel cp = (JPanel) getContentPane();
		cp.removeAll();
		cp.add(nameInput);
//		cp.add(gamePanel);
//		gamePanel = new GamePanel();
//		gamePanel.setLocation(0, 0);
		repaint();
	}
	public void startNewGame(String player){
		Save save = GamingSave.createNewSave(player);
		GamingLevels.loadSave(save);
		GameRuntime.setCurrentPlayer(save.player());
		gamePanel = new GamePanel();
		gamePanel.setLocation(0, 0);
		gamePanel.map.setCurrLevel(GamingLevels.getCurrentLevel());
		JPanel cp = (JPanel) getContentPane();
		cp.removeAll();
		cp.add(gamePanel);
		repaint();
	}
	public void selectSave() {
		savePanel = new SavePanel();
		savePanel.setLocation(0, 0);
		JPanel cp = (JPanel) getContentPane();
		cp.removeAll();
		cp.add(savePanel);
		repaint();
	}
	public void jumpToMainMenu() {
		JPanel cp = (JPanel) getContentPane();
		cp.removeAll();
		cp.add(logoPanel);
		repaint();
	}
}
