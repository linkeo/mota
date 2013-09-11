package com.nju.se.team.mota.game.util;

import java.awt.image.BufferedImage;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nju.se.team.mota.data.ImageLoader;

public class Animation {
	private String[][][] images;
	public String[][][] getImages() {
		return images;
	}
	public void setImages(String[][][] images) {
		this.images = images;
	}
	int currentFrame, imageCnt;
	public Animation() {
		images = new String[0][0][0];
	}
	public Animation(int frameCnt, int x, int y) {
		images = new String[frameCnt][x][y];
	}
	public void update(){
		currentFrame = (currentFrame+1)%imageCnt;
	}
	public BufferedImage[][] currImage(){
		return getImageAt(currentFrame);
	}
	public BufferedImage[][] getImageAt(int frame){
		int y = 0, z=0;
		y = images[frame].length;
		if(y>0)z = images[frame][0].length;
		BufferedImage[][] bis = new BufferedImage[y][z];
		for(int i=0;i<y;++i)
			for(int j=0;j<z;++j)
				bis[i][j] = ImageLoader.get(images[frame][i][j]);
		return bis;
	}
	public String[][] getImageKeyAt(int frame){
		int y = 0, z=0;
		y = images[frame].length;
		if(y>0)z = images[frame][0].length;
		String[][] bis = new String[y][z];
		for(int i=0;i<y;++i)
			for(int j=0;j<z;++j)
				bis[i][j] = images[frame][i][j];
		return bis;
	}
	public void init(){
		currentFrame = 0;
	}
	public void load(JSONObject json){
		JSONArray js = json.getJSONArray("images");
		int x = js.length();
		int y = 0, z=0;
		if(x>0)y = js.getJSONArray(0).length();
		if(y>0)z = js.getJSONArray(0).getJSONArray(0).length();
		images = new String[x][y][z];
		for(int i=0;i<x;++i){
			JSONArray subj = js.getJSONArray(i);
			for(int j=0;j<y;++j){
				JSONArray subsubj = subj.getJSONArray(j);
				for(int k=0;k<z;++k){
					images[i][j][k] = subsubj.getString(k);
				}
			}
		}
	}
	public static Animation make(JSONObject json){
		Animation a = new Animation();
		a.load(json);
		return a;
	}
	public Animation copy(){
		return Animation.make(new JSONObject(this));
	}
	public int frameCount(){
		return images.length;
	}
}
