package com.nju.se.team.mota.game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ImageLoader;
import com.nju.se.team.mota.game.uielem.ToolElem;
import com.nju.se.team.mota.game.unit.Player;
import com.nju.se.team.mota.game.unit.PlayerListener;
import com.nju.se.team.mota.game.unit.Tool;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.TransparentButton;
import com.nju.se.team.mota.util.TransparentListPanel;
import com.nju.se.team.mota.util.TransparentPanel;

public class GamePanel extends JPanel implements PlayerListener{
	
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
		ToolListPanel toolsPanel;
		TransparentButton menuButton;
		TransparentPanel menuMaskPanel;
		TransparentListPanel menuPanel;
		TransparentButton continueButton;
		TransparentButton saveButton;
		TransparentButton loadButton;
		TransparentButton backButton;
		TransparentButton exitButton;
		
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
			toolsPanel = new ToolListPanel();
			for(Tool tool: player.getTools())
				toolsPanel.add(new ToolElem(tool));
			menuButton = new TransparentButton("�˵�", 0.5f, 0.75f, 1f);
			menuMaskPanel = new TransparentPanel(null);
			menuPanel = new TransparentListPanel();
			continueButton = new TransparentButton("������Ϸ", 0.5f, 0.75f, 1f);
			saveButton = new TransparentButton("����", 0.5f, 0.75f, 1f);
			loadButton = new TransparentButton("��ȡ�浵", 0.5f, 0.75f, 1f);
			backButton = new TransparentButton("�������˵�", 0.5f, 0.75f, 1f);
			exitButton = new TransparentButton("�˳���Ϸ", 0.5f, 0.75f, 1f);
			
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
		menuMaskPanel.setBounds(0, 0, 960, 640);
		menuPanel.setBounds((960-320)/2, (640-320)/2, 320, 320);
		menuMaskPanel.addMouseListener(new MouseAdapter() {
		});
		menuMaskPanel.setTransparency(0.8f);
		menuPanel.setTransparency(0f);
		continueButton.setSize(300, 52);
		saveButton.setSize(300, 52);
		loadButton.setSize(300, 52);
		backButton.setSize(300, 52);
		exitButton.setSize(300, 52);
		
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
		JPanel glasspane = (JPanel) GameMain.frame.getGlassPane();
		glasspane.setLayout(null);
		glasspane.add(menuMaskPanel);
			menuMaskPanel.add(menuPanel);
				menuPanel.add(continueButton);
				menuPanel.add(saveButton);
				menuPanel.add(loadButton);
				menuPanel.add(backButton);
				menuPanel.add(exitButton);
		setFont();
		addListener();
	}
	private void addListener() {
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameMain.frame.getGlassPane().setVisible(true);
			}
		});
		continueButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameMain.frame.getGlassPane().setVisible(false);
			}
		});
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameMain.frame.getGlassPane().setVisible(false);
				GameMain.frame.jumpToMainMenu();
			}
		});
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameRuntime.exitGame();
			}
		});
		if(GameRuntime.getCurrentPlayer()!=null)
			GameRuntime.getCurrentPlayer().addListener(this);
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
		continueButton.setFont(Fonts.getYahei(24));
		saveButton.setFont(Fonts.getYahei(24));
		loadButton.setFont(Fonts.getYahei(24));
		backButton.setFont(Fonts.getYahei(24));
		exitButton.setFont(Fonts.getYahei(24));
		continueButton.setBackground(Color.WHITE);
		saveButton.setBackground(Color.WHITE);
		loadButton.setBackground(Color.WHITE);
		backButton.setBackground(Color.WHITE);
		exitButton.setBackground(Color.WHITE);
		rightPanel.setBackground(Color.DARK_GRAY);
		menuMaskPanel.setBackground(Color.BLACK);
	}
	@Override
	public void nameChanged(Player p) {
		playerLabel.setText("���:"+player.getName()+" (LV:"+player.getLV()+")");
	}
	@Override
	public void lvChanged(Player p) {
		playerLabel.setText("���:"+player.getName()+" (LV:"+player.getLV()+")");
	}
	@Override
	public void hpChanged(Player p) {
		HPLabel.setText("����ֵ:"+player.getHP());
	}
	@Override
	public void atkChanged(Player p) {
		ATKLabel.setText("������:"+player.getATK());
	}
	@Override
	public void defChanged(Player p) {
		DEFLabel.setText("������:"+player.getDEF());
	}
	@Override
	public void moneyChanged(Player p) {
		moneyLabel.setText("��Ǯ:"+player.getMoney());
	}
	@Override
	public void expChanged(Player p) {
		EXPLabel.setText("����:"+player.getEXP());
	}
	@Override
	public void keyChanged(Player p) {
		yellowKeyLabel.setText("��Կ��:"+player.getYellowkey());
		blueKeyLabel.setText("��Կ��:"+player.getBluekey());
		redKeyLabel.setText("��Կ��:"+player.getRedkey());
	}
	@Override
	public void toolChanged(Player p) {
		// TODO Auto-generated method stub
	}
	
}
