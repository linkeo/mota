package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.data.LevelLoader;
import com.nju.se.team.mota.game.unit.Player;

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
		JButton menuButton;
		JButton continueButton;
		JButton saveButton;
		JButton	backButton;
		JButton exitButton;
		
	Player player;
	public GamePanel() {
		super(null);
		setSize(960, 640);
		player = new Player();
		loadLevel(1);
		leftPanel = new JPanel(null);
		rightPanel = new JPanel(null);
			levelLabel = new JLabel("µÚ   "+map.getCurrLevel().getLevel()+"   ¹Ø", JLabel.CENTER);
			playerImgLabel = new JLabel(new ImageIcon(ImageLoader.get("hero_1x0")));
			playerLabel = new JLabel("Íæ¼Ò:"+player.getName()+" (LV:"+player.getLV()+")");
			HPLabel = new JLabel("ÉúÃüÖµ:"+player.getHP());
			ATKLabel = new JLabel("¹¥»÷Á¦:"+player.getATK());
			DEFLabel = new JLabel("·ÀÓùÁ¦:"+player.getDEF());
			EXPLabel = new JLabel("¾­Ñé:"+player.getEXP());
			moneyLabel = new JLabel("½ðÇ®:"+player.getMoney());
			yellowKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x0")));
			yellowKeyLabel = new JLabel("»ÆÔ¿³×:"+player.getYellowkey());
			blueKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x1")));
			blueKeyLabel = new JLabel("À¶Ô¿³×:"+player.getBluekey());
			redKeyImgLabel = new JLabel(new ImageIcon(ImageLoader.get("item1_0x2")));
			redKeyLabel = new JLabel("ºìÔ¿³×:"+player.getRedkey());
			toolsLabel = new JLabel("ÎïÆ·À¸",JLabel.CENTER);
			toolsPanel = new ToolsPanel(player.getTools());
			menuButton = new JButton("²Ëµ¥");
			
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
	public void loadLevel(int i){
		Level l = LevelLoader.getLevel(i);
		if(map==null){
			map = new GameMapPanel(l,player);
		}else
			map.setCurrLevel(l);
	}
	public void setFont(){
		levelLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 24));
		levelLabel.setForeground(Color.LIGHT_GRAY);
		playerLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		playerLabel.setForeground(Color.LIGHT_GRAY);
		HPLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		HPLabel.setForeground(Color.LIGHT_GRAY);
		ATKLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		ATKLabel.setForeground(Color.LIGHT_GRAY);
		DEFLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		DEFLabel.setForeground(Color.LIGHT_GRAY);
		EXPLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		EXPLabel.setForeground(Color.LIGHT_GRAY);
		moneyLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		moneyLabel.setForeground(Color.LIGHT_GRAY);
		yellowKeyLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		yellowKeyLabel.setForeground(Color.LIGHT_GRAY);
		blueKeyLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		blueKeyLabel.setForeground(Color.LIGHT_GRAY);
		redKeyLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		redKeyLabel.setForeground(Color.LIGHT_GRAY);
		toolsLabel.setFont(new Font("Î¢ÈíÑÅºÚ", 1, 18));
		toolsLabel.setForeground(Color.LIGHT_GRAY);
	}
}
