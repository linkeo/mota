package com.nju.se.team.mota.game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.TransparentButton;

public class GamePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel leftPanel;
		GameMapPanel map;
	JPanel rightPanel;
		JLabel levelLabel;
		JLabel playerImgLabel;
		JLabel playerLabel;
		JLabel HPImgLabel;
		JLabel HPLabel;
		JLabel ATKImgLabel;
		JLabel ATKLabel;
		JLabel DEFImgLabel;
		JLabel DEFLabel;
		JLabel EXPImgLabel;
		JLabel EXPLabel;
		JLabel moneyImgLabel;
		JLabel moneyLabel;
		JLabel yellowKeyImgLabel;
		JLabel yellowKeyLabel;
		JLabel blueKeyImgLabel;
		JLabel blueKeyLabel;
		JLabel redKeyImgLabel;
		JLabel redKeyLabel;
		JLabel toolsLabel;
		ToolsPanel toolsPanel;
		TransparentButton menuButton;
		JButton continueButton;
		JButton saveButton;
		JButton	backButton;
		JButton exitButton;
		
	Player player;
	public GamePanel() {
		super(null);
		player = GameRuntime.getCurrentPlayer();
		setSize(960, 640);
		leftPanel = new JPanel(null);
			map = new GameMapPanel();
		rightPanel = new JPanel(null);
			levelLabel = new JLabel("��   "+GamingLevels.getCurrentLevel()+"   ��", JLabel.CENTER);
			playerImgLabel = new JLabel(new ImageIcon(ImageLoader.get("hero_1x0")));
			playerLabel = new JLabel("���:"+player.getName()+" (LV:"+player.getLV()+")");
			HPLabel = new JLabel("����ֵ:"+player.getHP());
			ATKLabel = new JLabel("������:"+player.getATK());
			DEFLabel = new JLabel("������:"+player.getDEF());
			EXPLabel = new JLabel("����:"+player.getEXP());
			moneyLabel = new JLabel("��Ǯ:"+player.getMoney());
			yellowKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x0")));
			yellowKeyLabel = new JLabel("��Կ��:"+player.getYellowkey());
			blueKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x1")));
			blueKeyLabel = new JLabel("��Կ��:"+player.getBluekey());
			redKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x2")));
			redKeyLabel = new JLabel("��Կ��:"+player.getRedkey());
			toolsLabel = new JLabel("��Ʒ��",JLabel.CENTER);
			toolsPanel = new ToolsPanel(player.getTools());
			menuButton = new TransparentButton("�˵�", 0.5f, 0.75f, 1f);
			
		leftPanel.setBounds(0, 0, 640, 640);
			map.setBounds(0, 0, 640, 640);
		rightPanel.setBounds(640, 0, 320, 640);
			levelLabel.setBounds(0, 0, 320, 40);
			playerImgLabel.setBounds(0, 40, 40, 40);
			playerLabel.setBounds(40, 40, 280, 40);
			HPLabel.setBounds(0, 80, 320, 40);
			ATKLabel.setBounds(0, 120, 320, 40);
			DEFLabel.setBounds(0, 160, 320, 40);
			EXPLabel.setBounds(0, 200, 320, 40);
			moneyLabel.setBounds(0, 240, 320, 40);
			yellowKeyImgLabel.setBounds(0, 280, 40, 40);
			yellowKeyLabel.setBounds(40, 280, 280, 40);
			blueKeyImgLabel.setBounds(0, 320, 40, 40);
			blueKeyLabel.setBounds(40, 320, 280, 40);
			redKeyImgLabel.setBounds(0, 360, 40, 40);
			redKeyLabel.setBounds(40, 360, 280, 40);
			toolsLabel.setBounds(0, 400, 320, 40);
			toolsPanel.setLocation(0, 440);
			menuButton.setBounds(0, 520, 320, 80);
		rightPanel.setBackground(Color.DARK_GRAY);
		add(leftPanel);
			leftPanel.add(map);
		add(rightPanel);
			rightPanel.add(levelLabel);
			rightPanel.add(playerImgLabel);
			rightPanel.add(playerLabel);
			rightPanel.add(HPLabel);
			rightPanel.add(ATKLabel);
			rightPanel.add(DEFLabel);
			rightPanel.add(EXPLabel);
			rightPanel.add(moneyLabel);
			rightPanel.add(yellowKeyImgLabel);
			rightPanel.add(yellowKeyLabel);
			rightPanel.add(blueKeyImgLabel);
			rightPanel.add(blueKeyLabel);
			rightPanel.add(redKeyImgLabel);
			rightPanel.add(redKeyLabel);
			rightPanel.add(toolsLabel);
			rightPanel.add(toolsPanel);
			rightPanel.add(menuButton);
		setFont();
	}
	public void setFont(){
		levelLabel.setFont(Fonts.getYahei(24));
		menuButton.setFont(Fonts.getYahei(24));
		levelLabel.setForeground(Color.LIGHT_GRAY);
		playerLabel.setFont(Fonts.getYahei(18));
		playerLabel.setForeground(Color.LIGHT_GRAY);
		HPLabel.setFont(Fonts.getYahei(18));
		HPLabel.setForeground(Color.LIGHT_GRAY);
		ATKLabel.setFont(Fonts.getYahei(18));
		ATKLabel.setForeground(Color.LIGHT_GRAY);
		DEFLabel.setFont(Fonts.getYahei(18));
		DEFLabel.setForeground(Color.LIGHT_GRAY);
		EXPLabel.setFont(Fonts.getYahei(18));
		EXPLabel.setForeground(Color.LIGHT_GRAY);
		moneyLabel.setFont(Fonts.getYahei(18));
		moneyLabel.setForeground(Color.LIGHT_GRAY);
		yellowKeyLabel.setFont(Fonts.getYahei(18));
		yellowKeyLabel.setForeground(Color.LIGHT_GRAY);
		blueKeyLabel.setFont(Fonts.getYahei(18));
		blueKeyLabel.setForeground(Color.LIGHT_GRAY);
		redKeyLabel.setFont(Fonts.getYahei(18));
		redKeyLabel.setForeground(Color.LIGHT_GRAY);
		toolsLabel.setFont(Fonts.getYahei(18));
		toolsLabel.setForeground(Color.LIGHT_GRAY);
	}
}
