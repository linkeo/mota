package com.nju.se.team.mota.game;

import java.awt.Color;
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
import com.nju.se.team.mota.util.ImageButton;
import com.nju.se.team.mota.util.TransparentLabel;

public class LogoPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PANEL_WIDTH = 960, PANEL_HEIGHT = 640,
			FRAME_LENGTH = 20;
	BufferedImage bgImage, logoImage;
	TransparentLabel bgLabel, logoLabel;
	Timer timer;
	int time;
	long startTime;
	boolean started = false;
	ImageButton bNew, bLoad, bQuit;
	BufferedImage newImage, loadImage, quitImage;
	JLabel lWriter;
	private static final String writer =
			"This game was programmed by Linkeo and lzw. Image Resources were from Internet.";
	
	public LogoPanel() {
		super(null);
		setSize(960, 640);
		try {
			bgImage = ImageIO.read(ResLoader.getImageFile("bg"));
			bgImage = ImageHandler.zoomStretch(bgImage, PANEL_WIDTH, PANEL_HEIGHT);
			logoImage = ImageIO.read(ResLoader.getImageFile("logo"));
			
			bgLabel = new TransparentLabel(new ImageIcon(bgImage));
			logoLabel = new TransparentLabel(new ImageIcon(logoImage));

			bNew = new ImageButton(
					new ImageIcon(newImage = ImageIO.read(ResLoader.getImageFile("b_new_0"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_new_1"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_new_1")))
					);
			bLoad = new ImageButton(
					new ImageIcon(loadImage = ImageIO.read(ResLoader.getImageFile("b_load_0"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_load_1"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_load_1")))
					);
			bQuit = new ImageButton(
					new ImageIcon(quitImage = ImageIO.read(ResLoader.getImageFile("b_quit_0"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_quit_1"))),
					new ImageIcon(ImageIO.read(ResLoader.getImageFile("b_quit_1")))
					);
			bNew.setActived(false);
			bLoad.setActived(false);
			bQuit.setActived(false);
			
			lWriter = new JLabel();
			lWriter.setForeground(Color.WHITE);
			
			add(bgLabel);
			add(logoLabel);
			
			add(bNew);
			add(bLoad);
			add(bQuit);
			
			add(lWriter);
			
			bgLabel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			logoLabel.setBounds((PANEL_WIDTH-logoImage.getWidth())/2,
					(PANEL_HEIGHT-logoImage.getHeight())/2,
					logoImage.getWidth(), logoImage.getHeight());
			bNew.setLocation((PANEL_WIDTH - bNew.getSize().width)/2,
					(PANEL_HEIGHT - bNew.getSize().height)/2-bNew.getSize().height);
			bLoad.setLocation((PANEL_WIDTH - bLoad.getSize().width)/2,
					(PANEL_HEIGHT - bLoad.getSize().height)/2);
			bQuit.setLocation((PANEL_WIDTH - bQuit.getSize().width)/2,
					(PANEL_HEIGHT - bQuit.getSize().height)/2+bQuit.getSize().height);
			lWriter.setBounds(10, PANEL_HEIGHT - 25, PANEL_WIDTH - 20, 20);
			
			timer = new Timer(10, this);
			
			int z = 0;
			setComponentZOrder(logoLabel, z++);
			setComponentZOrder(bNew, z++);
			setComponentZOrder(bLoad, z++);
			setComponentZOrder(bQuit, z++);
			setComponentZOrder(lWriter, z++);
			setComponentZOrder(bgLabel, z++);
			
			bNew.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(!bNew.isActived())return;
					timer.stop();
					GameMain.frame.newGame();
				}
			});
			bLoad.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(!bNew.isActived())return;
					timer.stop();
					bLoad.setNormal();
					GameMain.frame.loadGame();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(System.currentTimeMillis() - startTime > (time+1)*FRAME_LENGTH){
			updateLogo(time++);
		}
	}
	private void updateLogo(int i) {
		
		//效果1: LOGO渐现: 0~50
		float logoOpacity = 1f;
		switch(i/50){
		case 0: logoOpacity = i/50f; break;
		default: logoOpacity = 1f;
		}
		logoLabel.setTransparency(logoOpacity);
		
		
		//效果2: LOGO上移: 50~150
		int startY = (PANEL_HEIGHT-logoImage.getHeight())/2;
		int endY = 0;
		int logoY = startY;
		switch((i+50)/100){
		case 0: logoY = startY; break;
		case 1: logoY = (int) (startY + (endY-startY)*((i-50)/100f)); break;
		default: logoY = endY;
		}
		logoLabel.setLocation((PANEL_WIDTH-logoImage.getWidth())/2, logoY);

		//效果3: 按钮显现: ?(>50)~150
		logoY = logoY+logoLabel.getSize().height/2;
		int newY = (PANEL_HEIGHT - bNew.getSize().height)/2-bNew.getSize().height+bNew.getSize().height/2;
		int loadY = (PANEL_HEIGHT - bLoad.getSize().height)/2+bLoad.getSize().height/2;
		int quitY = (PANEL_HEIGHT - bQuit.getSize().height)/2+bQuit.getSize().height+bQuit.getSize().height/2;
		int newMIN = logoLabel.getSize().height/2+bNew.getSize().height/2;
		int loadMIN = logoLabel.getSize().height/2+bLoad.getSize().height/2;
		int quitMIN = logoLabel.getSize().height/2+bQuit.getSize().height/2;
		int newMAX = newY-(endY+logoLabel.getSize().height/2);
		int loadMAX = loadY-(endY+logoLabel.getSize().height/2);
		int quitMAX = quitY-(endY+logoLabel.getSize().height/2);
		newMAX = Math.min(newMAX, Math.min(loadMAX, quitMAX));
		loadMAX = Math.min(newMAX, Math.min(loadMAX, quitMAX));
		quitMAX = Math.min(newMAX, Math.min(loadMAX, quitMAX));
		
		float newOpacity = 0f;
		float loadOpacity = 0f;
		float quitOpacity = 0f;
		
		if(newY - logoY < newMIN){
			newOpacity = 0f;
		}else if(newY - logoY > newMAX){
			newOpacity = 1f;
		}else
			newOpacity = 1f*(newY - logoY - newMIN)/(newMAX - newMIN);
		
		if(loadY - logoY < loadMIN){
			loadOpacity = 0f;
		}else if(loadY - logoY > loadMAX){
			loadOpacity = 1f;
		}else
			loadOpacity = 1f*(loadY - logoY - loadMIN)/(loadMAX - loadMIN);
		
		if(quitY - logoY < quitMIN){
			quitOpacity = 0f;
		}else if(quitY - logoY > quitMAX){
			quitOpacity = 1f;
		}else
			quitOpacity = 1f*(quitY - logoY - quitMIN)/(quitMAX - quitMIN);
//		switch(i/50){
//		case 0: newOpacity = loadOpacity = quitOpacity = 0f; break;
//		case 1: newOpacity = loadOpacity = quitOpacity = 0f; break;
//		case 2: newOpacity = loadOpacity = quitOpacity = (i-100)/50f; break;
//		default: newOpacity = loadOpacity = quitOpacity = 1f;
//		}
		bNew.setTransparency(newOpacity);
		bLoad.setTransparency(loadOpacity);
		bQuit.setTransparency(quitOpacity);
		if(i>150){
			bNew.setActived(true);
			bLoad.setActived(true);
			bQuit.setActived(true);
		}	
		
		//效果4: 显示作者 150~?
		if(i>150){
			int pos = i-150;
			pos = Math.min(pos, writer.length());
			lWriter.setText(writer.substring(0, pos));
		}
	}
	@Override
	public void repaint() {
		super.repaint();
	}
	@Override
	public void paint(Graphics g) {
		if(!started) start();
		if(timer.)
		super.paint(g);
	}
	private void start() {
		started = true;
		startTime = System.currentTimeMillis();
		timer.start();
	}
	
	
	
}
