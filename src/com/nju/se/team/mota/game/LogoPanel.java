package com.nju.se.team.mota.game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.nju.se.team.mota.data.ResLoader;
import com.nju.se.team.mota.util.ImageHandler;

public class LogoPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PANEL_WIDTH = 960, PANEL_HEIGHT = 640,
			FRAME_LENGTH = 20;
	BufferedImage bgImage, logoImage;
	JLabel bgLabel, logoLabel;
	Timer timer;
	int time;
	long startTime;
	boolean started = false;
	
	public LogoPanel() {
		super(null);
		setSize(960, 640);
		try {
			bgImage = ImageIO.read(ResLoader.getImageFile("bg"));
			bgImage = ImageHandler.zoomStretch(bgImage, PANEL_WIDTH, PANEL_HEIGHT);
			logoImage = ImageIO.read(ResLoader.getImageFile("logo"));
			
			bgLabel = new JLabel(new ImageIcon(bgImage));
			logoLabel = new JLabel(new ImageIcon(logoImage));
			
			add(bgLabel);
			add(logoLabel);
			bgLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			logoLabel.setBounds((PANEL_WIDTH-logoImage.getWidth())/2,
					(PANEL_HEIGHT-logoImage.getHeight())/2,
					logoImage.getWidth(), logoImage.getHeight());
			timer = new Timer(10, this);
			setComponentZOrder(logoLabel, 0);
			setComponentZOrder(bgLabel, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(System.currentTimeMillis() - startTime > (time+1)*FRAME_LENGTH){
			updateLogo(time++);
		}
	}
	private void updateLogo(int i) {
		float logoOpacity = 1f;
		switch(i/50){
		case 0: logoOpacity = i/50f; break;
//		case 1: logoOpacity = (100-i)/50f; break;
//		case 2: logoOpacity = (i-100)/50f; break;
		default: logoOpacity = 1f;
		}
		BufferedImage logoImage = ImageHandler.transparent(this.logoImage, logoOpacity);
		logoLabel.setIcon(new ImageIcon(logoImage));
		
		int startY = (PANEL_HEIGHT-logoImage.getHeight())/2;
		int endY = 0;
		int logoY = startY;
		switch((i+50)/100){
		case 0: logoY = startY; break;
		case 1: logoY = (int) (startY + (endY-startY)*((i-50)/100f)); break;
		default: logoY = endY;
		}
		logoLabel.setLocation((PANEL_WIDTH-logoImage.getWidth())/2, logoY);
		
	}
	@Override
	public void repaint() {
		super.repaint();
	}
	@Override
	public void paint(Graphics g) {
		if(!started) start();
		super.paint(g);
	}
	private void start() {
		started = true;
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	
	
}
