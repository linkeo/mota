package com.nju.se.team.mota.game.util;

import java.awt.image.BufferedImage;

public class Animation {
	BufferedImage[][] images;
	int currentFrame, imageCnt;
	
	public void update(){
		currentFrame = (currentFrame+1)%imageCnt;
	}
	public BufferedImage[] currImage(){
		return images[currentFrame];
	}
	public void init(){
		currentFrame = 0;
	}
}
