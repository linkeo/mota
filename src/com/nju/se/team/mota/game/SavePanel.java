package com.nju.se.team.mota.game;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.data.SaveLoader;
import com.nju.se.team.mota.game.uielem.SaveElem;
import com.nju.se.team.mota.util.ImageHandler;
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

			add(bgLabel);
			add(logoLabel);
			add(saveListPanel);

			bgLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			logoLabel.setBounds((PANEL_WIDTH-logoImage.getWidth())/2, 0,
					logoImage.getWidth(), logoImage.getHeight());
			saveListPanel.setBounds(320, 160, 320, 320);

			int z = 0;
			setComponentZOrder(logoLabel, z++);
			setComponentZOrder(saveListPanel, z++);
			setComponentZOrder(bgLabel, z++);
			
			for(int i=0;i<10;i++){
				saveListPanel.add(new SaveElem(Save.newSave("Íæ¼Ò"+i)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
