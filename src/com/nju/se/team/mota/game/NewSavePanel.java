package com.nju.se.team.mota.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;
import com.nju.se.team.mota.util.TransparentButton;
import com.nju.se.team.mota.util.TransparentLabel;
import com.nju.se.team.mota.util.TransparentPanel;

public class NewSavePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PANEL_WIDTH = 960, PANEL_HEIGHT = 640;
	BufferedImage bgImage, logoImage;
	TransparentLabel bgLabel, logoLabel;
	TransparentPanel nameInputPanel;
	JLabel nameLabel;
	JTextField nameText;
	TransparentButton back, submit;
	public NewSavePanel() {
		super(null);
		setSize(960, 640);
		try {
			bgImage = ImageIO.read(ResLoader.getImageFile("bg"));
			bgImage = ImageHandler.zoomStretch(bgImage, PANEL_WIDTH, PANEL_HEIGHT);
			logoImage = ImageIO.read(ResLoader.getImageFile("logo"));
			
			bgLabel = new TransparentLabel(new ImageIcon(bgImage));
			logoLabel = new TransparentLabel(new ImageIcon(logoImage));
			
			nameInputPanel = new TransparentPanel();
			nameInputPanel.setTransparency(0.5f);
			nameLabel = new JLabel("角色名:");
			nameText = new JTextField();
			nameInputPanel.add(nameLabel);
			nameInputPanel.add(nameText);
			
			back = new TransparentButton("返回", 0.5f, 0.75f, 1f);
			submit = new TransparentButton("确认", 0.5f, 0.75f, 1f);
			back.setFont(Fonts.getYahei(18));
			submit.setFont(Fonts.getYahei(18));
			nameLabel.setFont(Fonts.getYahei(18));
			nameText.setFont(Fonts.getYahei(18));
			nameLabel.setHorizontalAlignment(JLabel.CENTER);
			nameText.setHorizontalAlignment(JTextField.CENTER);
			
			add(bgLabel);
			add(logoLabel);
			add(nameInputPanel);
			add(back);
			add(submit);
			
			
			bgLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			logoLabel.setBounds((PANEL_WIDTH-logoImage.getWidth())/2, 0,
					logoImage.getWidth(), logoImage.getHeight());
			nameInputPanel.setBounds(320, 290, 320, 60);
				nameLabel.setBounds(10, 10, 100, 40);
				nameText.setBounds(120, 10, 190, 40);
			back.setBounds(320, 370, 155, 40);
			submit.setBounds(485, 370, 155, 40);
			
			int z = 0;
			setComponentZOrder(logoLabel, z++);
			setComponentZOrder(nameInputPanel, z++);
			setComponentZOrder(back, z++);
			setComponentZOrder(submit, z++);
			setComponentZOrder(bgLabel, z++);
			
			addListeners();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addListeners(){
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameMain.frame.jumpToMainMenu();
			}
		});
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String player = nameText.getText();
				if(player.isEmpty()) return;
				GameMain.frame.startNewGame(player);
			}
		});
	}
}