package com.nju.se.team.mota.game.unit;

import java.awt.image.BufferedImage;

/**
 * ¹¤¾ß
 * @author linkeo
 * @author lzw
 *
 */
public class Tool extends Abiotic{
	private String hintInfo;
	public static Tool parse(Abiotic a){
		Tool tool = new Tool();
		tool.load(a.parseEntityJSON());
		return tool;
	}
	public BufferedImage getImage(){
		return currAnimation().currImage()[0][0];
	}
	public String getHintInfo() {
		return hintInfo;
	}
	public void setHintInfo(String hintInfo) {
		this.hintInfo = hintInfo;
	}

}