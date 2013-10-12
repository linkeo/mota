package com.nju.se.team.mota.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.data.SaveLoader;
import com.nju.se.team.mota.game.uielem.SaveElem;
import com.nju.se.team.mota.util.Fonts;
import com.nju.se.team.mota.util.ImageHandler;
import com.nju.se.team.mota.util.TransparentButton;
import com.nju.se.team.mota.util.TransparentLabel;

public class SavePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PANEL_WIDTH = 960, PANEL_HEIGHT = 640;
	BufferedImage bgImage, logoImage;
	TransparentLabel bgLabel, logoLabel;
	SaveListPanel saveListPanel;
	TransparentButton back, load, delete;
	public SavePanel() {
		super(null);
		setSize(960, 640);
		try {
			bgImage = ImageIO.read(ResLoader.getImageFile("bg"));
			bgImage = ImageHandler.zoomStretch(bgImage, PANEL_WIDTH, PANEL_HEIGHT);
			logoImage = ImageIO.read(ResLoader.getImageFile("logo"));
			
			bgLabel = new TransparentLabel(new ImageIcon(bgImage));
			logoLabel = new TransparentLabel(new ImageIcon(logoImage));
			
			saveListPanel = new SaveListPanel();
			saveListPanel.setTransparency(0.5f);
			
			back = new TransparentButton("∑µªÿ", 0.5f, 0.75f, 1f);
			load = new TransparentButton("‘ÿ»Î", 0.5f, 0.75f, 1f);
			delete = new TransparentButton("…æ≥˝", 0.5f, 0.75f, 1f);
			back.setFont(Fonts.getYahei(18));
			load.setFont(Fonts.getYahei(18));
			delete.setFont(Fonts.getYahei(18));
			
			add(bgLabel);
			add(logoLabel);
			add(saveListPanel);
			add(back);
			add(load);
			add(delete);
			
			
			bgLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			logoLabel.setBounds((PANEL_WIDTH-logoImage.getWidth())/2, 0,
					logoImage.getWidth(), logoImage.getHeight());
			saveListPanel.setBounds(320, 160, 320, 320);
			back.setBounds(320, 490, 100, 40);
			load.setBounds(430, 490, 100, 40);
			delete.setBounds(540, 490, 100, 40);
			
			int z = 0;
			setComponentZOrder(logoLabel, z++);
			setComponentZOrder(saveListPanel, z++);
			setComponentZOrder(back, z++);
			setComponentZOrder(load, z++);
			setComponentZOrder(delete, z++);
			setComponentZOrder(bgLabel, z++);
			
			loadSaves();
			addListeners();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSaves() {
		for(Save s : SaveLoader.getAllSaves())
			saveListPanel.add(new SaveElem(s));
	}

	private void addListeners(){
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameMain.frame.jumpToMainMenu();
			}
		});
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Save save = saveListPanel.getSelectedContent();
				if(save != null)
					GameMain.frame.startExistingGame(save);
			}
		});
	}
}
