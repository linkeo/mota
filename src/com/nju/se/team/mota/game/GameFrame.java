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
	public GameFrame() {
		setTitle("ħ��2013");
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
}