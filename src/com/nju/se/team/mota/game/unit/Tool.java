package com.nju.se.team.mota.game.unit;

import java.awt.image.BufferedImage;

/**
 * ����
 * @author linkeo
 * @author lzw
 *
 */
public class Tool extends Abiotic{
	public static Tool parse(Abiotic a){
		Tool tool = new Tool();
		tool.load(a.parseEntityJSON());
		System.out.println(a.parseEntityJSON());
		return tool;
	}
	public BufferedImage getImage(){
		System.out.println(getCurrStatus());
		System.out.println(currAnimation());
		return currAnimation().currImage()[0][0];
	}
}