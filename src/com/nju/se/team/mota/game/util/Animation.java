package com.nju.se.team.mota.game.util;

import java.awt.image.BufferedImage;

import org.json.JSONArray;

import com.nju.se.team.mota.data.ImageLoader;

public class Animation {
	String[][] images;
	int currentFrame, imageCnt;
	
	public void update(){
		currentFrame = (currentFrame+1)%imageCnt;
	}
	public BufferedImage[] currImage(){
		return ImageLoader.get(images[currentFrame]);
	}
	public void init(){
		currentFrame = 0;
	}
	public void load(JSONArray json){
		int x = json.length();
		int y = 0;
		if(x>0)y = json.getJSONArray(0).length();
		images = new String[x][y];
		for(int i=0;i<x;++i){
			JSONArray subj = json.getJSONArray(i);
			for(int j=0;j<y;++j){
				images[i][j] = subj.getString(j);
			}
		}
	}
	public static Animation create(JSONArray json){
		Animation a = new Animation();
		a.load(json);
		return a;
	}
	public String toJSONString(){
		StringBuilder sb = new StringBuilder();
		int x = images.length;
		int y = 0;
		if(x>0)y=images[0].length;
		images = new String[x][y];
		sb.append('[');
		for(int i=0;i<x;++i){
			if(i>0)sb.append(',');
			sb.append('[');
			for(int j=0;j<y;++j){
				if(j>0)sb.append(',');
				sb.append(images[i][j]);
			}
			sb.append(']');
		}
		sb.append(']');
		return sb.toString();
	}
}
