package com.nju.se.team.mota.game;

import java.awt.Color;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel leftPanel;
	JPanel rightPanel;
	
	public GamePanel() {
		super(null);
		setSize(960, 640);
		leftPanel = new JPanel(null);
		rightPanel = new JPanel(null);
		leftPanel.setBounds(0, 0, 640, 640);
		rightPanel.setBounds(640, 0, 320, 640);
		rightPanel.setBackground(Color.DARK_GRAY);
		add(leftPanel);
		add(rightPanel);
	}
}
